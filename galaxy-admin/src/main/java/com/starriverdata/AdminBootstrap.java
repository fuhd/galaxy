package com.starriverdata;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@MapperScan(basePackages = "com.starriverdata.*.mapper")
@SpringBootApplication(scanBasePackages = "com.starriverdata")
@ServletComponentScan(value = "com.starriverdata.config")
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
public class AdminBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(AdminBootstrap.class, args);
    }
}
