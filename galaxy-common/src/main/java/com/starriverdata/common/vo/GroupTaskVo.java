package com.starriverdata.common.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupTaskVo {

    private String actionId;
    private String jobId;
    private String name;
    private String status;
    private String readyStatus;
    private String lastResult;
}
