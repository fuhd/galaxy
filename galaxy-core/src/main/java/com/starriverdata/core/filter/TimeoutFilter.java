package com.starriverdata.core.filter;

import com.starriverdata.common.config.ExecuteFilter;
import com.starriverdata.common.config.Filter;
import com.starriverdata.common.config.ServiceLoader;
import com.starriverdata.common.constants.Constants;
import com.starriverdata.common.entity.HeraJobMonitor;
import com.starriverdata.common.entity.HeraSso;
import com.starriverdata.common.service.HeraJobMonitorService;
import com.starriverdata.common.service.HeraSsoService;
import com.starriverdata.common.service.JobFailAlarm;
import com.starriverdata.common.util.ActionUtil;
import com.starriverdata.common.util.NamedThreadFactory;
import com.starriverdata.common.vo.JobElement;
import com.starriverdata.config.HeraGlobalEnv;
import com.starriverdata.logs.ErrorLog;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Filter("timeoutFilter")
public class TimeoutFilter implements ExecuteFilter {

    @Autowired
    private HeraJobMonitorService jobMonitorService;

    @Autowired
    private HeraSsoService heraSsoService;

    private volatile Timer timeoutCheck;

    private ConcurrentHashMap<JobElement, Timeout> cache;

    @Override
    public void onExecute(JobElement jobElement) {
        if (jobElement.getCostMinute() == null || jobElement.getCostMinute() <= 0) {
            return;
        }
        if (timeoutCheck == null) {
            synchronized (this) {
                if (timeoutCheck == null) {
                    timeoutCheck = new HashedWheelTimer(
                            new NamedThreadFactory("timeout-check-timer", true),
                            1,
                            TimeUnit.SECONDS);
                    cache = new ConcurrentHashMap<>(HeraGlobalEnv.getMaxParallelNum());
                }
            }
        }
        cache.putIfAbsent(jobElement, timeoutCheck.newTimeout(timeout -> {
            List<JobFailAlarm> alarms = ServiceLoader.getAlarms();
            Set<HeraSso> monitorUser = getMonitorUser(ActionUtil.getJobId(jobElement.getJobId()));
            for (JobFailAlarm alarm : alarms) {
                try {
                    alarm.alarm(jobElement, monitorUser);
                } catch (Exception e) {
                    ErrorLog.error("告警失败:", e);
                }
            }
        }, jobElement.getCostMinute(), TimeUnit.MINUTES));
    }

    @Override
    public void onResponse(JobElement element) {
        if (cache != null) {
            Timeout remove = cache.remove(element);
            if (remove != null) {
                remove.cancel();
            }
        }
    }

    private Set<HeraSso> getMonitorUser(Integer jobId) {
        Set<HeraSso> monitorUser = new HashSet<>();
        Optional.ofNullable(jobMonitorService.findByJobId(jobId))
                .map(HeraJobMonitor::getUserIds)
                .ifPresent(ids -> Arrays.stream(ids
                        .split(Constants.COMMA))
                        .filter(StringUtils::isNotBlank)
                        .forEach(id -> {
                            Optional.ofNullable(heraSsoService.findSsoById(Integer.parseInt(id))).ifPresent(monitorUser::add);
                        }));
        return monitorUser;
    }
}
