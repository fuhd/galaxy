package com.starriverdata.core.netty.worker.request;

import com.starriverdata.common.constants.Constants;
import com.starriverdata.common.entity.HeraAction;
import com.starriverdata.common.entity.HeraJobHistory;
import com.starriverdata.common.entity.model.HeraJobBean;
import com.starriverdata.common.entity.vo.HeraDebugHistoryVo;
import com.starriverdata.common.entity.vo.HeraJobHistoryVo;
import com.starriverdata.common.enums.StatusEnum;
import com.starriverdata.common.exception.HeraException;
import com.starriverdata.common.util.ActionUtil;
import com.starriverdata.common.util.BeanConvertUtils;
import com.starriverdata.common.vo.JobStatus;
import com.starriverdata.config.HeraGlobalEnv;
import com.starriverdata.core.job.Job;
import com.starriverdata.core.job.JobContext;
import com.starriverdata.core.netty.worker.HistoryPair;
import com.starriverdata.core.netty.worker.WorkContext;
import com.starriverdata.core.util.JobUtils;
import com.starriverdata.logs.ErrorLog;
import com.starriverdata.logs.GalaxyLog;
import com.starriverdata.logs.ScheduleLog;
import com.starriverdata.logs.SocketLog;
import com.starriverdata.protocol.*;
import com.google.protobuf.InvalidProtocolBufferException;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Future;

/**
 * worker job 最终执行体，收到master handler执行请求的时候，开始创建Job processor
 */
public class WorkExecuteJob {

    public Future<RpcResponse.Response> execute(WorkContext workContext, RpcRequest.Request request) {
        try {
            if (request.getOperate() == RpcOperate.Operate.Debug) {
                return debug(workContext, request);
            } else if (request.getOperate() == RpcOperate.Operate.Manual) {
                return manual(workContext, request);
            } else if (request.getOperate() == RpcOperate.Operate.Schedule) {
                return schedule(workContext, request);
            }
        } catch (HeraException e) {
            ErrorLog.error("执行任务失败", e);
        }
        return null;
    }


    /**
     * worker中，调度中心手动执行任务最终执行位置，JobUtils.createDebugJob创建job文件到服务器，拼接shell，并调用命令执行
     */
    private Future<RpcResponse.Response> manual(WorkContext workContext, RpcRequest.Request request) throws HeraException {
        RpcExecuteMessage.ExecuteMessage message;
        try {
            message = RpcExecuteMessage.ExecuteMessage.newBuilder().mergeFrom(request.getBody()).build();
        } catch (InvalidProtocolBufferException e) {
            throw new HeraException("解析消息异常", e);
        }
        final Long actionId = Long.parseLong(message.getActionId());
        SocketLog.info("worker received master request to run manual job, actionId = {}", actionId);
        HeraAction heraAction = workContext.getHeraJobActionService().findById(actionId);
        HeraJobHistoryVo history = BeanConvertUtils.convert(workContext.getHeraJobHistoryService().findById(heraAction.getHistoryId()));
        return workContext.getWorkExecuteThreadPool().submit(() -> {
            history.setExecuteHost(WorkContext.host);
            history.setStartTime(new Date());
            workContext.getHeraJobHistoryService().update(BeanConvertUtils.convert(history));

            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            File directory = new File(HeraGlobalEnv.getWorkDir()
                    + File.separator + date + File.separator + "manual-" + history.getId());
            if (!directory.exists()) {
                if (!directory.mkdirs()) {
                    GalaxyLog.error("创建文件失败:" + directory.getAbsolutePath());
                }
            }
            int exitCode = -1;
            Exception exception = null;
            HistoryPair historyPair = new HistoryPair(actionId, heraAction.getHistoryId());
            try {
                HeraJobBean jobBean = workContext.getHeraGroupService().getUpstreamJobBean(history.getJobId());
                Job job = JobUtils.createScheduleJob(new JobContext(JobContext.SCHEDULE_RUN),
                        jobBean, history, directory.getAbsolutePath());
                workContext.getManualRunning().put(historyPair, job);
                exitCode = job.run();
            } catch (Exception e) {
                exception = e;
                history.getLog().appendHeraException(e);
            } finally {
                StatusEnum statusEnum = getStatusFromCode(exitCode);
                //更新状态和日志
                workContext.getHeraJobHistoryService().updateHeraJobHistoryLogAndStatus(
                        HeraJobHistory.builder()
                                .id(history.getId())
                                .log(history.getLog().getContent())
                                .status(statusEnum.toString())
                                .illustrate(history.getIllustrate())
                                .endTime(new Date())
                                .build());
                workContext.getManualRunning().remove(historyPair);
            }

            ResponseStatus.Status status = ResponseStatus.Status.OK;
            String errorText = "";
            if (exitCode != 0) {
                status = ResponseStatus.Status.ERROR;
            }
            if (exception != null && exception.getMessage() != null) {
                errorText = exception.getMessage();
            }

            RpcResponse.Response response = RpcResponse.Response.newBuilder()
                    .setRid(request.getRid())
                    .setOperate(RpcOperate.Operate.Schedule)
                    .setStatusEnum(status)
                    .setErrorText(errorText)
                    .build();
            SocketLog.info("send execute message, actionId = {}", actionId);
            return response;
        });
    }

