package com.starriverdata.common.entity.vo;

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
public class HeraGroupVo {

    private Integer id;


    private String description;

    private Integer directory;

    private Date gmtCreate;

    private Date gmtModified;

    private String name;

    private String owner;

    private Integer parent;

    private String uIdS;

    private boolean existed;

    private String selfConfigs;
    private Map<String, String> configs;
    private List<Map<String,String>> resources;

    private Map<String, String> inheritConfig;
}
