package com.starriverdata.common.service.impl;

import com.starriverdata.common.entity.HeraRerun;
import com.starriverdata.common.entity.model.TablePageForm;
import com.starriverdata.common.entity.vo.HeraRerunVo;
import com.starriverdata.common.mapper.HeraRerunMapper;
import com.starriverdata.common.service.HeraRerunService;
import com.starriverdata.common.util.ActionUtil;
import com.starriverdata.common.util.Pair;
import com.starriverdata.common.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HeraRerunServiceImpl implements HeraRerunService {


    @Autowired
    private HeraRerunMapper heraRerunMapper;

    @Override
    public boolean add(HeraRerunVo heraRerunVo) {
        HeraRerun rerun = this.getRerun(heraRerunVo);
        rerun.setGmtCreate(System.currentTimeMillis());
        return heraRerunMapper.insert(this.getRerun(heraRerunVo)) > 0;
    }

    @Override
    public HeraRerun findById(Integer id) {
        return heraRerunMapper.selectById(id);
    }

    @Override
    public HeraRerunVo findVoById(Integer id) {
        return Optional.of(this.findById(id))
                .map(this::getVo)
                .orElseThrow(() -> new NullPointerException("can not found hera_rerun record in db"));
    }

    @Override
    public boolean updateById(HeraRerun heraRerun) {
        Integer res = heraRerunMapper.updateById(heraRerun);
        return res != null && res > 0;
    }

    @Override
    public boolean updateById(HeraRerunVo heraRerunVo) {
        return this.updateById(this.getRerun(heraRerunVo));
    }

    @Override
    public boolean deleteById(Integer id) {
        Integer res = heraRerunMapper.deleteById(id);
        return res != null && res > 0;
    }

    @Override
    public Pair<Integer, List<HeraRerunVo>> findByPage(TablePageForm pageForm) {
        return Optional.of(heraRerunMapper.selectAll(pageForm.getStartPos(), pageForm.getLimit()))
                .map(rerunList -> Pair.of(heraRerunMapper.selectCount(), rerunList.stream().map(this::getVo).collect(Collectors.toList())))
                .orElseThrow(() -> new NullPointerException("can not found hera_rerun record in db"));
    }

    @Override
    public List<HeraRerunVo> findByEnd(Integer isEnd) {
        return Optional.of(heraRerunMapper.selectByEnd(isEnd))
                .map(rerunList -> rerunList.stream().map(this::getVo).collect(Collectors.toList()))
                .orElseThrow(() -> new NullPointerException("can not found hera_rerun record in db"));
    }

    @Override
    public HeraRerunVo findRecordByTime(Long millis, Integer jobId, int isEnd) {
        return Optional.of(heraRerunMapper.selectRecordByTime(millis, jobId, isEnd))
                .map(this::getVo)
                .orElseThrow(() -> new NullPointerException("can not found hera_rerun record in db"));
    }

    @Override
    public HeraRerunVo findByIdAndEnd(Integer jobId, int isEnd) {
        return Optional.of(heraRerunMapper.selectByIdAndEnd(jobId, isEnd))
                .map(this::getVo)
                .orElseThrow(() -> new NullPointerException("can not found hera_rerun record in db"));
    }

    @Override
    public Integer findCntByJob(Integer jobId, int isEnd) {
        return heraRerunMapper.selectCountByJob(jobId, isEnd);
    }

    private HeraRerunVo getVo(HeraRerun rerun) {
        HeraRerunVo rerunVo = new HeraRerunVo();
        BeanUtils.copyProperties(rerun, rerunVo);
        rerunVo.setEndTime(ActionUtil.getDefaultFormatterDate(new Date(rerun.getEndMillis())));
        rerunVo.setStartTime(ActionUtil.getDefaultFormatterDate(new Date(rerun.getStartMillis())));
        rerunVo.setGmtCreate(ActionUtil.getDefaultFormatterDate(new Date(rerun.getGmtCreate())));
        rerunVo.setExtra(StringUtil.convertStringToMap(rerun.getExtra()));
        return rerunVo;
    }

    private HeraRerun getRerun(HeraRerunVo heraRerunVo) {
        HeraRerun rerun = new HeraRerun();
        BeanUtils.copyProperties(heraRerunVo, rerun);
        if (StringUtils.isNotEmpty(heraRerunVo.getStartTime())) {
            rerun.setStartMillis(ActionUtil.getMillisFromStrDate(heraRerunVo.getStartTime()));
        }
        if (StringUtils.isNotEmpty(heraRerunVo.getEndTime())) {
            rerun.setEndMillis(ActionUtil.getMillisFromStrDate(heraRerunVo.getEndTime()));
        }
        if (StringUtils.isNotEmpty(heraRerunVo.getGmtCreate())) {
            rerun.setGmtCreate(ActionUtil.getMillisFromStrDate(heraRerunVo.getGmtCreate()));
        }
        if (heraRerunVo.getExtra() != null) {
            rerun.setExtra(StringUtil.convertMapToString(heraRerunVo.getExtra()));
        }
        return rerun;
    }
}
