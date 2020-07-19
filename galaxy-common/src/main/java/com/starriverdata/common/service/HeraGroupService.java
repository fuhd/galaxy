package com.starriverdata.common.service;

import com.starriverdata.common.entity.HeraGroup;
import com.starriverdata.common.entity.model.HeraJobBean;

import java.util.List;

public interface HeraGroupService {

    HeraGroup getRootGroup();

    /**
     * 获取上游任务以及组
     */
    HeraJobBean getUpstreamJobBean(Integer jobId);

    int insert(HeraGroup heraGroup);

    int delete(int id);

    int update(HeraGroup heraGroup);

    List<HeraGroup> getAll();

    HeraGroup findById(Integer id);

    List<HeraGroup> findByIds(List<Integer> list);

    List<HeraGroup> findByParent(Integer parentId);

    List<HeraGroup> findByOwner(String owner);

    HeraGroup findConfigById(Integer id);


    boolean changeParent(Integer id, Integer parent);

    List<HeraGroup> findDownStreamGroup(Integer groupId);

}
