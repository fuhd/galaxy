package com.starriverdata.common.vo;

import lombok.Data;

import java.util.List;

@Data
public class WorkInfoVo {

    /**
     * 机器信息
     */
    private List<MachineInfoVo> machineInfo;
    /**
     * 系统信息
     */
    private OSInfoVo osInfo;
    /**
     * 进程监控
     */
    private List<ProcessMonitorVo> processMonitor;

}
