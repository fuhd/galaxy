package com.starriverdata.common.entity.vo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Builder
@Data
public class HeraProfileVo {
    private String id;
    private String uid;
    //TODO: fuhd
    @Builder.Default
    private Map<String, String> hadoopConf = new HashMap<String, String>();
    //TODO: fuhd
    @Builder.Default
    private Date gmtCreate = new Date();
    //TODO: fuhd
    @Builder.Default
    private Date gmtModified = new Date();
}
