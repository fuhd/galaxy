package com.starriverdata.common.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HeraRerunVo {

    private Integer id;

    private Integer jobId;

    private Integer isEnd;

    private String name;

    private String startTime;

    private String endTime;

    private String gmtCreate;

    private String ssoName;

    private Map<String, String> extra;

    private Long actionNow;

}
