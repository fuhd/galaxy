package com.starriverdata.core.event.base;

import com.starriverdata.core.event.Dispatcher;
import com.starriverdata.event.AbstractEvent;
import com.starriverdata.event.ApplicationEvent;
import com.starriverdata.event.EventType;
import lombok.Data;
import lombok.EqualsAndHashCode;

//TODO:fuhd
@EqualsAndHashCode(callSuper=true)
@Data
public class MvcEvent extends AbstractEvent {

    private ApplicationEvent applicationEvent;
    private String name;

    public MvcEvent(EventType type) {
        super(type);
    }

    public MvcEvent(Dispatcher dispatcher, ApplicationEvent applicationEvent) {
        super(dispatcher);
        this.applicationEvent = applicationEvent;
    }
}
