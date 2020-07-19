package com.starriverdata.event;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AbstractEvent {

    private boolean cancelled;
    private Object source;
    private EventType type;

    public AbstractEvent(EventType type) {
        this.type = type;
    }

    public AbstractEvent(Object source) {
        this.source = source;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }


}
