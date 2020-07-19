package com.starriverdata.event;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=true)
@Builder
@Data
public class HeraScheduleTriggerEvent extends ApplicationEvent {

    private final Long actionId;

    public HeraScheduleTriggerEvent(Long actionId) {
        super(Events.ScheduleTrigger);
        this.actionId = actionId;
    }
}
