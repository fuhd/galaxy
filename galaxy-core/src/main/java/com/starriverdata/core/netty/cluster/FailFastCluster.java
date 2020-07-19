package com.starriverdata.core.netty.cluster;

import com.starriverdata.core.exception.RemotingException;
import com.starriverdata.core.netty.HeraChannel;
import com.starriverdata.core.netty.NettyChannel;
import com.starriverdata.protocol.RpcSocketMessage;
import io.netty.channel.Channel;

/**
 * 快速失败集群容错 比如心跳
 */
public class FailFastCluster extends AbstractCluster {

    public static HeraChannel wrap(HeraChannel channel) {
        return new FailFastCluster(channel);
    }

    public static HeraChannel wrap(Channel channel) {
        return wrap(new NettyChannel(channel));
    }

    private FailFastCluster(HeraChannel channel) {
        super(channel);
    }

    @Override
    public void writeAndFlush(RpcSocketMessage.SocketMessage msg) throws RemotingException {
        channel.writeAndFlush(msg);
    }
}
