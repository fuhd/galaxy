package com.starriverdata.monitor.service.impl;

import com.starriverdata.common.service.EmailService;
import com.starriverdata.logs.ErrorLog;
import com.starriverdata.logs.MonitorLog;
import com.starriverdata.monitor.domain.AlarmInfo;
import com.starriverdata.monitor.service.AlarmCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class AlarmCenterImpl implements AlarmCenter {

    @Autowired
    private EmailService emailService;

    @Override
    public boolean sendToWeChat(AlarmInfo alarmInfo) {
        //TODO 使用者自己开发
        MonitorLog.info("[微信告警]userInfo:{},消息结果:{}", alarmInfo.toString(), "请管理员自己设置企业微信告警配置");
        return false;
    }

    @Override
    public boolean sendToPhone(AlarmInfo alarmInfo) {
        //TODO 使用者自己开发
        MonitorLog.info("[电话告警]userInfo:{},消息结果:{}", alarmInfo.toString(), "请管理员自己设置企业微信告警配置");
        return false;
    }

    @Override
    public boolean sendToEmail(String title, String content, String address) {
        try {
            emailService.sendEmail(title, content, address);
            return true;
        } catch (MessagingException e) {
            ErrorLog.error("发送邮件[title:" + title + ",content:" + content + "]失败", e);

        }
        return false;
    }
}
