package com.starriverdata.monitor;

import com.starriverdata.common.service.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.MessagingException;

@ComponentScan(basePackages = "com.starriverdata")
@MapperScan(basePackages = "com.starriverdata.common.mapper")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EmailTest.class)
public class EmailTest {

    @Autowired
    EmailService emailService;

    @Test
    public void sendEmail() {
        try {
            emailService.sendEmail("test1", "content1", "xiaosuda@2dfire.com");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
