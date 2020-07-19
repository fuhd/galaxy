package com.starriverdata.core.netty.worker.request;

import com.starriverdata.core.exception.RemotingException;
import com.starriverdata.core.netty.HeraChannel;
import com.starriverdata.core.tool.OsProcessJob;
import com.starriverdata.logs.ErrorLog;
import com.starriverdata.protocol.RpcOperate;
import com.starriverdata.protocol.RpcRequest.Request;
import com.starriverdata.protocol.RpcSocketMessage.SocketMessage;
import com.starriverdata.protocol.RpcWorkInfo.WorkInfo;

public class WorkHandlerRequest {

    public void getWorkInfo(HeraChannel channel) {
        OsProcessJob processJob = new OsProcessJob();
        Integer exitCode = processJob.run();
        if (exitCode == 0) {
            try {
                channel.writeAndFlush(
                        SocketMessage.newBuilder()
                                .setKind(SocketMessage.Kind.REQUEST)
                                .setBody(Request.newBuilder()
                                        .setBody(WorkInfo.newBuilder()
                                                .setOSInfo(processJob.getOsInfo())
                                                .addAllProcessMonitor(processJob.getProcessMonitors())
                                                .build().toByteString())
                                        .setOperate(RpcOperate.Operate.SetWorkInfo)
                                        .build()
                                        .toByteString())
                                .build());
            } catch (RemotingException e) {
                ErrorLog.error("发送消息失败", e);
            }
        }

    }
}
