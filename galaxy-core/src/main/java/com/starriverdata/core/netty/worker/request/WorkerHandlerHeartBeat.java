package com.starriverdata.core.netty.worker.request;

import com.starriverdata.common.exception.HeraException;
import com.starriverdata.core.exception.RemotingException;
import com.starriverdata.core.netty.util.AtomicIncrease;
import com.starriverdata.core.netty.worker.HistoryPair;
import com.starriverdata.core.netty.worker.WorkContext;
import com.starriverdata.core.tool.CpuLoadPerCoreJob;
import com.starriverdata.core.tool.MemUseRateJob;
import com.starriverdata.protocol.RpcHeartBeatMessage;
import com.starriverdata.protocol.RpcOperate;
import com.starriverdata.protocol.RpcRequest;
import com.starriverdata.protocol.RpcSocketMessage;

import java.util.stream.Collectors;

public class WorkerHandlerHeartBeat {

    public boolean send(WorkContext context) throws HeraException {
        try {
            MemUseRateJob memUseRateJob = new MemUseRateJob(1);
            memUseRateJob.readMemUsed();
            CpuLoadPerCoreJob loadPerCoreJob = new CpuLoadPerCoreJob();
            loadPerCoreJob.run();

            RpcHeartBeatMessage.HeartBeatMessage hbm = RpcHeartBeatMessage.HeartBeatMessage.newBuilder()
                    .setHost(WorkContext.host)
                    .setMemTotal(memUseRateJob.getMemTotal())
                    .setMemRate(memUseRateJob.getRate())
                    .setCpuLoadPerCore(loadPerCoreJob.getLoadPerCore())
                    .setTimestamp(System.currentTimeMillis())
                    .addAllDebugRunnings(context.getDebugRunning().keySet().stream().map(String::valueOf).collect(Collectors.toList()))
                    .addAllManualRunnings(context.getManualRunning().keySet().stream().map(HistoryPair::getActionId).map(String::valueOf).collect(Collectors.toList()))
                    .addAllRunnings(context.getRunning().keySet().stream().map(HistoryPair::getActionId).map(String::valueOf).collect(Collectors.toList()))
                    .setCores(WorkContext.cpuCoreNum)
                    .build();
            context.getServerChannel().writeAndFlush(RpcSocketMessage.SocketMessage.newBuilder().
                    setKind(RpcSocketMessage.SocketMessage.Kind.REQUEST).
                    setBody(RpcRequest.Request.newBuilder().
                            setRid(AtomicIncrease.getAndIncrement()).
                            setOperate(RpcOperate.Operate.HeartBeat).
                            setBody(hbm.toByteString()).
                            build().toByteString()).
                    build());
        } catch (RemotingException e) {
            throw new HeraException("发送心跳消息失败", e);
        }
        return true;
    }

}
