package com.starriverdata.event;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class HeraJobLostEvent extends ApplicationEvent {

    private final Long jobId;

    public HeraJobLostEvent(EventType type, Long jobId) {
        super(type);
        this.jobId = jobId;
    }
}
