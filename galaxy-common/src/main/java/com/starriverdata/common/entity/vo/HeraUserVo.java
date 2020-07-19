package com.starriverdata.common.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HeraUserVo {

    private int id;

    private String email;

    private String createTime;

    private String opTime;

    private String name;

    private String phone;

    private String wangwang;

    private int isEffective;

    private String description;
}