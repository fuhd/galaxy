package com.starriverdata.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HeraHostGroup {

    private Integer id;

    private String name;

    private Integer effective;

    private String description;

    private Date gmtCreate;

    private Date gmtModified;

}
