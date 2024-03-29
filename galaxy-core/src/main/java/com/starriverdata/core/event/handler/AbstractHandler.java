package com.starriverdata.core.event.handler;

import com.starriverdata.event.ApplicationEvent;
import com.starriverdata.event.EventType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象任务实践处理器
 */
@Data
public abstract class AbstractHandler {

    protected List<AbstractHandler> children;
    protected boolean initialized;
    protected AbstractHandler parent;

    private List<EventType> supportedEvents;


    public boolean canHandle(ApplicationEvent event) {
        return canHandle(event, true);
    }

    public boolean canHandle(ApplicationEvent event, boolean bubbleDown) {
        if (supportedEvents != null && supportedEvents.contains(event.getType())) {
            return true;
        }
        if (children != null && bubbleDown) {
            for (AbstractHandler c : children) {
                if (c.canHandle(event, bubbleDown)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * hera事件处理方法
     */
    public abstract void handleEvent(ApplicationEvent event);

    public void initialize() {

    }

    public void destroy() {
    }

    protected void registerEventType(EventType type) {
        if (supportedEvents == null) {
            supportedEvents = new ArrayList<>();
        }
        if (type != null) {
            if (!supportedEvents.contains(type)) {
                supportedEvents.add(type);
            }
        }
    }
}
