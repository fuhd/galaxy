package com.starriverdata.core.netty.master.response;

import com.starriverdata.common.constants.Constants;
import com.starriverdata.common.entity.HeraJobHistory;
import com.starriverdata.common.entity.vo.HeraDebugHistoryVo;
import com.starriverdata.common.entity.vo.HeraJobHistoryVo;
import com.starriverdata.common.enums.TriggerTypeEnum;
import com.starriverdata.common.util.BeanConvertUtils;
import com.starriverdata.common.vo.JobElement;
import com.starriverdata.core.exception.RemotingException;
import com.starriverdata.core.message.HeartBeatInfo;
import com.starriverdata.core.netty.master.MasterContext;
import com.starriverdata.core.netty.master.MasterWorkHolder;
import com.starriverdata.core.netty.master.RunJobThreadPool;
import com.starriverdata.core.netty.worker.WorkContext;
import com.starriverdata.core.tool.CpuLoadPerCoreJob;
import com.starriverdata.core.tool.MemUseRateJob;
import com.starriverdata.event.Events;
import com.starriverdata.event.HeraJobMaintenanceEvent;
import com.starriverdata.logs.ErrorLog;
import com.starriverdata.logs.GalaxyLog;
import com.starriverdata.logs.TaskLog;
import com.starriverdata.protocol.*;
import com.starriverdata.protocol.JobExecuteKind.ExecuteKind;
import com.starriverdata.protocol.ResponseStatus.Status;
import com.starriverdata.protocol.RpcHeartBeatMessage.AllHeartBeatInfoMessage;
import com.starriverdata.protocol.RpcHeartBeatMessage.HeartBeatMessage;
import com.starriverdata.protocol.RpcWebOperate.WebOperate;
import com.starriverdata.protocol.RpcWebRequest.WebRequest;
import com.starriverdata.protocol.RpcWebResponse.WebResponse;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * master处理work发起的web请求
 */
public class MasterHandlerWebResponse {


    private static volatile boolean workReady = false;

    /**
     * 处理work发起的调度中心任务执行 操作
     */
    public static WebResponse handleWebExecute(MasterContext context, WebRequest request) {
        if (request.getEk() == ExecuteKind.ManualKind || request.getEk() == ExecuteKind.ScheduleKind) {
            Long historyId = Long.parseLong(request.getId());

            HeraJobHistory heraJobHistory = context.getHeraJobHistoryService().findById(historyId);
            HeraJobHistoryVo history = BeanConvertUtils.convert(heraJobHistory);
            context.getMaster().run(history, context.getHeraJobService().findById(history.getJobId()));
            WebResponse webResponse = WebResponse.newBuilder()
                    .setRid(request.getRid())
                    .setOperate(WebOperate.ExecuteJob)
                    .setStatus(Status.OK)
                    .build();
            TaskLog.info("MasterHandlerWebResponse: send web execute response, actionId = {} ", history.getJobId());
            return webResponse;
        } else if (request.getEk() == ExecuteKind.DebugKind) {
            Long debugId = Long.parseLong(request.getId());
            HeraDebugHistoryVo debugHistory = context.getHeraDebugHistoryService().findById(debugId);
            TaskLog.info("2-1.MasterHandlerWebResponse: receive web debug response, debugId = " + debugId);
            context.getMaster().debug(debugHistory);

            WebResponse webResponse = WebResponse.newBuilder()
                    .setRid(request.getRid())
                    .setOperate(WebOperate.ExecuteJob)
                    .setStatus(Status.OK)
                    .build();
            TaskLog.info("2-2.MasterHandlerWebResponse : send web debug response, debugId = {}", debugId);
            return webResponse;
        }
        return WebResponse.newBuilder()
                .setRid(request.getRid())
                .setErrorText("未识别的操作类型" + request.getEk())
                .setStatus(Status.ERROR)
                .build();
    }

    /**
     * 处理work发起的开发中心任务执行 操作
     */
    public static WebResponse handleWebDebug(MasterContext context, WebRequest request) {
        Long debugId = Long.parseLong(request.getId());
        Queue<JobElement> queue = context.getDebugQueue();
        WebResponse response;
        for (JobElement jobElement : queue) {
            if (jobElement.getJobId().equals(debugId)) {
                response = WebResponse.newBuilder()
                        .setRid(request.getRid())
                        .setOperate(WebOperate.ExecuteDebug)
                        .setStatus(Status.ERROR)
                        .setErrorText("任务已经在队列中")
                        .build();
                return response;
            }
        }
        HeraDebugHistoryVo debugHistory = context.getHeraDebugHistoryService().findById(debugId);
        context.getMaster().debug(debugHistory);
        return WebResponse.newBuilder()
                .setRid(request.getRid())
                .setOperate(WebOperate.ExecuteDebug)
                .setStatus(Status.OK)
                .build();
    }

