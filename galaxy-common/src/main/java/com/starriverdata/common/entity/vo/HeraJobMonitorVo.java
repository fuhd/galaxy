package com.starriverdata.common.entity.vo;

import lombok.Data;
import java.util.List;

@Data
public class HeraJobMonitorVo {

    /**
     * 任务Id
     */
    private Integer id;

    /**
     * 任务名称
     */
    private String jobName;

    /**
     * 任务描述
     */
    private String description;

    /**
     * 监控人员
     */
    private String userIds;

    /**
     * 监控人
     */
    private List<HeraSsoVo> monitors;

}