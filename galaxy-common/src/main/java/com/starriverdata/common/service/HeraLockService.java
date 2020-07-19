package com.starriverdata.common.service;

import com.starriverdata.common.entity.HeraLock;

import java.util.Date;

public interface HeraLockService {

    HeraLock findBySubgroup(String group);

    Integer insert(HeraLock heraLock);

    int update(HeraLock heraLock);

    Integer changeLock(String host, Date serverUpdate, Date gmtModified, String lastHost);
}
