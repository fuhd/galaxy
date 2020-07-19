package com.starriverdata.event;

import com.starriverdata.common.entity.HeraDebugHistory;
import lombok.Builder;
import lombok.Getter;

@Builder
public class HeraDebugSuccessEvent extends ApplicationEvent {

    @Getter
    private HeraDebugHistory history;
    private Integer fileId;

    public HeraDebugSuccessEvent(HeraDebugHistory history, Integer fileId) {
        super(Events.JobSucceed);
        this.fileId = fileId;
        this.history = history;
    }

}
