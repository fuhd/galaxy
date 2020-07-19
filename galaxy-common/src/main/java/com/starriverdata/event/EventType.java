package com.starriverdata.event;

import lombok.Data;

@Data
public class EventType {

    private static int count = 0;
    final String id;

    public EventType() {
        id = String.valueOf(count++);
    }

}
