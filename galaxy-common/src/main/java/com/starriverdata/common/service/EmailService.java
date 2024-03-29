package com.starriverdata.common.service;

import javax.mail.MessagingException;

public interface EmailService {

    /**
     * 发送邮件
     * @param title   邮件标题
     * @param content 邮件内容
     * @param address 收件人，多个用,隔开
     * @throws MessagingException
     */
    void sendEmail(String title, String content, String address) throws MessagingException;
}
