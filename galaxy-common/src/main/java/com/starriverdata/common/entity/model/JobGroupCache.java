package com.starriverdata.common.entity.model;

import com.starriverdata.common.entity.vo.HeraActionVo;
import com.starriverdata.common.kv.Tuple;
import com.starriverdata.common.service.HeraJobActionService;
import com.starriverdata.common.vo.JobStatus;
import lombok.Builder;

/**
 * 版本运行状态缓存
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
