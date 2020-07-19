package com.starriverdata.common.service;

import com.starriverdata.common.entity.HeraRerun;
import com.starriverdata.common.entity.model.TablePageForm;
import com.starriverdata.common.entity.vo.HeraRerunVo;
import com.starriverdata.common.util.Pair;

import java.util.List;

public interface HeraRerunService {

    boolean add(HeraRerunVo heraRerun);

    HeraRerun findById(Integer id);

    HeraRerunVo findVoById(Integer id);

    boolean updateById(HeraRerun heraRerun);

    boolean updateById(HeraRerunVo heraRerunVo);

    boolean deleteById(Integer id);

    Pair<Integer,List<HeraRerunVo>> findByPage(TablePageForm pageForm);

    List<HeraRerunVo> findByEnd(Integer isEnd);

    HeraRerunVo findRecordByTime(Long millis, Integer jobId, int isEnd);

    HeraRerunVo findByIdAndEnd(Integer jobId, int i);

    Integer findCntByJob(Integer jobId, int isEnd);

}
