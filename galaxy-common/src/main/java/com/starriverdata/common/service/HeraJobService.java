package com.starriverdata.common.service;

import com.starriverdata.common.entity.HeraJob;
import com.starriverdata.common.entity.vo.HeraJobTreeNodeVo;

import java.util.List;
import java.util.Map;

public interface HeraJobService {

    int insert(HeraJob heraJob);

    int delete(int id);

    Integer update(HeraJob heraJob);

    List<HeraJob> getAll();

    HeraJob findById(int id);

    Integer findMustEndMinute(int id);

    List<HeraJob> findEstimatedEndHours(int startTime, int endTime);

    HeraJob findMemById(int id);

    List<HeraJob> findByIds(List<Integer> list);

    List<HeraJob> findByPid(int groupId);

    /**
     * 构建job树形目录结构
     */
    Map<String, List<HeraJobTreeNodeVo>> buildJobTree(String owner);

    boolean changeSwitch(Integer id, Integer status);

    Map<String, Object> findCurrentJobGraph(int jobId, Integer type);

    List<Integer> findJobImpact(int jobId, Integer type);

    List<HeraJob> findDownStreamJob(Integer jobId);

    List<HeraJob> findUpStreamJob(Integer jobId);

    List<HeraJob> getAllJobDependencies();

    boolean changeParent(Integer newId, Integer parentId);

    boolean isRepeat(Integer jobId);

    Integer updateScript(Integer id, String script);

    Integer selectMaxId();

    HeraJob copyJobFromExistsJob(Integer jobId);

}
