package com.starriverdata.common.service;

import com.starriverdata.common.entity.HeraHostGroup;
import com.starriverdata.common.entity.vo.HeraHostGroupVo;

import java.util.List;
import java.util.Map;

public interface HeraHostGroupService {

    int insert(HeraHostGroup heraHostGroup);

    int delete(int id);

    int update(HeraHostGroup heraHostGroup);

    List<HeraHostGroup> getAll();

    HeraHostGroup findById(int id);

    /**
     * 查询出所有的host组 如：本地 测试 etc.
     */
    Map<Integer, HeraHostGroupVo> getAllHostGroupInfo();


}
