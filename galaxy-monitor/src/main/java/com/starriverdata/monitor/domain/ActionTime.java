package com.starriverdata.monitor.domain;

import lombok.Data;

@Data
public class ActionTime {

    private Integer jobId;

    private String actionId;

    private String jobTime;

    private Integer yesterdayTime;
}
