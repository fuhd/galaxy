package com.starriverdata.event;

import lombok.Getter;

public class HeraJobMaintenanceEvent extends ApplicationEvent {

    @Getter
    private final Long id;

    public HeraJobMaintenanceEvent(EventType type, Long id) {
        super(type);
        this.id = id;
    }
}
