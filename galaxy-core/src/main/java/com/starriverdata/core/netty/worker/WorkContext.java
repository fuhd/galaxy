package com.starriverdata.core.netty.worker;

import com.starriverdata.common.service.*;
import com.starriverdata.common.util.NamedThreadFactory;
import com.starriverdata.config.HeraGlobalEnv;
import com.starriverdata.core.job.Job;
import com.starriverdata.core.netty.HeraChannel;
import com.starriverdata.core.tool.RunShell;
import com.starriverdata.core.util.NetUtils;
import com.starriverdata.logs.ErrorLog;
import com.starriverdata.logs.GalaxyLog;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

@Data
@NoArgsConstructor
@Component
public class WorkContext {

    private static final String loadStr = "cat /proc/cpuinfo |grep processor | wc -l";
    public static String host;
    public static Integer cpuCoreNum;

    public static Map<String, String> cacheDBMap;

    static {
        host = NetUtils.getLocalAddress();
        GalaxyLog.info("-----------------------------当前机器的IP为:{}-----------------------------", host);
        if (HeraGlobalEnv.isLinuxSystem()) {
            RunShell shell = new RunShell(loadStr);
            Integer exitCode = shell.run();
            if (exitCode == 0) {
                try {
                    cpuCoreNum = Integer.parseInt(shell.getResult());
                } catch (IOException e) {
                    ErrorLog.error("获取cpu核数失败，使用默认配置", e);
                    cpuCoreNum = 4;
                }
            }
        } else {
            cpuCoreNum = 4;
        }
    }

    public String serverHost;
    @Autowired
    private HeraDebugHistoryService heraDebugHistoryService;
    @Autowired
    private HeraJobHistoryService heraJobHistoryService;
    @Autowired
    @Qualifier("heraGroupMemoryService")
    private HeraGroupService heraGroupService;
    @Autowired
    private HeraJobActionService heraJobActionService;
    @Autowired
    @Qualifier("heraFileMemoryService")
    private HeraFileService heraFileService;
    @Autowired
    private HeraProfileService heraProfileService;
    @Autowired
    @Qualifier("heraJobMemoryService")
    private HeraJobService heraJobService;
    private HeraChannel serverChannel;
    private Map<HistoryPair, Job> running = new ConcurrentHashMap<>();
    private Map<HistoryPair, Job> manualRunning = new ConcurrentHashMap<>();
    private Map<Long, Job> debugRunning = new ConcurrentHashMap<>();
    private WorkHandler handler;
    private WorkClient workClient;
    /**
     * 处理web 异步请求
     */
    private ExecutorService workWebThreadPool = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 1L, TimeUnit.MINUTES,
            new SynchronousQueue<>(), new NamedThreadFactory("worker-web"), new ThreadPoolExecutor.AbortPolicy());
    /**
     * 执行任务
     */
    private ExecutorService workExecuteThreadPool = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 1L, TimeUnit.MINUTES,
            new SynchronousQueue<>(), new NamedThreadFactory("worker-execute"), new ThreadPoolExecutor.AbortPolicy());

    public void init() {
        //TODO 可以在这里接入统一数据源或者全局的自定义配置。
        cacheDBMap = new HashMap<>(0);
    }


}
