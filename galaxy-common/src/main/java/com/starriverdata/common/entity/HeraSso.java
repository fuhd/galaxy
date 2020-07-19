package com.starriverdata.common.entity;

import lombok.Data;

@Data
public class HeraSso {

    private Integer id;

    private String name;

    private String password;

    private Integer gid;

    private String phone;

    private String email;

    private String jobNumber;

    private Long gmtModified;

    private Integer isValid;
}
