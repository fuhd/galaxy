package com.starriverdata;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


/**
 * @author: <a href="mailto:lingxiao@2dfire.com">凌霄</a>
 * @time: Created in 11:59 2018/1/1
 * 启动入口
 */
@MapperScan(basePackages = "com.starriverdata.*.mapper")
@SpringBootApplication(scanBasePackages = "com.starriverdata")
@ServletComponentScan(value = "com.starriverdata.config")
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
public class AdminBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(AdminBootstrap.class, args);
    }
}