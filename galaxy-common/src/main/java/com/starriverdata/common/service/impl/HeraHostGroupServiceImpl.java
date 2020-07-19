package com.starriverdata.common.service.impl;

import com.starriverdata.common.entity.HeraHostGroup;
import com.starriverdata.common.entity.HeraHostRelation;
import com.starriverdata.common.mapper.HeraHostGroupMapper;
import com.starriverdata.common.service.HeraHostRelationService;
import com.starriverdata.common.entity.vo.HeraHostGroupVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.starriverdata.common.service.HeraHostGroupService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("heraHostGroupService")
public class HeraHostGroupServiceImpl implements HeraHostGroupService {

    @Autowired
    private HeraHostGroupMapper heraHostGroupMapper;
    @Autowired
    private HeraHostRelationService heraHostRelationService;

    @Override
    public int insert(HeraHostGroup heraHostGroup) {
        return heraHostGroupMapper.insert(heraHostGroup);
    }

    @Override
    public int delete(int id) {
        return heraHostGroupMapper.delete(id);
    }

    @Override
    public int update(HeraHostGroup heraHostGroup) {
        return heraHostGroupMapper.update(heraHostGroup);
    }

    @Override
    public List<HeraHostGroup> getAll() {
        return heraHostGroupMapper.getAll();
    }

    @Override
    public HeraHostGroup findById(int id) {
        HeraHostGroup group = HeraHostGroup.builder().id(id).build();
        return heraHostGroupMapper.findById(group);
    }

    @Override
    public Map<Integer, HeraHostGroupVo> getAllHostGroupInfo() {
        List<HeraHostGroup> groupList = this.getAll();
        Map<Integer, HeraHostGroupVo> hostGroupInfoMap = new HashMap<>(groupList.size());
        List<HeraHostRelation> relationList = heraHostRelationService.getAll();
        groupList.forEach(heraHostGroup -> {
            if(heraHostGroup.getEffective() == 1) {
                HeraHostGroupVo vo = HeraHostGroupVo.builder()
                        .id(String.valueOf(heraHostGroup.getId()))
                        .name(heraHostGroup.getName())
                        .nextPos(0)
                        .description(heraHostGroup.getDescription())
                        .build();
                List<String> hosts = new ArrayList<>();
                relationList.forEach(heraHostRelation -> {
                    if(heraHostRelation.getHostGroupId() == (heraHostGroup.getId())) {
                        hosts.add(heraHostRelation.getHost());
                    }
                });
                vo.setHosts(hosts);
                hostGroupInfoMap.put(heraHostGroup.getId(), vo);
            }
        });
        return hostGroupInfoMap;
    }
}
