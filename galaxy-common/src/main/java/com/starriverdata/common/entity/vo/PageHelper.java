package com.starriverdata.common.entity.vo;

import lombok.Data;

@Data
public class PageHelper {
    private Integer offset;
    private Integer pageSize;
    private Integer jobId;
}
