package com.starriverdata.common.service;

import com.starriverdata.common.entity.HeraJobMonitor;
import com.starriverdata.common.entity.vo.HeraJobMonitorVo;

import java.util.List;

public interface HeraJobMonitorService {

    boolean addMonitor(String userId, Integer jobId);

    boolean removeMonitor(String userId, Integer jobId);

    HeraJobMonitor findByJobId(Integer jobId);

    List<HeraJobMonitor> findAll();

    List<HeraJobMonitorVo> findAllVo();

    boolean updateMonitor(String userIds, Integer jobId);
}
