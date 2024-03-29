package com.starriverdata.common.service.impl;

import com.starriverdata.common.entity.HeraRecord;
import com.starriverdata.common.entity.model.TablePageForm;
import com.starriverdata.common.entity.vo.PageHelper;
import com.starriverdata.common.mapper.HeraRecordMapper;
import com.starriverdata.common.service.HeraRecordService;
import com.starriverdata.common.util.ActionUtil;
import com.starriverdata.common.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HeraRecordServiceImpl implements HeraRecordService {

    @Autowired
    private HeraRecordMapper recordMapper;

    @Override
    public boolean add(HeraRecord record) {
        if (record.getContent() == null) {
            record.setContent("");
        }

        record.setGmtModified(ActionUtil.getMillis());
        record.setGmtCreate(ActionUtil.getMillis());
        return recordMapper.insert(record) > 1;
    }

    @Override
    public Pair<Integer, List<HeraRecord>> selectByPage(TablePageForm pageForm) {
        return Pair.of(recordMapper.allCount(), recordMapper.selectByPage(pageForm.getStartPos(), pageForm.getLimit()));
    }

    @Override
    public Pair<Integer, List<HeraRecord>> selectByPage(TablePageForm pageForm, Integer gid) {
        return Pair.of(recordMapper.countByGid(gid), recordMapper.selectByGid(pageForm.getStartPos(), pageForm.getLimit(), gid));
    }

    @Override
    public Map<String, Object> findPageByLogId(PageHelper pageHelper) {
        Map<String, Object> res = new HashMap<>(2);
        res.put("total", recordMapper.selectCountById(pageHelper.getJobId()));
        res.put("rows", recordMapper.findPageByLogId(pageHelper.getOffset(), pageHelper.getPageSize(), pageHelper.getJobId()));
        return res;
    }
}
