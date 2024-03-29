package com.starriverdata.core.event.listenter;

import com.starriverdata.common.util.NamedThreadFactory;
import com.starriverdata.core.event.Dispatcher;
import com.starriverdata.core.event.base.MvcEvent;
import com.starriverdata.event.EventType;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public abstract class AbstractListener implements Listener<MvcEvent> {

    private Executor executor;

    @Override
    public void handleEvent(MvcEvent event) {
        EventType eventType = event.getType();
        if (eventType == Dispatcher.beforeDispatch) {
            beforeDispatch(event);
        } else if (eventType == Dispatcher.afterDispatch) {
            afterDispatch(event);
        }

    }

    public void beforeDispatch(MvcEvent mvcEvent) {

    }

    public void afterDispatch(MvcEvent mvcEvent) {

    }

    public Executor getSinglePool() {
        if (executor == null) {
            synchronized (this) {
                if (executor == null) {
                    executor = new ThreadPoolExecutor(
                            1, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(Integer.MAX_VALUE), new NamedThreadFactory("listener-thread-pool", true), new ThreadPoolExecutor.AbortPolicy());
                }
            }
        }
        return executor;
    }
}
