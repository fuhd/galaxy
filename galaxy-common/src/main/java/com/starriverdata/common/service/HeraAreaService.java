package com.starriverdata.common.service;

import com.starriverdata.common.entity.HeraArea;

import java.util.List;

public interface HeraAreaService {

    Integer add(HeraArea heraArea);

    HeraArea findById(Integer id);

    List<HeraArea> findByIdList(List<Integer> idList);

    Integer updateById(HeraArea heraArea);

    Integer deleteById(Integer id);

    List<HeraArea> findAll();
}
