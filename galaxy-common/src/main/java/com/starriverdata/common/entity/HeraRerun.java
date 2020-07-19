package com.starriverdata.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 开发中心脚本记录
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HeraRerun {

    private Integer id;

    private Integer jobId;

    private Integer isEnd;

    private String name;

    private Long startMillis;

    private Long endMillis;

    private Long gmtCreate;

    private String ssoName;

    private String extra;

    private Long actionNow;

}
