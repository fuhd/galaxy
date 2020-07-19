package com.starriverdata.core.netty.listener;

import com.starriverdata.protocol.RpcResponse;
import com.starriverdata.protocol.RpcWebResponse;

/**
 * web请求在netty中的handler处理响应监听
 */
public abstract class ResponseListener {

    /**
     * 自动调度层消息response
     */
    public abstract void onResponse(RpcResponse.Response response);

    /**
     * 页面任务执行请求response
     */
    public abstract void onWebResponse(RpcWebResponse.WebResponse webResponse);
}