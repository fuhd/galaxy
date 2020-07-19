package com.starriverdata.core.exception;

import com.starriverdata.core.netty.HeraChannel;

import java.net.SocketAddress;

public class RemotingException extends Exception {

    private SocketAddress localAddress;

    private SocketAddress remoteAddress;

    public RemotingException(HeraChannel channel, String message, Throwable cause) {
        this(channel == null ? null : channel.getLocalAddress(), channel == null ? null : channel.getRemoteAddress(),
                message, cause);
    }

    public RemotingException(HeraChannel channel, String msg) {
        this(channel == null ? null : channel.getLocalAddress(), channel == null ? null : channel.getRemoteAddress(),
                msg);
    }

    public RemotingException(SocketAddress localAddress, SocketAddress remoteAddress, String message) {
        super(message);

        this.localAddress = localAddress;
        this.remoteAddress = remoteAddress;
    }

    public RemotingException(SocketAddress localAddress, SocketAddress remoteAddress, String message,
                             Throwable cause) {
        super(message, cause);

        this.localAddress = localAddress;
        this.remoteAddress = remoteAddress;
    }

    public SocketAddress getLocalAddress() {
        return localAddress;
    }

    public SocketAddress getRemoteAddress() {
        return remoteAddress;
    }
}
