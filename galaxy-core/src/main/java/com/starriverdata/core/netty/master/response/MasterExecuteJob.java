package com.starriverdata.core.netty.master.response;

import com.starriverdata.common.enums.TriggerTypeEnum;
import com.starriverdata.config.HeraGlobalEnv;
import com.starriverdata.core.exception.RemotingException;
import com.starriverdata.core.netty.listener.MasterResponseListener;
import com.starriverdata.core.netty.master.MasterContext;
import com.starriverdata.core.netty.master.MasterWorkHolder;
import com.starriverdata.core.netty.util.AtomicIncrease;
import com.starriverdata.logs.ErrorLog;
import com.starriverdata.logs.TaskLog;
import com.starriverdata.protocol.JobExecuteKind.ExecuteKind;
import com.starriverdata.protocol.RpcDebugMessage.DebugMessage;
import com.starriverdata.protocol.RpcExecuteMessage.ExecuteMessage;
import com.starriverdata.protocol.RpcOperate.Operate;
import com.starriverdata.protocol.RpcRequest.Request;
import com.starriverdata.protocol.RpcResponse.Response;
import com.starriverdata.protocol.RpcSocketMessage.SocketMessage;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaosuda
 * @date 2018/11/10
 */
public class MasterExecuteJob {

    public Future<Response> executeJob(final MasterContext context, final MasterWorkHolder holder, ExecuteKind kind, final Long id) {
        switch (kind) {
            case ScheduleKind:
                return executeScheduleJob(context, holder, id);
            case ManualKind:
                return executeManualJob(context, holder, id);
            case DebugKind:
                return executeDebugJob(context, holder, id);
            default:
                return null;
        }
    }


    /**
     * 请求work 执行手动任务
     *
     * @param context    MasterContext
     * @param workHolder MasterWorkHolder
     * @return Future
     */
    private Future<Response> executeManualJob(MasterContext context, MasterWorkHolder workHolder, Long actionId) {
        workHolder.getManningRunning().add(actionId);
        return buildFuture(context, Request.newBuilder()
                .setRid(AtomicIncrease.getAndIncrement())
                .setOperate(Operate.Manual)
                .setBody(ExecuteMessage
                        .newBuilder()
                        .setActionId(String.valueOf(actionId))
                        .build().toByteString())
                .build(), workHolder, actionId, TriggerTypeEnum.MANUAL);
    }


    /**
     * 请求work 执行调度任务/恢复任务
     *
     * @param context    MasterContext
     * @param workHolder MasterWorkHolder
     * @param actionId   String
     * @return Future
     */
    private Future<Response> executeScheduleJob(MasterContext context, MasterWorkHolder workHolder, Long actionId) {
        workHolder.getRunning().add(actionId);
        return buildFuture(context, Request.newBuilder()
                .setRid(AtomicIncrease.getAndIncrement())
                .setOperate(Operate.Schedule)
                .setBody(ExecuteMessage
                        .newBuilder()
                        .setActionId(String.valueOf(actionId))
                        .build().toByteString())
                .build(), workHolder, actionId, TriggerTypeEnum.SCHEDULE);

    }


    /**
     * 请求work 执行开发中心任务
     *
     * @param context    MasterContext
     * @param workHolder MasterWorkHolder
     * @param id         String
     * @return Future
     */
    private Future<Response> executeDebugJob(MasterContext context, MasterWorkHolder workHolder, Long id) {
        workHolder.getDebugRunning().add(id);
        return buildFuture(context, Request.newBuilder()
                .setRid(AtomicIncrease.getAndIncrement())
                .setOperate(Operate.Debug)
                .setBody(DebugMessage
                        .newBuilder()
                        .setDebugId(String.valueOf(id))
                        .build().toByteString())
                .build(), workHolder, id, TriggerTypeEnum.DEBUG);

    }

    /**
     * 向work发送执行任务的命令 并等待work返回结果
     *
     * @param context  MasterContext
     * @param request  Request
     * @param holder   MasterWorkHolder
     * @param actionId String
     * @param typeEnum TriggerTypeEnum
     * @return Future
     */

    private Future<Response> buildFuture(MasterContext context, Request request, MasterWorkHolder holder, Long actionId, TriggerTypeEnum typeEnum) {
        final CountDownLatch latch = new CountDownLatch(1);
        MasterResponseListener responseListener = new MasterResponseListener(request, false, latch, null);
        context.getHandler().addListener(responseListener);
        Future<Response> future = context.getThreadPool().submit(() -> {
            try {
                latch.await(HeraGlobalEnv.getTaskTimeout(), TimeUnit.HOURS);
                if (!responseListener.getReceiveResult()) {
                    ErrorLog.error("任务({})信号丢失，{}小时未收到work返回：{}", typeEnum.toName(), HeraGlobalEnv.getTaskTimeout(), actionId);
                }
            } finally {
                context.getHandler().removeListener(responseListener);
                switch (typeEnum) {
                    case MANUAL:
                        holder.getManningRunning().remove(actionId);
                        break;
                    case SCHEDULE:
                        holder.getRunning().remove(actionId);
                        break;
                    case MANUAL_RECOVER:
                        holder.getRunning().remove(actionId);
                        break;
                    case DEBUG:
                        holder.getDebugRunning().remove(actionId);
                        break;
                    default:
                        ErrorLog.warn("未识别的任务执行类型{}", typeEnum);
                }
            }
            return responseListener.getResponse();
        });
        try {
            holder.getChannel().writeAndFlush(SocketMessage
                    .newBuilder()
                    .setKind(SocketMessage.Kind.REQUEST)
                    .setBody(request.toByteString())
                    .build());
            TaskLog.info("5.MasterExecuteJob:master send debug command to worker,rid = " + request.getRid() + ",actionId = " + actionId + ",address " + holder.getChannel().getRemoteAddress());
        } catch (RemotingException e) {
            context.getHandler().removeListener(responseListener);
            ErrorLog.error("5.MasterExecuteJob:master send debug command to worker exception,rid = " + request.getRid() + ",actionId = " + actionId + ",address " + holder.getChannel().getRemoteAddress(), e);
        }
        return future;

    }
}
