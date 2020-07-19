package com.starriverdata.core.netty.master.schedule;

import com.starriverdata.config.HeraGlobalEnv;
import com.starriverdata.core.netty.ScheduledChore;
import com.starriverdata.core.netty.master.Master;
import com.starriverdata.logs.ScanLog;

import java.util.concurrent.TimeUnit;

/**
 * 任务队列扫描
 */
public class JobQueueScan extends ScheduledChore {

    /**
     * scan频率递增的步长
     */
    private final Integer DELAY_TIME = 100;
    /**
     * 最大scan频率
     */
    private final Integer MAX_DELAY_TIME = 10 * 1000;
    private Integer nextTime = HeraGlobalEnv.getScanRate();
    private Master master;


    private JobQueueScan(Master master, long initialDelay, long period, TimeUnit unit) {
        super("JobQueueScan", initialDelay, period, unit);
        this.master = master;
    }

    public JobQueueScan(Master master) {
        this(master, 60, HeraGlobalEnv.getScanRate(), TimeUnit.MILLISECONDS);
    }

    @Override
    protected void chore() {
        try {
            if (!master.isTaskLimit() && master.scan()) {
                nextTime = HeraGlobalEnv.getScanRate();
            } else {
                nextTime = (nextTime + DELAY_TIME) > MAX_DELAY_TIME ? MAX_DELAY_TIME : nextTime + DELAY_TIME;
            }
            this.setInitialDelay(nextTime);
        } catch (Exception e) {
            ScanLog.error("scan waiting queueTask exception", e);
        } finally {
            choreService.scheduledChoreOnce(this);
        }
    }
}
