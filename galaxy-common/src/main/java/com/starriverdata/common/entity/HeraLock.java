package com.starriverdata.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * hera的分布式锁基于数据库实现
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HeraLock {

    private Integer id;
    private String host;
    private Date serverUpdate;
    private Date gmtCreate ;
    private Date gmtModified;
    private String subgroup;

}
