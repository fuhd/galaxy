package com.starriverdata.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeraDebugHistory {

    private Long id;

    private Integer fileId;

    private Date startTime;

    private Date endTime;

    private String executeHost;

    private String status;

    private String owner;

    private Date gmtCreate ;

    private Date gmtModified;

    private String script;

    private String runType;

    private String log;

    private int hostGroupId;

    private Integer jobId;
}
