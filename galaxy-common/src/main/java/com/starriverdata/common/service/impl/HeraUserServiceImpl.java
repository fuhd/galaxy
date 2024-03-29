package com.starriverdata.common.service.impl;

import com.starriverdata.common.entity.HeraUser;
import com.starriverdata.common.mapper.HeraUserMapper;
import com.starriverdata.common.service.HeraUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("heraUserService")
public class HeraUserServiceImpl implements HeraUserService {

    @Autowired
    private HeraUserMapper heraUserMapper;


    @Override
    public int insert(HeraUser heraUser) {
        Date now = new Date();
        heraUser.setGmtCreate(now);
        heraUser.setGmtModified(now);
        return heraUserMapper.insert(heraUser);
    }

    @Override
    public int delete(Integer id) {
        return heraUserMapper.delete(id);
    }

    @Override
    public boolean update(HeraUser heraUser) {
        return heraUserMapper.update(heraUser) > 0;
    }

    @Override
    public List<HeraUser> getAll() {
        return heraUserMapper.getAll();
    }

    @Override
    public HeraUser findById(Integer id) {
        return heraUserMapper.findById(id);
    }

    @Override
    public HeraUser findByName(String name) {
        HeraUser heraUser = HeraUser.builder().name(name).build();
        return heraUserMapper.getByName(heraUser);
    }

    @Override
    public List<HeraUser> findByIds(List<Integer> list) {
        return heraUserMapper.findByIds(list);
    }

    @Override
    public int updateEffective(Integer id, String effective) {
        return heraUserMapper.updateEffective(id, effective);
    }

    @Override
    public List<HeraUser> findAllName() {
        return heraUserMapper.getAllName();
    }

    @Override
    public List<HeraUser> getGroups() {
        return heraUserMapper.selectGroups();
    }
}
