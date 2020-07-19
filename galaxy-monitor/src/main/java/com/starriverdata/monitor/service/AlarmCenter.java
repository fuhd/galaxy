package com.starriverdata.monitor.service;

import com.starriverdata.monitor.domain.AlarmInfo;

public interface AlarmCenter {

    /**
     * 发送监控信息到企业微信
     */
    boolean sendToWeChat(AlarmInfo alarmInfo);

    /**
     * 发送监控信息到手机
     */
    boolean sendToPhone(AlarmInfo alarmInfo);

    /**
     * 发送信息到邮件
     */
    boolean sendToEmail(String title, String content, String address);

}
