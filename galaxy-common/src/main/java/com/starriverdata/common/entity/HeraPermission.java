package com.starriverdata.common.entity;

import com.starriverdata.common.config.SkipColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HeraPermission {

    private int id;

    private String type;

    private Long targetId;

    private String uid;

    @SkipColumn
    private Date gmtCreate;

    @SkipColumn
    private Date gmtModified;

    private Integer isValid;


}
