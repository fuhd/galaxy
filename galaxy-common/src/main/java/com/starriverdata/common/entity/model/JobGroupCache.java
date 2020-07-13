package com.starriverdata.common.entity.model;

import com.starriverdata.common.entity.vo.HeraActionVo;
import com.starriverdata.common.kv.Tuple;
import com.starriverdata.common.service.HeraJobActionService;
import com.starriverdata.common.vo.JobStatus;
import lombok.Builder;

/**
 * @author: <a href="mailto:lingxiao@2dfire.com">凌霄</a>
 * @time: Created in 下午10:37 2018/5/6
 * @desc 版本运行状态缓存
 */
@Builder
public class JobGroupCache {


    private final Long actionId;
    private HeraActionVo heraActionVo;

    private HeraJobActionService heraJobActionService;

    public HeraActionVo getHeraActionVo() {
        if(heraActionVo == null) {
            Tuple<HeraActionVo, JobStatus> jobStatusTuple = heraJobActionService.findHeraActionVo(actionId);
            if(jobStatusTuple != null) {
                heraActionVo = jobStatusTuple.getSource();
            } else {
                heraActionVo = null;

            }
        }
        return heraActionVo;
    }

    public void refresh() {
        Tuple<HeraActionVo, JobStatus> jobStatusTuple = heraJobActionService.findHeraActionVo(actionId);
        if(jobStatusTuple != null) {
            heraActionVo = jobStatusTuple.getSource();
        } else {
            heraActionVo = null;
        }
    }
}