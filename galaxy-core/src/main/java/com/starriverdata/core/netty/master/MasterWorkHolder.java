package com.starriverdata.core.netty.master;

import com.starriverdata.core.message.HeartBeatInfo;
import com.starriverdata.core.netty.HeraChannel;
import com.starriverdata.protocol.RpcWorkInfo.WorkInfo;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class MasterWorkHolder {

    private HeraChannel channel;

    /**
     * 存放的actionId
     */
    private Set<Long> running = new HashSet<>();
    /**
     * 存放的actionId
     */
    private Set<Long> manningRunning = new HashSet<>();
    /**
     * 存放的debugId
     */
    private Set<Long> debugRunning = new HashSet<>();

    private HeartBeatInfo heartBeatInfo;

    private volatile WorkInfo workInfo;

    public MasterWorkHolder(HeraChannel channel) {
        this.channel = channel;
    }

}
