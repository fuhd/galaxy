package com.starriverdata.common.util;

import com.alibaba.fastjson.JSONArray;
import com.starriverdata.common.entity.HeraJob;
import com.starriverdata.common.entity.vo.HeraJobVo;
import com.starriverdata.common.processor.JobProcessor;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanConvertUtilsTest {

    @Test
    public void convertToHeraJob() {
        HeraJobVo heraJobVo = new HeraJobVo();
        HeraJob heraJob = BeanConvertUtils.convertToHeraJob(heraJobVo);

        System.out.println(heraJob.getResources());
    }

    @Test
    public void stringToMap() {
        String x = "a=b";
        Map<String,String> res = new HashMap<>();

        BeanConvertUtils.stringToMap(x, res);
        Assert.assertTrue(res.size() == 1);
    }
    @Test
    public void jsonTest() {
        String preProcessor = "[]";
        if (preProcessor != null
                && !"".equals(preProcessor)) {
            JSONArray preArray = JSONArray.parseArray(preProcessor);
            List<JobProcessor> preProcessers = new ArrayList<>();
            System.out.println(preArray.size());

        }
    }
}