package com.starriverdata.common.exception;

import com.starriverdata.logs.ErrorLog;

public class HeraCaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        ErrorLog.error("Thread pool caught thread exception " + e);
        throw new RuntimeException(e);
    }
}
