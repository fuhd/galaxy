package com.starriverdata.core.event.base;

import com.starriverdata.core.event.listenter.Listener;
import com.starriverdata.event.AbstractEvent;
import com.starriverdata.event.EventType;

import java.util.List;

public interface Observable {

    void addListener(EventType eventType, Listener<? extends AbstractEvent> listener);

    List<Listener<? extends AbstractEvent>> getListeners(EventType eventType);

    boolean hasListeners();

    boolean hasListeners(EventType eventType);

    void removeAllListeners();

    void removeAllListeners(EventType eventType, Listener<? extends AbstractEvent> listener);

    boolean fireEvent(EventType eventType, AbstractEvent abstractEvent);


}
