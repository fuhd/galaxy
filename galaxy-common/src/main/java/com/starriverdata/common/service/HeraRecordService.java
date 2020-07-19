package com.starriverdata.common.service;

import com.starriverdata.common.entity.HeraRecord;
import com.starriverdata.common.entity.model.TablePageForm;
import com.starriverdata.common.entity.vo.PageHelper;
import com.starriverdata.common.util.Pair;

import java.util.List;
import java.util.Map;

public interface HeraRecordService {

    boolean add(HeraRecord record);

    Pair<Integer, List<HeraRecord>> selectByPage(TablePageForm pageForm);

    Pair<Integer, List<HeraRecord>> selectByPage(TablePageForm pageForm, Integer gid);

    Map<String, Object> findPageByLogId(PageHelper pageHelper);
}