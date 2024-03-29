package com.starriverdata.core.quartz;

import com.starriverdata.common.constants.Constants;
import com.starriverdata.logs.ErrorLog;
import com.starriverdata.logs.GalaxyLog;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.Properties;

/**
 * quartz调度器初始化
 */
@Configuration
@Service("quartzSchedulerService")
public class QuartzSchedulerService {

    private Scheduler scheduler;

    /**
     * 设置quartz配置: @Constructor 先于  @PostConstruct执行
     */
    //@Constructor
    public Properties setQuartzProperties() {
        GalaxyLog.info("start init quartz properties");
        Properties prop = new Properties();
        prop.put("org.quartz.scheduler.instanceName", "heraQuartzSchedulerPool");
        prop.put("org.quartz.scheduler.rmi.export", "false");
        prop.put("org.quartz.scheduler.rmi.proxy", "false");
        prop.put("org.quartz.scheduler.wrapJobExecutionInUserTransaction", "false");
        prop.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        prop.put("org.quartz.threadPool.threadCount", String.valueOf(Constants.AVAILABLE_CORES));
        prop.put("org.quartz.threadPool.threadPriority", "5");
        prop.put("org.quartz.threadPool.threadsInheritContextClassLoaderOfInitializingThread", "true");
        prop.put("org.quartz.jobStore.misfireThreshold", "60000");
        prop.put("org.quartz.jobStore.class", "org.quartz.simpl.RAMJobStore");
        return prop;
    }

    public void start() {
        GalaxyLog.info("start init quartz schedule");
        try {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory(setQuartzProperties());
            scheduler = schedulerFactory.getScheduler();
            scheduler.start();
            GalaxyLog.info("start init quartz scheduler");
        } catch (SchedulerException e) {
            ErrorLog.error("failed init quartz scheduler", e);
        }
    }

    public void shutdown() {
        if (scheduler != null) {
            try {
                scheduler.shutdown();
                GalaxyLog.info("worker shutdown quartz service");
            } catch (SchedulerException e) {
                ErrorLog.error("failed shutdown quartz scheduler", e);
            }
        }
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void deleteJob(Long actionId) {
        try {
            JobKey jobKey = new JobKey(String.valueOf(actionId), Constants.HERA_GROUP);
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            if (jobDetail != null) {
                scheduler.deleteJob(jobKey);
                GalaxyLog.warn("remove action {} from quartz", actionId);
            }
        } catch (SchedulerException e) {
            ErrorLog.error("remove quartz schedule error : " + actionId, e);
        }
    }
}
