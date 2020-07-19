package com.starriverdata.common.service.impl;

import com.starriverdata.common.entity.HeraHostRelation;
import com.starriverdata.common.mapper.HeraHostRelationMapper;
import com.starriverdata.common.service.HeraHostRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("heraHostRelationService")
public class HeraHostRelationServiceImpl implements HeraHostRelationService {

    @Autowired
    private HeraHostRelationMapper heraHostRelationMapper;

    @Override
    public int insert(HeraHostRelation heraHostRelation) {
        return heraHostRelationMapper.insert(heraHostRelation);
    }

    @Override
    public int delete(int id) {
        return heraHostRelationMapper.delete(id);
    }

    @Override
    public int update(HeraHostRelation heraHostRelation) {
        return heraHostRelationMapper.update(heraHostRelation);
    }

    @Override
    public List<HeraHostRelation> getAll() {
        return heraHostRelationMapper.getAll();
    }

    @Override
    public HeraHostRelation findById(HeraHostRelation heraHostRelation) {
        return heraHostRelationMapper.findById(heraHostRelation);
    }

    @Override
    public List<String> findPreemptionGroup(int id) {
        return heraHostRelationMapper.findPreemptionGroup(id);
    }
}
