package com.starriverdata.common.service.impl;

import com.starriverdata.common.entity.HeraAdvice;
import com.starriverdata.common.mapper.HeraAdviceMapper;
import com.starriverdata.common.service.HeraAdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaosuda
 * @date 2018/12/5
 */
@Service("heraAdviceService")
public class HeraHeraAdviceServiceImpl implements HeraAdviceService {


    @Autowired
    private HeraAdviceMapper heraAdviceMapper;

    @Override
    public boolean addAdvice(HeraAdvice heraAdvice) {
        Integer res = heraAdviceMapper.insert(heraAdvice);
        return res != null && res > 0;
    }

    @Override
    public List<HeraAdvice> getAll() {
        return heraAdviceMapper.getAll();
    }
}
