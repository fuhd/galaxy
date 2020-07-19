package com.starriverdata.common.service;

import com.starriverdata.common.entity.HeraDebugHistory;
import com.starriverdata.common.entity.vo.HeraDebugHistoryVo;

import java.util.List;

public interface HeraDebugHistoryService {

    Long insert(HeraDebugHistory heraDebugHistory);

    int delete(Long id);

    int update(HeraDebugHistory heraDebugHistory);

    List<HeraDebugHistory> getAll();

    HeraDebugHistoryVo findById(Long id);

    List<HeraDebugHistory> findByFileId(Integer fileId);

    int updateStatus(HeraDebugHistory heraDebugHistory);

    int updateLog(HeraDebugHistory heraDebugHistory);


    HeraDebugHistory findLogById(Integer id);

    void updateStatus(Long id, String msg, String status);
}
