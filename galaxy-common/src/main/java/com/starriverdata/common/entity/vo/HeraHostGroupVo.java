package com.starriverdata.common.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HeraHostGroupVo {

    private String id;

    private String name;

    private String description;

    private List<String> hosts;

    private int nextPos;

}
