package com.starriverdata.monitor.service.impl;

import com.starriverdata.common.config.Alarm;
import com.starriverdata.common.entity.HeraJob;
import com.starriverdata.common.entity.HeraSso;
import com.starriverdata.common.enums.AlarmLevel;
import com.starriverdata.common.vo.JobElement;
import com.starriverdata.event.HeraJobFailedEvent;
import com.starriverdata.monitor.domain.AlarmInfo;
import com.starriverdata.monitor.service.AlarmCenter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.Set;

/**
 * 电话告警 使用者自己实现
 *
 * @author xiaosuda
 * @date 2019/3/6
 */
@Alarm
public class PhoneJobFailAlarm extends AbstractJobFailAlarm {

    @Autowired
    private AlarmCenter alarmCenter;

    @Override
    public void alarm(HeraJobFailedEvent failedEvent, Set<HeraSso> monitorUser) {
        HeraJob heraJob = failedEvent.getHeraJob();
        //低于电话等级直接跳过
        if (AlarmLevel.lessThan(heraJob.getOffset(), AlarmLevel.PHONE) || heraJob.getAuto() != 1) {
            return;
        }
        AlarmInfo alarmInfo = new AlarmInfo();
        alarmInfo.setMessage(buildJobErrorMsg(heraJob, failedEvent.getRunCount(), monitorUser));
        Optional.ofNullable(monitorUser).ifPresent(users ->
                users.forEach(user -> {
                    alarmInfo.setPhone(user.getPhone());
                    alarmCenter.sendToPhone(alarmInfo);
                }));
    }

    @Override
    public void alarm(JobElement element, Set<HeraSso> monitorUser) {

    }
}
