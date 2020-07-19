package com.starriverdata.common.service.impl;

import com.starriverdata.common.entity.HeraGroup;
import com.starriverdata.common.entity.HeraJob;
import com.starriverdata.common.entity.model.HeraGroupBean;
import com.starriverdata.common.entity.model.HeraJobBean;
import com.starriverdata.common.mapper.HeraGroupMapper;
import com.starriverdata.common.service.HeraGroupService;
import com.starriverdata.common.service.HeraJobService;
import com.starriverdata.common.util.BeanConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("heraGroupService")
public class HeraGroupServiceImpl implements HeraGroupService {

    @Autowired
    protected HeraGroupMapper heraGroupMapper;

    @Autowired
    private HeraJobService heraJobService;

    @Override
    public HeraGroup getRootGroup() {
        return this.findById(-1);
    }

    @Override
    public HeraJobBean getUpstreamJobBean(Integer jobId) {
        HeraJob heraJob = heraJobService.findById(jobId);
        if (heraJob != null) {
            HeraJobBean jobBean = HeraJobBean.builder()
                    .heraJob(heraJob)
                    .build();
            jobBean.setGroupBean(getUpstreamGroupBean(heraJob.getGroupId()));
            return jobBean;
        }
        return null;
    }

    private HeraGroupBean getUpstreamGroupBean(Integer groupId) {
        HeraGroup heraGroup = this.findById(groupId);
        HeraGroupBean result = HeraGroupBean.builder()
                .groupVo(BeanConvertUtils.convert(heraGroup))
                .build();
        if (heraGroup != null && heraGroup.getParent() != null) {
            HeraGroupBean parentGroupBean = getUpstreamGroupBean(heraGroup.getParent());
            result.setParentGroupBean(parentGroupBean);
        }
        return result;
    }


    @Override
    public int insert(HeraGroup heraGroup) {
        return heraGroupMapper.insert(heraGroup);
    }

    @Override
    public int delete(int id) {
        return heraGroupMapper.delete(id);
    }

    @Override
    public int update(HeraGroup heraGroup) {
        return heraGroupMapper.update(heraGroup);
    }

    @Override
    public List<HeraGroup> getAll() {
        return heraGroupMapper.getAll();
    }

    @Override
    public HeraGroup findById(Integer id) {
        return heraGroupMapper.findById(id);
    }

    @Override
    public List<HeraGroup> findByIds(List<Integer> list) {
        return heraGroupMapper.findByIds(list);
    }

    @Override
    public List<HeraGroup> findByParent(Integer parentId) {
        return heraGroupMapper.findByParent(parentId);
    }

    @Override
    public List<HeraGroup> findByOwner(String owner) {
        return heraGroupMapper.findByOwner(owner);
    }

    @Override
    public HeraGroup findConfigById(Integer id) {
        return heraGroupMapper.selectConfigById(id);
    }

    @Override
    public boolean changeParent(Integer id, Integer parent) {
        Integer update = heraGroupMapper.changeParent(id, parent);
        return update != null && update > 0;
    }

    @Override
    public List<HeraGroup> findDownStreamGroup(Integer groupId) {
        List<HeraGroup> res = new ArrayList<>();
        getDownStreamGroup(this.findById(groupId), res);
        return res;
    }

    private void getDownStreamGroup(HeraGroup heraGroup, List<HeraGroup> streamGroup) {
        if (heraGroup.getDirectory() != 0) {
            streamGroup.add(heraGroup);
            return;
        }
        this.findByParent(heraGroup.getId()).forEach(group -> getDownStreamGroup(group, streamGroup));


    }
}
