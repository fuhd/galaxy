package com.starriverdata.event;

import com.starriverdata.common.entity.HeraDebugHistory;
import lombok.Builder;
import lombok.Getter;

@Builder
public class HeraDebugFailEvent extends ApplicationEvent {

    @Getter
    private final HeraDebugHistory debugHistory;
    private final Integer fileId;
    private final Throwable throwable;

    public HeraDebugFailEvent(HeraDebugHistory history, Integer fileId, Throwable t) {
        super(Events.JobFailed);
        this.fileId = fileId;
        this.debugHistory = history;
        this.throwable = t;
    }
}