    /**
     * 处理work发起的任务更新 操作
     */
    public static WebResponse handleWebUpdate(MasterContext context, WebRequest request) {
        Long id = Long.parseLong(request.getId());
        context.getMaster().generateSingleAction(Integer.parseInt(request.getId()));
        context.getDispatcher().forwardEvent(new HeraJobMaintenanceEvent(Events.UpdateJob, id));
        return WebResponse.newBuilder()
                .setRid(request.getRid())
                .setOperate(WebOperate.UpdateJob)
                .setStatus(Status.OK)
                .build();

    }

    /**
     * 处理work发起的生成版本 操作
     */
    public static WebResponse generateActionByJobId(MasterContext context, WebRequest request) {
        boolean result = String.valueOf(Constants.ALL_JOB_ID).equals(request.getId()) ? context.getMaster().generateBatchAction(true) : context.getMaster().generateSingleAction(Integer.parseInt(request.getId()));
        return WebResponse.newBuilder()
                .setRid(request.getRid())
                .setOperate(WebOperate.ExecuteJob)
                .setStatus(result ? Status.OK : Status.ERROR)
                .build();

    }

    /**
     * 处理work发起的任务取消操作
     */
    public static WebResponse handleWebCancel(MasterContext context, WebRequest request) {
        switch (request.getEk()) {
            case ManualKind:
            case DebugKind:
            case ScheduleKind:
                return MasterCancelJob.cancel(request.getEk(), context, request);
            default:
                return WebResponse.newBuilder()
                        .setRid(request.getRid())
                        .setOperate(request.getOperate())
                        .setStatus(ResponseStatus.Status.ERROR)
                        .setErrorText("无法识别的任务取消类型：" + request.getEk())
                        .build();
        }
    }

    /**
     * 处理work发起的任务执行 操作
     */
    public static WebResponse buildJobQueueInfo(MasterContext context, WebRequest request) {
        //输出线程池信息
        context.getMaster().printThreadPoolLog();
        Map<String, HeartBeatMessage> allInfo = new HashMap<>(context.getWorkMap().size());
        context.getWorkMap().values().forEach(workHolder -> {
            HeartBeatInfo beatInfo = workHolder.getHeartBeatInfo();
            if (beatInfo != null) {
                allInfo.put(Constants.WORK_PREFIX + beatInfo.getHost(), HeartBeatMessage.newBuilder()
                        .addAllDebugRunnings(beatInfo.getDebugRunning())
                        .addAllRunnings(beatInfo.getRunning())
                        .addAllManualRunnings(beatInfo.getManualRunning())
                        .setMemRate(beatInfo.getMemRate())
                        .setMemTotal(beatInfo.getMemTotal())
                        .setCpuLoadPerCore(beatInfo.getCpuLoadPerCore())
                        .setTimestamp(beatInfo.getTimestamp())
                        .setHost(beatInfo.getHost())
                        .setCores(beatInfo.getCores())
                        .build());

            }
        });


        //debug任务队列
        List<Long> waitDebugQueue = RunJobThreadPool.getWaitClusterJob(TriggerTypeEnum.DEBUG);
        List<Long> masterDebugQueue = new ArrayList<>(waitDebugQueue.size() + context.getDebugQueue().size());
        context.getDebugQueue().forEach(jobElement -> masterDebugQueue.add(jobElement.getJobId()));
        masterDebugQueue.addAll(waitDebugQueue);
        //自动任务队列

        List<Long> waitScheduleQueue = RunJobThreadPool.getWaitClusterJob(TriggerTypeEnum.SCHEDULE, TriggerTypeEnum.MANUAL_RECOVER);
        List<Long> masterScheduleQueue = new ArrayList<>(waitScheduleQueue.size() + context.getScheduleQueue().size());
        masterScheduleQueue.addAll(waitScheduleQueue);
        context.getScheduleQueue().forEach(jobElement -> masterScheduleQueue.add(jobElement.getJobId()));

        //手动任务队列
        List<Long> waitManualQueue = RunJobThreadPool.getWaitClusterJob(TriggerTypeEnum.MANUAL);
        List<Long> masterManualQueue = new ArrayList<>(waitManualQueue.size() + context.getManualQueue().size());
        masterManualQueue.addAll(waitManualQueue);
        context.getManualQueue().forEach(jobElement -> masterManualQueue.add(jobElement.getJobId()));


        MemUseRateJob memUseRateJob = new MemUseRateJob(1);
        memUseRateJob.readMemUsed();
        CpuLoadPerCoreJob loadPerCoreJob = new CpuLoadPerCoreJob();
        loadPerCoreJob.run();

        allInfo.put(Constants.MASTER_PREFIX + WorkContext.host, HeartBeatMessage.newBuilder()
                .addAllDebugRunnings(masterDebugQueue.stream().map(String::valueOf).collect(Collectors.toList()))
                .addAllRunnings(masterScheduleQueue.stream().map(String::valueOf).collect(Collectors.toList()))
                .addAllManualRunnings(masterManualQueue.stream().map(String::valueOf).collect(Collectors.toList()))
                .setMemRate(memUseRateJob.getRate())
                .setMemTotal(memUseRateJob.getMemTotal())
                .setCpuLoadPerCore(loadPerCoreJob.getLoadPerCore())
                .setTimestamp(System.currentTimeMillis())
                .setHost(WorkContext.host)
                .setCores(WorkContext.cpuCoreNum)
                .build());

        return WebResponse.newBuilder()
                .setRid(request.getRid())
                .setOperate(request.getOperate())
                .setStatus(Status.OK)
                .setBody(AllHeartBeatInfoMessage.newBuilder().putAllValues(allInfo).build().toByteString())
                .build();
    }

