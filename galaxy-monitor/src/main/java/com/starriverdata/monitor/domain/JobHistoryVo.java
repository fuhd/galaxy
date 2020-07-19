package com.starriverdata.monitor.domain;

import lombok.Data;

@Data
public class JobHistoryVo {

    private String jobId;

    //private String actionId;

    private String startTime;

    private String endTime;

    private String executeHost;

    private String status;

    private String operator;

    private String description;

    private String jobName;
    
    private String groupId;

    private String groupName;

    private Integer times;
    
    private String bizLabel;
}
