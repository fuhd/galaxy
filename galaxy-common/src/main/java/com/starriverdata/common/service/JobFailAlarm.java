package com.starriverdata.common.service;

import com.starriverdata.common.entity.HeraSso;
import com.starriverdata.common.vo.JobElement;
import com.starriverdata.event.HeraJobFailedEvent;

import java.util.Set;

public interface JobFailAlarm {


    /**
     * 任务失败告警接口，自己可以自定义实现 默认:com.dfire.monitor.service.impl.EmailJobFailAlarm
     * 一定要把实现类使用spring管理
     */
    void alarm(HeraJobFailedEvent failedEvent, Set<HeraSso> monitorUser);

    /**
     * 任务执行超时告警
     */
    void alarm(JobElement element, Set<HeraSso> monitorUser);
}
