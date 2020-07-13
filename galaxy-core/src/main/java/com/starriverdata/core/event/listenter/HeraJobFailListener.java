package com.starriverdata.core.event.listenter;

import com.starriverdata.common.config.ServiceLoader;
import com.starriverdata.common.constants.Constants;
import com.starriverdata.common.entity.HeraJob;
import com.starriverdata.common.entity.HeraJobMonitor;
import com.starriverdata.common.entity.HeraSso;
import com.starriverdata.common.enums.TriggerTypeEnum;
import com.starriverdata.common.service.HeraJobMonitorService;
import com.starriverdata.common.service.HeraJobService;
import com.starriverdata.common.service.HeraSsoService;
import com.starriverdata.common.service.JobFailAlarm;
import com.starriverdata.common.util.ActionUtil;
import com.starriverdata.config.HeraGlobalEnv;
import com.starriverdata.core.event.base.MvcEvent;
import com.starriverdata.core.netty.master.MasterContext;
import com.starriverdata.event.HeraJobFailedEvent;
import com.starriverdata.logs.ErrorLog;
import com.starriverdata.logs.MonitorLog;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 任务失败的预处理
 *
 * @author xiaosuda
 */
public class HeraJobFailListener extends AbstractListener {


    private List<JobFailAlarm> alarms;

    private HeraJobMonitorService jobMonitorService;

    private HeraSsoService heraSsoService;

    private HeraJobService heraJobService;

    public HeraJobFailListener(MasterContext masterContext) {
        alarms = ServiceLoader.getAlarms();
        jobMonitorService = masterContext.getHeraJobMonitorService();
        heraSsoService = masterContext.getHeraSsoService();
        heraJobService = masterContext.getHeraJobService();
    }

    @Override
    public void beforeDispatch(MvcEvent mvcEvent) {
        if (mvcEvent.getApplicationEvent() instanceof HeraJobFailedEvent) {
            if (HeraGlobalEnv.getAlarmEnvSet().contains(HeraGlobalEnv.getEnv())) {
                HeraJobFailedEvent failedEvent = (HeraJobFailedEvent) mvcEvent.getApplicationEvent();
                if (failedEvent.getTriggerType() == TriggerTypeEnum.MANUAL || failedEvent.getTriggerType() == TriggerTypeEnum.DEBUG) {
                    return;
                }
                //重跑次数未达到重试次数
                if (failedEvent.getRunCount() <= failedEvent.getRetryCount()) {
                    return;
                }
                super.getSinglePool().execute(() -> {
                    Integer jobId = ActionUtil.getJobId(failedEvent.getActionId());
                    if (jobId == null) {
                        return;
                    }
                    HeraJob heraJob = heraJobService.findById(jobId);
                    if (heraJob == null) {
                        MonitorLog.warn("版本为{}的任务{}被删除", failedEvent.getActionId(), jobId);
                        return;
                    }
                    failedEvent.setHeraJob(heraJob);
                    Set<HeraSso> monitorUser = new HashSet<>();
                    Optional.ofNullable(jobMonitorService.findByJobId(jobId))
                            .map(HeraJobMonitor::getUserIds)
                            .ifPresent(ids -> Arrays.stream(ids
                                    .split(Constants.COMMA))
                                    .filter(StringUtils::isNotBlank)
                                    .forEach(id -> {
                                        Optional.ofNullable(heraSsoService.findSsoById(Integer.parseInt(id))).ifPresent(monitorUser::add);
                                    }));
                    for (JobFailAlarm failAlarm : alarms) {
                        try {
                            failAlarm.alarm(failedEvent, monitorUser);
                        } catch (Exception e) {
                            ErrorLog.error("任务失败的通知事件异常:" + e.getMessage(), e);
                        }
                    }
                });
            }
        }
    }
}
