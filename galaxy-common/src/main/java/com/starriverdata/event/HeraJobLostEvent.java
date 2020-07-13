package com.starriverdata.event;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: <a href="mailto:lingxiao@2dfire.com">凌霄</a>
 * @time: Created in 下午4:20 2018/4/19
 * @desc
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class HeraJobLostEvent extends ApplicationEvent {

    private final Long jobId;

    public HeraJobLostEvent(EventType type, Long jobId) {
        super(type);
        this.jobId = jobId;
    }
}
