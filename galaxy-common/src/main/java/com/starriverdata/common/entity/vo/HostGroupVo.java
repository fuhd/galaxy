package com.starriverdata.common.entity.vo;

import lombok.Data;

@Data
public class HostGroupVo {

    private Integer id;
    private String name;
    private boolean effective;
    private String gmtCreate;
    private String gmtModified;
    private String description;
}
