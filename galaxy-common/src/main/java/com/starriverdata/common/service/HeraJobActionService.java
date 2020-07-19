package com.starriverdata.common.service;

import com.starriverdata.common.entity.HeraAction;
import com.starriverdata.common.entity.model.TablePageForm;
import com.starriverdata.common.entity.vo.HeraActionVo;
import com.starriverdata.common.kv.Tuple;
import com.starriverdata.common.vo.GroupTaskVo;
import com.starriverdata.common.vo.JobStatus;

import java.util.List;

public interface HeraJobActionService {

    int insert(HeraAction heraAction, Long nowAction);

    /**
     * 批量插入
     */
    List<HeraAction> batchInsert(List<HeraAction> heraActionList, Long nowAction);

    int delete(Long id);

    int update(HeraAction heraAction);

    List<HeraAction> getAll();

    HeraAction findById(Long actionId);

    HeraAction findById(String actionId);

    HeraAction findLatestByJobId(Long jobId);

    List<HeraAction> findByJobId(Long jobId);

    int updateStatus(JobStatus jobStatus);

    Tuple<HeraActionVo, JobStatus> findHeraActionVo(Long jobId);

    /**
     * 查找当前版本的运行状态
     */
    JobStatus findJobStatus(Long actionId);

    JobStatus findJobStatusByJobId(Long jobId);

    Integer updateStatus(Long id, String status);

    Integer updateStatusAndReadDependency(HeraAction heraAction);

    List<HeraAction> getAfterAction(Long action);

    /**
     * 根据jobId 获取所有的版本
     */
    List<Long> getActionVersionByJobId(Long jobId);

    List<HeraActionVo> getNotRunScheduleJob();

    List<HeraActionVo> getFailedJob();

    List<GroupTaskVo> findByJobIds(List<Integer> idList, String startDate, String endDate, TablePageForm pageForm, String status);

    void deleteHistoryRecord(Integer beforeDay);

    void deleteAllHistoryRecord(Integer beforeDay);

    List<HeraAction> findByStartAndEnd(Long startAction, Long endAction, Integer jobId,Integer limit);

    boolean deleteAction(long startAction, long endAction, Integer jobId);

    HeraAction findTodaySuccessByJobId(int id);
}
