package com.starriverdata.common.entity.vo;

import lombok.Data;

@Data
public class PageHelperTimeRange {
    private Integer offset;
    private Integer pageSize;
    private Integer jobId;
    private String jobType;
    private String beginDt;
    private String endDt;
}
