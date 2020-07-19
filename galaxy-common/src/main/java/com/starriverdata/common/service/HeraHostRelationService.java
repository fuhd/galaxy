package com.starriverdata.common.service;

import com.starriverdata.common.entity.HeraHostRelation;

import java.util.List;

public interface HeraHostRelationService {

    int insert(HeraHostRelation heraHostRelation);

    int delete(int id);

    int update(HeraHostRelation heraHostRelation);

    List<HeraHostRelation> getAll();

    HeraHostRelation findById(HeraHostRelation heraHostRelation);

    List<String> findPreemptionGroup(int id);
}
