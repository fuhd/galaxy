package com.starriverdata.common.service.impl;

import com.starriverdata.common.constants.Constants;
import com.starriverdata.common.entity.HeraJobMonitor;
import com.starriverdata.common.entity.vo.HeraJobMonitorVo;
import com.starriverdata.common.mapper.HeraJobMonitorMapper;
import com.starriverdata.common.service.HeraJobMonitorService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class HeraJobMonitorServiceImpl implements HeraJobMonitorService {

    @Autowired
    private HeraJobMonitorMapper heraJobMonitorMapper;

    @Override
    public boolean addMonitor(String userId, Integer jobId) {
        HeraJobMonitor res = heraJobMonitorMapper.findByJobId(jobId);
        HeraJobMonitor monitor = new HeraJobMonitor();
        monitor.setUserIds(userId.endsWith(Constants.COMMA) ? userId : userId + Constants.COMMA);
        monitor.setJobId(jobId);
        //插入
        if (res == null) {
            Integer insert = heraJobMonitorMapper.insert(monitor);
            return insert != null && insert > 0;
        } else { //更新
            Integer update = heraJobMonitorMapper.insertUser(monitor);
            return update != null && update > 0;
        }
    }

    @Override
    public boolean removeMonitor(String userId, Integer jobId) {
        HeraJobMonitor oldMonitor = heraJobMonitorMapper.findByJobId(jobId);
        StringBuilder newMonitor = new StringBuilder();
        Arrays.stream(oldMonitor.getUserIds().split(Constants.COMMA)).forEach(id -> {
            if (!id.equals(userId)) {
                newMonitor.append(id).append(Constants.COMMA);
            }
        });
        Integer res = heraJobMonitorMapper.update(jobId, newMonitor.toString());
        return res != null && res > 0;
    }

    @Override
    public HeraJobMonitor findByJobId(Integer jobId) {
        return heraJobMonitorMapper.findByJobId(jobId);
    }

    @Override
    public List<HeraJobMonitor> findAll() {
        return heraJobMonitorMapper.selectAll();
    }

    @Override
    public List<HeraJobMonitorVo> findAllVo() {
        return heraJobMonitorMapper.selectAllVo();
    }

    @Override
    public boolean updateMonitor(String userIds, Integer jobId) {
        if (StringUtils.isNotBlank(userIds)) {
            userIds = userIds.endsWith(Constants.COMMA) ? userIds : userIds + Constants.COMMA;
        }
        Integer update = heraJobMonitorMapper.update(jobId, userIds);
        return update != null && update > 0;
    }
}
