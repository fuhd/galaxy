package com.starriverdata.common.service;

import com.starriverdata.common.entity.HeraPermission;

import java.util.List;

public interface HeraPermissionService {

    int insert(HeraPermission heraPermission);

    List<HeraPermission> findByTargetId(Integer targetId, String type, Integer isValid);

    HeraPermission findByCond(Integer id, String owner, String type);

    Integer updateByUid(Integer id, String type, Integer isValid, String uId);


    Integer insertList(List<HeraPermission> permissions);
}
