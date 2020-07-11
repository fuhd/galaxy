package com.dfire.core.event.base;

import com.dfire.core.event.Dispatcher;
import com.dfire.event.AbstractEvent;
import com.dfire.event.ApplicationEvent;
import com.dfire.event.EventType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: <a href="mailto:lingxiao@2dfire.com">凌霄</a>
 * @time: Created in 下午5:19 2018/4/18
 * @desc
 */
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
