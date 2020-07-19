package com.starriverdata.core.netty.master.response;

import com.alibaba.fastjson.JSONObject;
import com.starriverdata.core.message.HeartBeatInfo;
import com.starriverdata.core.netty.master.MasterContext;
import com.starriverdata.core.netty.master.MasterWorkHolder;
import com.starriverdata.logs.ErrorLog;
import com.starriverdata.logs.HeartLog;
import com.starriverdata.logs.HeraLog;
import com.starriverdata.protocol.RpcHeartBeatMessage.HeartBeatMessage;
import com.starriverdata.protocol.RpcRequest.Request;
import com.starriverdata.protocol.RpcWorkInfo.WorkInfo;
import com.google.protobuf.InvalidProtocolBufferException;
import io.netty.channel.Channel;

public class MasterHandleRequest {

    public static void handleHeartBeat(MasterContext masterContext, Channel channel, Request request) {
        MasterWorkHolder workHolder = masterContext.getWorkMap().get(channel);
        HeartBeatInfo heartBeatInfo = new HeartBeatInfo();
        HeartBeatMessage heartBeatMessage;
        try {
            heartBeatMessage = HeartBeatMessage.newBuilder().mergeFrom(request.getBody()).build();
            heartBeatInfo.setHost(heartBeatMessage.getHost());
            heartBeatInfo.setMemRate(heartBeatMessage.getMemRate());
            heartBeatInfo.setMemTotal(heartBeatMessage.getMemTotal());
            heartBeatInfo.setCpuLoadPerCore(heartBeatMessage.getCpuLoadPerCore());
            heartBeatInfo.setRunning(heartBeatMessage.getRunningsList());
            heartBeatInfo.setDebugRunning(heartBeatMessage.getDebugRunningsList());
            heartBeatInfo.setManualRunning(heartBeatMessage.getManualRunningsList());
            heartBeatInfo.setTimestamp(heartBeatMessage.getTimestamp());
            heartBeatInfo.setCores(heartBeatMessage.getCores());
            workHolder.setHeartBeatInfo(heartBeatInfo);
            HeartLog.debug("received heart beat from {} : {}", heartBeatMessage.getHost(), JSONObject.toJSONString(heartBeatInfo));
        } catch (InvalidProtocolBufferException e) {
            ErrorLog.error("解析消息异常", e);
        }
    }

    public static void setWorkInfo(MasterContext masterContext, Channel channel, Request request) {
        MasterWorkHolder workHolder = masterContext.getWorkMap().get(channel);
        try {
            WorkInfo workInfo = WorkInfo.parseFrom(request.getBody());
            workHolder.setWorkInfo(workInfo);
            HeraLog.info("set workInfo success,{}", channel.remoteAddress());
        } catch (InvalidProtocolBufferException e) {
            ErrorLog.error("解析消息异常", e);
        }
    }
}
