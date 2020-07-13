package com.starriverdata.common.entity.vo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: <a href="mailto:lingxiao@2dfire.com">凌霄</a>
 * @time: Created in 上午10:47 2018/5/2
 * @desc
 */
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