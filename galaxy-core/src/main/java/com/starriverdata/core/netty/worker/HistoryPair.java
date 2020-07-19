package com.starriverdata.core.netty.worker;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HistoryPair {

    private Long actionId;

    private Long historyId;

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof HistoryPair)) {
            return false;
        }
        HistoryPair pair = (HistoryPair) obj;
        return actionId.equals(pair.getActionId()) && historyId.equals(pair.getHistoryId());
    }

    @Override
    public int hashCode() {
        return actionId.hashCode() * 1996 + historyId.hashCode() * 811;
    }
}
