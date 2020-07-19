package com.starriverdata.core.netty.cluster;

import com.starriverdata.core.netty.HeraChannel;
import io.netty.channel.Channel;

import java.net.SocketAddress;

public abstract class AbstractCluster implements Cluster {

    protected HeraChannel channel;

    public AbstractCluster(HeraChannel channel) {
        this.channel = channel;
    }

    @Override
    public SocketAddress getRemoteAddress() {
        return channel.getRemoteAddress();
    }

    @Override
    public SocketAddress getLocalAddress() {
        return channel.getLocalAddress();
    }

    @Override
    public Channel getChannel() {
        return channel.getChannel();
    }

    @Override
    public void close() {
        channel.close();
    }


    @Override
    public String toString() {
        return "NettyChannel{" +
                "channel=" + channel +
                '}';
    }
}
