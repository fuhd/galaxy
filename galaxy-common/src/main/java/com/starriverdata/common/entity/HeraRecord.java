package com.starriverdata.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * hera操作日志 以及脚本的历史版本恢复
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HeraRecord {

    private int id;

    private int type;

    private String content;

    private String sso;

    private Integer gid;

    private String logType;

    private Integer logId;

    private Long gmtCreate;

    private Long gmtModified;


}