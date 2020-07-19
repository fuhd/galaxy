package com.starriverdata.common.entity;

import lombok.Data;

@Data
public class HeraJobMonitor {

    /**
     * 任务Id
     */
    private Integer jobId;
    /**
     * 监控人ID ,分割
     */
    private String userIds;
}
