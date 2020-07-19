package com.starriverdata.core.netty.listener.adapter;

import com.starriverdata.core.netty.listener.ResponseListener;
import com.starriverdata.protocol.RpcResponse;
import com.starriverdata.protocol.RpcWebResponse;
/**
 * 接口适配
 */
public class ResponseListenerAdapter extends ResponseListener {

    @Override
    public void onResponse(RpcResponse.Response response) {

    }

    @Override
    public void onWebResponse(RpcWebResponse.WebResponse webResponse) {

    }
}
