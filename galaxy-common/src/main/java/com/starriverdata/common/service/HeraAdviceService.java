package com.starriverdata.common.service;

import com.starriverdata.common.entity.HeraAdvice;

import java.util.List;

/**
 * @author xiaosuda
 * @date 2018/12/5
 */
public interface HeraAdviceService {

    boolean addAdvice(HeraAdvice heraAdvice);

    List<HeraAdvice> getAll();
}
