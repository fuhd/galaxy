package com.starriverdata.common.service;

import com.starriverdata.common.entity.HeraJobHistory;
import com.starriverdata.common.entity.vo.PageHelperTimeRange;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface HeraJobHistoryService {

    int updateHeraJobHistoryStatus(HeraJobHistory jobStatus);

    int updateHeraJobHistoryLog(HeraJobHistory heraJobHistory);

    int insert(HeraJobHistory heraJobHistory);

    int delete(Long id);

    int update(HeraJobHistory heraJobHistory);

    int updateStatusAndIllustrate(Long id, String status, String illustrate, Date endTime);

    List<HeraJobHistory> getAll();

    HeraJobHistory findById(Long id);

    List<HeraJobHistory> findByActionId(Long actionId);

    Integer updateHeraJobHistoryLogAndStatus(HeraJobHistory build);

    /**
     * 根据jobId查询运行历史
     */
    List<HeraJobHistory> findByJobId(Long jobId);

    HeraJobHistory findLogById(Integer id);

    Map<String, Object> findLogByPage(PageHelperTimeRange pageHelperTimeRange);

    List<HeraJobHistory> findTodayJobHistory();

    void deleteHistoryRecord(Integer beforeDay);

    HeraJobHistory findNewest(Long jobId);

    HeraJobHistory findPropertiesById(Long id);
}
