package com.starriverdata.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HeraJobHistory {

    private Long id;

    private Long actionId;

    private Integer jobId;

    private Date startTime;

    private Date endTime;

    private String executeHost;

    private String operator;

    private String status;

    private Integer triggerType;

    private String illustrate;

    private Date statisticEndTime;

    private String log ;

    private String timezone;

    private String cycle;

    private int hostGroupId;
    
    private String batchId;
    
    private String bizLabel;
    
    private Date gmtCreate;

    String properties ;

}
