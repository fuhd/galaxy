package com.starriverdata.event;

import com.starriverdata.common.entity.vo.HeraJobHistoryVo;
import com.starriverdata.common.enums.TriggerTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class HeraJobSuccessEvent extends ApplicationEvent {


    private final HeraJobHistoryVo heraJobHistory;

    private String statisticEndTime;
    private Long jobId;
    private TriggerTypeEnum triggerType;

    public HeraJobSuccessEvent(Long jobId, TriggerTypeEnum triggerType, HeraJobHistoryVo heraJobHistory) {
        super(Events.JobSucceed);
        this.jobId = jobId;
        this.triggerType = triggerType;
        this.heraJobHistory = heraJobHistory;
    }

}
