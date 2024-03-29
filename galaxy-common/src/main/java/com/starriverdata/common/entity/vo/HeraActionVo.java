package com.starriverdata.common.entity.vo;

import com.starriverdata.common.enums.JobRunTypeEnum;
import com.starriverdata.common.enums.JobScheduleTypeEnum;
import com.starriverdata.common.processor.Processor;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeraActionVo {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private Integer jobId;

    private Boolean auto;

    private Map<String, String> configs;

    private String cronExpression;

    private String cycle;

    private List<Long> dependencies;

    private List<Long> jobDependencies;

    private String description;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer groupId;

    private String historyId;

    private String host;

    private Date lastEndTime;

    private String lastResult;

    private String name;

    private Integer offset;

    private String owner;

    private List<Processor> postProcessors;

    private List<Processor> preProcessors;

    private List<String> readyDependency;

    private List<Map<String, String>> resources;

    private JobRunTypeEnum runType;

    private JobScheduleTypeEnum scheduleType;

    private String script;

    private Date startTime;

    private Long startTimestamp;

    private Date statisticStartTime;

    private Date statisticEndTime;

    private String status;

    private String timezone;

    private int hostGroupId;

    //private String cronPeriod;

    //private int cronInterval;

    private String batchId;

    //private String bizLabel;

}
