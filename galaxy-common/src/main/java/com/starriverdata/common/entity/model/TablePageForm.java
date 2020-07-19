package com.starriverdata.common.entity.model;

import lombok.Data;

/**
 * table分页工具
 */
@Data
public class TablePageForm {

    private Integer page;
    private Integer limit;
    private Integer count;


    public Integer getStartPos() {
        return (page - 1) * limit;
    }
}
