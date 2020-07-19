package com.starriverdata.core.netty.listener;

import com.starriverdata.core.netty.listener.adapter.ResponseListenerAdapter;
import com.starriverdata.logs.ErrorLog;
import com.starriverdata.protocol.RpcRequest;
import com.starriverdata.protocol.RpcResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.concurrent.CountDownLatch;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MasterResponseListener extends ResponseListenerAdapter {

    private RpcRequest.Request request;
    private volatile Boolean receiveResult;
    private CountDownLatch latch;
    private RpcResponse.Response response;

    @Override
    public void onResponse(RpcResponse.Response response) {
        if (response.getRid() == request.getRid()) {
            try {
                this.response = response;
                receiveResult = true;
            } catch (Exception e) {
                ErrorLog.error("release lock exception {}", e);
            } finally {
                latch.countDown();
            }
        }
    }

}