    /**
     * worker中，调度中心自动调度任务最终执行位置，JobUtils.createDebugJob创建job文件到服务器，拼接shell，并调用命令执行
     */
    private Future<RpcResponse.Response> schedule(WorkContext workContext, RpcRequest.Request request) throws HeraException {
        RpcExecuteMessage.ExecuteMessage message;
        try {
            message = RpcExecuteMessage.ExecuteMessage.newBuilder().mergeFrom(request.getBody()).build();
        } catch (InvalidProtocolBufferException e) {
            throw new HeraException("解析消息异常", e);
        }
        // 查看master分发 actionHistoryId
        final Long jobId = Long.parseLong(message.getActionId());
        SocketLog.info("worker received master request to run schedule, actionId :" + jobId);
        JobStatus jobStatus = workContext.getHeraJobActionService().findJobStatus(jobId);
        HeraJobHistory heraJobHistory = workContext.getHeraJobHistoryService().findById(jobStatus.getHistoryId());
        HeraJobHistoryVo history = BeanConvertUtils.convert(heraJobHistory);
        return workContext.getWorkExecuteThreadPool().submit(() -> {
            history.setExecuteHost(WorkContext.host);
            history.setStartTime(new Date());
            workContext.getHeraJobHistoryService().update(BeanConvertUtils.convert(history));
            HeraJobBean jobBean = workContext.getHeraGroupService().getUpstreamJobBean(heraJobHistory.getJobId());
            String date = ActionUtil.getCurrDate();
            File directory = new File(HeraGlobalEnv.getWorkDir()
                    + File.separator + date + File.separator + history.getId());
            if (!directory.exists()) {
                if (!directory.mkdirs()) {
                    GalaxyLog.error("创建文件失败:" + directory.getAbsolutePath());
                }
            }

            int exitCode = -1;
            Exception exception = null;
            HistoryPair historyPair = new HistoryPair(jobId, jobStatus.getHistoryId());
            try {
                Job job = JobUtils.createScheduleJob(new JobContext(JobContext.SCHEDULE_RUN), jobBean, history, directory.getAbsolutePath());
                workContext.getRunning().put(historyPair, job);
                exitCode = job.run();
            } catch (Exception e) {
                exception = e;
                history.getLog().appendHeraException(e);
            } finally {
                StatusEnum statusEnum = getStatusFromCode(exitCode);
                //更新状态和日志
                workContext.getHeraJobHistoryService().updateHeraJobHistoryLogAndStatus(
                        HeraJobHistory.builder().
                                id(history.getId()).
                                log(history.getLog().getContent()).status(statusEnum.toString()).
                                endTime(new Date())
                                .illustrate(history.getIllustrate())
                                .build());
                workContext.getRunning().remove(historyPair);
            }

            ResponseStatus.Status status = ResponseStatus.Status.OK;
            String errorText = "";
            if (exitCode != 0) {
                status = ResponseStatus.Status.ERROR;
            }
            if (exception != null) {
                errorText = exception.toString();
            }

            RpcResponse.Response response = RpcResponse.Response.newBuilder()
                    .setRid(request.getRid())
                    .setOperate(RpcOperate.Operate.Schedule)
                    .setStatusEnum(status)
                    .setErrorText(errorText)
                    .build();
            ScheduleLog.info("send execute message, resId = {} actionId = {}", request.getRid(), jobId);
            return response;
        });

    }

