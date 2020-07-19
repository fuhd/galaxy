package com.starriverdata.common.mapper;

import com.starriverdata.common.entity.HeraProfile;

public interface HeraProfileMapper {

    void update(String uid, HeraProfile p);

    public HeraProfile findByUid(String uid);

}
