package com.starriverdata.common.entity.vo;

import com.starriverdata.common.enums.JobRunTypeEnum;
import com.starriverdata.common.enums.StatusEnum;
import com.starriverdata.common.vo.LogContent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeraDebugHistoryVo {

    private Long id;

    private Integer fileId;

    private String startTime;

    private String endTime;

    private String executeHost;

    private StatusEnum status;

    private String owner;

    private String gmtCreate;

    private String gmtModified;

    private String script;

    private JobRunTypeEnum runType;

    private LogContent log ;

    private String host;

    private int hostGroupId;

    private Integer jobId;

}
