package com.starriverdata.core.netty.worker.request;

import com.starriverdata.config.HeraGlobalEnv;
import com.starriverdata.core.exception.RemotingException;
import com.starriverdata.core.netty.listener.WorkResponseListener;
import com.starriverdata.core.netty.util.AtomicIncrease;
import com.starriverdata.core.netty.worker.WorkContext;
import com.starriverdata.logs.ErrorLog;
import com.starriverdata.logs.SocketLog;
import com.starriverdata.protocol.JobExecuteKind.ExecuteKind;
import com.starriverdata.protocol.RpcSocketMessage.SocketMessage;
import com.starriverdata.protocol.RpcWebOperate.WebOperate;
import com.starriverdata.protocol.RpcWebRequest.WebRequest;
import com.starriverdata.protocol.RpcWebResponse.WebResponse;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class WorkerHandleWebRequest {

    public static Future<WebResponse> handleWebExecute(final WorkContext workContext, ExecuteKind kind, Long id) {
        return buildMessage(WebRequest.newBuilder()
                .setRid(AtomicIncrease.getAndIncrement())
                .setOperate(WebOperate.ExecuteJob)
                .setEk(kind)
                .setId(String.valueOf(id))
                .build(), workContext, "[执行]-任务超出3小时未得到master消息返回:" + id);
    }

    public static Future<WebResponse> handleWebAction(final WorkContext workContext, ExecuteKind kind, Long id) {
        return buildMessage(WebRequest.newBuilder()
                .setRid(AtomicIncrease.getAndIncrement())
                .setOperate(WebOperate.GenerateAction)
                .setEk(kind)
                .setId(String.valueOf(id))
                .build(), workContext, "[更新]-action超出3小时未得到master消息返回:" + id);
    }

    public static Future<WebResponse> handleCancel(final WorkContext workContext, ExecuteKind kind, Long id) {
        return buildMessage(WebRequest.newBuilder()
                .setRid(AtomicIncrease.getAndIncrement())
                .setOperate(WebOperate.CancelJob)
                .setEk(kind)
                .setId(String.valueOf(id))
                .build(), workContext, "[取消]-任务超出3小时未得到master消息返回：" + id);
    }

    public static Future<WebResponse> handleUpdate(final WorkContext workContext, String jobId) {
        return buildMessage(WebRequest.newBuilder()
                .setRid(AtomicIncrease.getAndIncrement())
                .setOperate(WebOperate.UpdateJob)
                .setEk(ExecuteKind.ManualKind)
                .setId(jobId)
                .build(), workContext, "[更新]-job超出3小时未得到master消息返回：" + jobId);
    }

    public static Future<WebResponse> getJobQueueInfoFromMaster(WorkContext workContext) {
        return buildMessage(WebRequest.newBuilder()
                .setRid(AtomicIncrease.getAndIncrement())
                .setOperate(WebOperate.GetAllHeartBeatInfo)
                .build(), workContext, "三个小时未获得master任务队列的获取信息");
    }


    public static Future<WebResponse> getAllWorkInfoFromMaster(WorkContext workContext) {
        return buildMessage(WebRequest.newBuilder()
                .setRid(AtomicIncrease.getAndIncrement())
                .setOperate(WebOperate.GetAllWorkInfo)
                .build(), workContext, "三个小时未获得master所有work信息");
    }

    private static Future<WebResponse> buildMessage(WebRequest request, WorkContext workContext, String errorMsg) {
        CountDownLatch latch = new CountDownLatch(1);
        WorkResponseListener responseListener = new WorkResponseListener(request, false, latch, null);
        workContext.getHandler().addListener(responseListener);
        Future<WebResponse> future = workContext.getWorkWebThreadPool().submit(() -> {
            latch.await(HeraGlobalEnv.getRequestTimeout(), TimeUnit.SECONDS);
            if (!responseListener.getReceiveResult()) {
                ErrorLog.error(errorMsg);
            }
            workContext.getHandler().removeListener(responseListener);
            return responseListener.getWebResponse();
        });
        try {
            workContext.getServerChannel().writeAndFlush(SocketMessage.newBuilder()
                    .setKind(SocketMessage.Kind.WEB_REQUEST)
                    .setBody(request.toByteString())
                    .build());
            SocketLog.info("1.WorkerHandleWebRequest: send web request to master requestId ={}", request.getRid());
        } catch (RemotingException e) {
            workContext.getHandler().removeListener(responseListener);
            ErrorLog.error("1.WorkerHandleWebRequest: send web request to master exception requestId =" + request.getRid(), e);
        }
        return future;

    }

}
