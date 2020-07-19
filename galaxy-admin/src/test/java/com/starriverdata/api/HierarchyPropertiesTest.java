package com.starriverdata.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ComponentScan(basePackages = "com.starriverdata")
@MapperScan(basePackages = "com.starriverdata.common.mapper")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = HierarchyPropertiesTest.class)
public class HierarchyPropertiesTest {

    @Test
    public void groupBeanTest() {

    }

    @Test
    public void buildGlobalGraphTest() {
    }


}
