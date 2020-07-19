package com.starriverdata.form;

import lombok.Data;

@Data
public class JobSearchForm {

    private String script;
    private String name;
    private String description;
    private String config;
    private Integer auto;
    private String runType;


}