package com.starriverdata.core.event.listenter;

import com.starriverdata.event.AbstractEvent;

public interface Listener<E extends AbstractEvent> {

    void handleEvent(E event);
}
