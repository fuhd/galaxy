package com.starriverdata.common.service.impl;

import com.starriverdata.common.entity.HeraProfile;
import com.starriverdata.common.entity.vo.HeraProfileVo;
import com.starriverdata.common.mapper.HeraProfileMapper;
import com.starriverdata.common.service.HeraProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("heraProfileService")
public class HeraProfileServiceImpl implements HeraProfileService {

    @Autowired
    private HeraProfileMapper heraProfileMapper;


    @Override
    public HeraProfileVo findByOwner(String owner) {
        return null;
    }

    @Override
    public void insert(HeraProfile profile) {

    }

    @Override
    public void update(HeraProfile profile) {

    }
}
