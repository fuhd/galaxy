package com.starriverdata.common.vo;

import lombok.Data;

@Data
public class OSInfoVo {

    /**
     * 用户占用
     */
    float user = 1;
    /**
     * 系统占用
     */
    float system = 2;
    /**
     * 内存使用率
     */
    float mem = 3;
    /**
     * cpu空闲
     */
    float cpu = 4;
    /**
     * swap空闲
     */
    float swap = 5;
}
