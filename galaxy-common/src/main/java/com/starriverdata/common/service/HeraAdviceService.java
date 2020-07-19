package com.starriverdata.common.service;

import com.starriverdata.common.entity.HeraAdvice;

import java.util.List;

public interface HeraAdviceService {

    boolean addAdvice(HeraAdvice heraAdvice);

    List<HeraAdvice> getAll();
}
