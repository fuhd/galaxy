package com.starriverdata.common.entity.vo;

import lombok.Data;

@Data
public class HeraSsoVo {

    private Integer id;

    private String name;

    private String gName;

    private String phone;

    private String email;

    private String jobNumber;

    private Long gmtModified;

    private Integer isValid;
}