    /**
     * worker中，开发中心脚本执行最终执行位置，JobUtils.createDebugJob创建job文件到服务器，拼接shell，并调用命令执行
     */
    private Future<RpcResponse.Response> debug(WorkContext workContext, RpcRequest.Request request) throws HeraException {
        RpcDebugMessage.DebugMessage debugMessage;
        try {
            debugMessage = RpcDebugMessage.DebugMessage.newBuilder().mergeFrom(request.getBody()).build();
        } catch (InvalidProtocolBufferException e) {
            throw new HeraException("解析消息异常", e);
        }
        Long debugId = Long.parseLong(debugMessage.getDebugId());
        HeraDebugHistoryVo history = workContext.getHeraDebugHistoryService().findById(debugId);
        return workContext.getWorkExecuteThreadPool().submit(() -> {
            int exitCode = -1;
            Exception exception = null;
            ResponseStatus.Status status;
            history.setExecuteHost(WorkContext.host);
            workContext.getHeraDebugHistoryService().update(BeanConvertUtils.convert(history));
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            try {
                File directory = new File(HeraGlobalEnv.getWorkDir() + File.separator + date + File.separator + "debug-" + debugId);
                if (!directory.exists()) {
                    if (!directory.mkdirs()) {
                        GalaxyLog.error("创建文件失败:" + directory.getAbsolutePath());
                    }
                }
                HeraJobBean jobBean = workContext.getHeraGroupService().getUpstreamJobBean(history.getJobId());
                Job job = JobUtils.createDebugJob(new JobContext(JobContext.DEBUG_RUN), BeanConvertUtils.convert(history), jobBean,
                        directory.getAbsolutePath(), workContext);
                workContext.getDebugRunning().putIfAbsent(debugId, job);
                exitCode = job.run();
            } catch (Exception e) {
                exception = e;
                history.getLog().appendHeraException(e);
            } finally {
                HeraDebugHistoryVo heraDebugHistoryVo = workContext.getHeraDebugHistoryService().findById(debugId);
                heraDebugHistoryVo.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

                StatusEnum statusEnum = getStatusFromCode(exitCode);
                if (exitCode == 0) {
                    status = ResponseStatus.Status.OK;
                    heraDebugHistoryVo.setStatus(statusEnum);
                } else {
                    status = ResponseStatus.Status.ERROR;
                    heraDebugHistoryVo.setStatus(statusEnum);
                }
                workContext.getHeraDebugHistoryService().updateStatus(BeanConvertUtils.convert(heraDebugHistoryVo));
                HeraDebugHistoryVo debugHistory = workContext.getDebugRunning().get(debugId).getJobContext().getDebugHistory();
                workContext.getHeraDebugHistoryService().updateLog(BeanConvertUtils.convert(debugHistory));
                workContext.getDebugRunning().remove(debugId);
            }
            String errorText = "";
            if (exception != null && exception.getMessage() != null) {
                errorText = exception.getMessage();
            }
            return RpcResponse.Response.newBuilder()
                    .setRid(request.getRid())
                    .setOperate(RpcOperate.Operate.Debug)
                    .setStatusEnum(status)
                    .setErrorText(errorText)
                    .build();
        });
    }

    private StatusEnum getStatusFromCode(int exitCode) {
        if (exitCode == Constants.SUCCESS_EXIT_CODE) {
            return StatusEnum.SUCCESS;
        }

        if (exitCode == Constants.WAIT_EXIT_CODE) {
            return StatusEnum.WAIT;
        }
        return StatusEnum.FAILED;

    }
}
