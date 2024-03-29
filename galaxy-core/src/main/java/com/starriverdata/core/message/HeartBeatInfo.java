package com.starriverdata.core.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 心跳中传递的机器信息
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HeartBeatInfo {

    private Float memRate;

    private List<String> running;

    private List<String> manualRunning;

    private List<String> debugRunning;

    private Long timestamp;

    private String date;

    private String host;

    /**
     * cpu load per core等于最近1分钟系统的平均cpu负载÷cpu核心数量
     */
    private Float cpuLoadPerCore;

    /**
     * 每个机器的总内存数
     */
    private Float memTotal;

    /**
     * 逻辑cpu核数
     */
    private Integer cores;
}
