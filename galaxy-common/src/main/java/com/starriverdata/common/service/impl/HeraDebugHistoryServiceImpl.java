package com.starriverdata.common.service.impl;

import com.starriverdata.common.entity.HeraDebugHistory;
import com.starriverdata.common.entity.vo.HeraDebugHistoryVo;
import com.starriverdata.common.mapper.HeraDebugHistoryMapper;
import com.starriverdata.common.service.HeraDebugHistoryService;
import com.starriverdata.common.util.ActionUtil;
import com.starriverdata.common.util.BeanConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("heraDebugHistoryService")
public class HeraDebugHistoryServiceImpl implements HeraDebugHistoryService {

    @Autowired
    HeraDebugHistoryMapper heraDebugHistoryMapper;

    @Override
    public Long insert(HeraDebugHistory heraDebugHistory) {
        heraDebugHistoryMapper.insert(heraDebugHistory);
        return heraDebugHistory.getId();
    }

    @Override
    public int delete(Long id) {
        return heraDebugHistoryMapper.delete(id);
    }

    @Override
    public int update(HeraDebugHistory heraDebugHistory) {
        return heraDebugHistoryMapper.update(heraDebugHistory);
    }

    @Override
    public List<HeraDebugHistory> getAll() {
        return heraDebugHistoryMapper.getAll();
    }

    @Override
    public HeraDebugHistoryVo findById(Long id) {
        return BeanConvertUtils.convert(heraDebugHistoryMapper.findById(id));
    }

    @Override
    public List<HeraDebugHistory> findByFileId(Integer fileId) {
        return heraDebugHistoryMapper.findByFileId(fileId);
    }

    @Override
    public int updateStatus(HeraDebugHistory heraDebugHistory) {
        return heraDebugHistoryMapper.updateStatus(heraDebugHistory);
    }

    @Override
    public int updateLog(HeraDebugHistory heraDebugHistory) {
        return heraDebugHistoryMapper.updateLog(heraDebugHistory);
    }

    @Override
    public HeraDebugHistory findLogById(Integer id) {
        return heraDebugHistoryMapper.findLogById(id);
    }

    @Override
    public void updateStatus(Long id, String msg, String status) {
        heraDebugHistoryMapper.updateStatusAndLog(id, msg, status, ActionUtil.getTodayString());
    }


}
