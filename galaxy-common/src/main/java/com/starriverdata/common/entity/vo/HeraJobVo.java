package com.starriverdata.common.entity.vo;

import com.starriverdata.common.enums.JobRunTypeEnum;
import com.starriverdata.common.processor.Processor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeraJobVo {

    private int id;

    private String auto;

    private Map<String, String> configs;

    private String cronExpression;

    private String cycle;

    private String dependencies;

    private String description;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer groupId;

    private String historyId;

    private String host;

    private Date lastEndTime;

    private String lastResult;

    private String name;

    private String alarmLevel;

    private Integer offset;

    private String owner;

    private List<Processor> postProcessors;

    private List<Processor> preProcessors;

    private String readyDependency;

    private List<Map<String, String>> resources;

    private JobRunTypeEnum runType;

    private Integer scheduleType;

    private String script;

    private Date startTime;

    private Long startTimestamp;

    private Date statisticEndTime;

    private Date statisticStartTime;

    private String status;

    private String timezone;

    private int hostGroupId;

    private String hostGroupName;

    private int mustEndMinute;

    private String rollBackTimes;

    private String rollBackWaitTime;

    private String runPriorityLevel;

    private String selfConfigs;

    private String resource;

    private Map<String, String> inheritConfig;

    private boolean focus;

    private String uIdS;

    private String areaId;

    private String focusUser;

    private short repeatRun;

    private String cronPeriod;

    private int cronInterval;

    private String bizLabel;

    private Integer isValid;

    private String estimatedEndHour;
}
