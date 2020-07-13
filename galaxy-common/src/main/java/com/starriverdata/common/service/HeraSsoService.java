package com.starriverdata.common.service;

import com.starriverdata.common.entity.HeraSso;
import com.starriverdata.common.entity.vo.HeraSsoVo;

import java.util.List;

public interface HeraSsoService {

    boolean addSso(HeraSso heraSso);

    List<HeraSso> getAll();

    boolean deleteSsoById(Integer id);

    boolean updateHeraSsoById(HeraSso heraSso);

    HeraSso findSsoById(Integer id);

    HeraSsoVo findSsoVoById(Integer id);

    HeraSso findSsoByName(String name);

    boolean checkByName(String name);

    boolean setValid(Integer id,Integer val);

}
