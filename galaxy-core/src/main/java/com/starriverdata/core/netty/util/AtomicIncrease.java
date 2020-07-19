package com.starriverdata.core.netty.util;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * 生成全局唯一id, 标志所有的netty层request与之对应的response,
 * 当request.id == response.id,标志一次请求结束，可解除对request的response的监听ResponseListener,标志一次请求结束
 */
public class AtomicIncrease {

    private static AtomicInteger rid = new AtomicInteger();

    public static int getAndIncrement() {

        return rid.getAndIncrement();
    }
}
