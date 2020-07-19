package com.starriverdata.common.entity.form;

import lombok.Data;

@Data
public class HeraRerunForm {
    private Integer id;
    private Integer jobId;
    private String name;
    private String startTime;
    private String endTime;
    private String threads;

}