    public static synchronized WebResponse buildAllWorkInfo(MasterContext context, WebRequest request) {
        if (!workReady) {
            GalaxyLog.info("workInfo未准备，准备请求work组装workInfo");
            //发送workInfo build 请求
            context.getThreadPool().submit(() -> context.getWorkMap().values().parallelStream().forEach(workHolder -> {
                try {
                    workHolder.getChannel().writeAndFlush(RpcSocketMessage.SocketMessage.newBuilder()
                            .setKind(RpcSocketMessage.SocketMessage.Kind.REQUEST)
                            .setBody(RpcRequest.Request.newBuilder().setOperate(RpcOperate.Operate.GetWorkInfo).build().toByteString())
                            .build());
                } catch (RemotingException e) {
                    ErrorLog.error("发送消息异常", e);
                }
            }));
            CountDownLatch latch = new CountDownLatch(1);
            context.getThreadPool().submit(() -> {
                int maxTime = 300, cnt = 0;
                boolean canExit;
                try {
                    while (cnt++ < maxTime) {
                        canExit = true;
                        for (MasterWorkHolder workHolder : context.getWorkMap().values()) {
                            if (workHolder.getWorkInfo() == null) {
                                canExit = false;
                                break;
                            }
                        }
                        if (canExit) {
                            GalaxyLog.info("所有workInfo已准备完毕");
                            workReady = true;
                            break;
                        }
                        TimeUnit.MILLISECONDS.sleep(10);
                    }
                } catch (InterruptedException e) {
                    ErrorLog.error("InterruptedException ", e);
                } finally {
                    latch.countDown();
                }
            });
            try {
                latch.await();
            } catch (InterruptedException e) {
                ErrorLog.error("InterruptedException", e);
            }

            context.getMasterSchedule().schedule(() -> {
                GalaxyLog.info("开始清理workInfo");
                workReady = false;
                context.getWorkMap().values().forEach(workHolder -> workHolder.setWorkInfo(null));
            }, 30, TimeUnit.SECONDS);
        }
        GalaxyLog.info("开始组装workInfo");

        Map<String, RpcWorkInfo.WorkInfo> workInfoMap = new HashMap<>(context.getWorkMap().size());
        context.getWorkMap().values().forEach(workHolder -> {
            String host = workHolder.getHeartBeatInfo().getHost();
            if (workHolder.getWorkInfo() != null) {
                if (host.equals(WorkContext.host)) {
                    workInfoMap.put(Constants.MASTER_PREFIX + host, workHolder.getWorkInfo());
                } else {
                    workInfoMap.put(Constants.WORK_PREFIX + host, workHolder.getWorkInfo());
                }
            }
        });
        return WebResponse.newBuilder()
                .setRid(request.getRid())
                .setOperate(request.getOperate())
                .setStatus(Status.OK)
                .setBody(RpcWorkInfo.AllWorkInfo.newBuilder().putAllValues(workInfoMap).build().toByteString())
                .build();
    }
}
