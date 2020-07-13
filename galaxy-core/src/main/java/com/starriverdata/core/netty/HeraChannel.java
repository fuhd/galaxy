package com.starriverdata.core.netty;

import com.starriverdata.core.exception.RemotingException;
import com.starriverdata.protocol.RpcSocketMessage;
import io.netty.channel.Channel;

import java.net.SocketAddress;

/**
 * @author xiaosuda
 * @date 2018/11/16
 */
public interface HeraChannel {


    void writeAndFlush(RpcSocketMessage.SocketMessage msg) throws RemotingException;

    SocketAddress getRemoteAddress();

    SocketAddress getLocalAddress();

    Channel getChannel();

    void close();
}
