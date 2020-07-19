package com.starriverdata.event;


import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ApplicationEvent extends AbstractEvent {

    private Object data;

    public ApplicationEvent(EventType type) {
        super(type);
    }

    public ApplicationEvent(EventType type, Object data) {
        super(type);
        this.data = data;
    }

}
