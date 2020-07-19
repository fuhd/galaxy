package com.starriverdata.core.quartz;

import org.junit.Test;
import org.quartz.*;

import java.io.IOException;

public class QuartzSchedulerServiceTest {

    @Test
    public void start() throws IOException, SchedulerException, InterruptedException {

        QuartzSchedulerService quartzSchedulerService = new QuartzSchedulerService();
        quartzSchedulerService.start();

        JobDetail jobDetail = JobBuilder.newJob(HeraQuartzJob.class).withIdentity("hera").build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("hera").withSchedule(scheduleBuilder).build();
        quartzSchedulerService.getScheduler().scheduleJob(jobDetail, trigger);

        Thread.sleep(1000 * 100);
    }
}
