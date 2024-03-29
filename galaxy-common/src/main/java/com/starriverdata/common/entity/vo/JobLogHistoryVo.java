package com.starriverdata.common.entity.vo;

import lombok.Data;

@Data
public class JobLogHistoryVo {

    private Long id;

    private String actionId;

    private String endTime;

    private String startTime;

    private int jobId;

    private String status;

    private String executeHost;

    private String operator;

    private String illustrate;

    private int triggerType;
    
    private String batchId;

    private String bizLabel;
    
    private String jobName;
    
    private String description;
    
    private String groupId;
    
    private String groupName;
    
    private String dur240px;
    
    private String begintime240px;

}
