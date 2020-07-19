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
public class HeraProfile {

    private String id;
    private String uid;
    private String hadoopConf;
    private Date gmtCreate;
    private Date gmtModified;
}
