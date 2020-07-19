package com.starriverdata.common.service;

import com.starriverdata.common.entity.HeraUser;

import java.util.List;

public interface HeraUserService {

    int insert(HeraUser heraUser);

    int delete(Integer id);

    boolean update(HeraUser heraUser);

    List<HeraUser> getAll();

    HeraUser findById(Integer id);

    HeraUser findByName(String name);

    List<HeraUser> findByIds(List<Integer> list);

    int updateEffective(Integer id, String effective);

    List<HeraUser> findAllName();

    List<HeraUser> getGroups();

}
