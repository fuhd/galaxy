package com.starriverdata.core.schedule;

import com.starriverdata.core.netty.master.MasterContext;
import com.starriverdata.logs.GalaxyLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class HeraSchedule {

    private AtomicBoolean running = new AtomicBoolean(false);

    @Autowired
    private MasterContext masterContext;

    public void startup() {
        if (!running.compareAndSet(false, true)) {
            return;
        }
        GalaxyLog.info("begin to start master context");
        masterContext.init();
    }

    public void shutdown() {
        if (running.compareAndSet(true, false)) {
            masterContext.destroy();
        }
    }
}
