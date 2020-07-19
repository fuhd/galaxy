package com.starriverdata.common.entity;

import lombok.Data;

import java.util.Date;

@Data
public class HeraArea {

    private Integer id;

    private String timezone;

    private String name;

    private Date gmtCreate;

    private Date gmtModified;

}
