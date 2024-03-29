package com.starriverdata.event;

import com.starriverdata.common.entity.HeraJob;
import com.starriverdata.common.entity.vo.HeraJobHistoryVo;
import com.starriverdata.common.enums.TriggerTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Objects;

@Data
@EqualsAndHashCode(callSuper=true)
public class HeraJobFailedEvent extends ApplicationEvent {

    private final HeraJobHistoryVo heraJobHistory;
    private final Long actionId;
    private final TriggerTypeEnum triggerType;
    private HeraJob heraJob;
    private int runCount = 0;
    private int rollBackTime = 0;
    private int retryCount = 0;

    public HeraJobFailedEvent(Long jobId, TriggerTypeEnum triggerType, HeraJobHistoryVo heraJobHistory) {
        super(Events.JobFailed);
        this.actionId = jobId;
        this.triggerType = triggerType;
        this.heraJobHistory = heraJobHistory;
    }

    public void setRollBackTime(int value) {
        if (Objects.equals(triggerType, TriggerTypeEnum.SCHEDULE) || Objects.equals(triggerType, TriggerTypeEnum.MANUAL_RECOVER)) {
            this.rollBackTime = value;
        }
    }
}
