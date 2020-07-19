package com.starriverdata.common.service;

import com.starriverdata.common.entity.HeraProfile;
import com.starriverdata.common.entity.vo.HeraProfileVo;

public interface HeraProfileService {

    HeraProfileVo findByOwner(String owner);

    void insert(HeraProfile profile);

    void update(HeraProfile profile);
}
