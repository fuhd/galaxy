package com.starriverdata.common.entity.vo;

import com.starriverdata.common.enums.StatusEnum;
import com.starriverdata.common.enums.TriggerTypeEnum;
import com.starriverdata.common.vo.LogContent;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Builder
@Data
public class HeraJobHistoryVo {

    private Long id;

    private Long actionId;

    private Integer jobId;

    private Date startTime;

    private Date endTime;

    private String executeHost;

    private String operator;

    private StatusEnum statusEnum;

    private TriggerTypeEnum triggerType;

    private String illustrate;

    private Date statisticsEndTime;

    private LogContent log;

    private String timezone;

    private String cycle;

    private int hostGroupId;

    private Map<String, String> properties;
    
    private String batchId;
    private String bizLabel;

}
