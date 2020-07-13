package com.starriverdata.core.filter;

import com.alibaba.fastjson.JSONObject;
import com.starriverdata.common.config.ExecuteFilter;
import com.starriverdata.common.constants.Constants;
import com.starriverdata.common.entity.HeraJobHistory;
import com.starriverdata.common.service.HeraJobHistoryService;
import com.starriverdata.common.util.HttpUtils;
import com.starriverdata.common.util.NamedThreadFactory;
import com.starriverdata.common.util.StringUtil;
import com.starriverdata.common.vo.JobElement;
import com.starriverdata.logs.ErrorLog;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * desc:
 *
 * @author scx
 * @create 2019/12/03
 */

//@Filter("yarnMonitorFilter")
public class YarnMonitorFilter implements ExecuteFilter {


    private final String APP_ID = "id";
    private final String NAME = "name";
    private final String ALLOCATED_MB = "allocatedMB";
    private final String ALLOCATED_V_CORES = "allocatedVCores";
    private final String CLUSTER_USAGE_PERCENTAGE = "clusterUsagePercentage";
    private final String QUEUE_USAGE_PERCENTAGE = "queueUsagePercentage";
    private final String MEMORY_SECONDS = "memorySeconds";
    private final String V_CORE_SECONDS = "vcoreSeconds";


    @Autowired
    private HeraJobHistoryService historyService;

    private ScheduledThreadPoolExecutor checkSchedule;

    private LinkedBlockingQueue<Long> jobQueue;

    private LinkedList<String> appQueue;


    @PostConstruct
    public void init() {
        jobQueue = new LinkedBlockingQueue<>();
        appQueue = new LinkedList<>();
        checkSchedule =
                new ScheduledThreadPoolExecutor(1, new NamedThreadFactory("yarn-monitor", true));
        checkSchedule.scheduleWithFixedDelay(() -> {
            for (Long id : new ArrayList<>(jobQueue)) {
                HeraJobHistory history = historyService.findPropertiesById(id);
                Map<String, String> properties = StringUtil.convertStringToMap(history.getProperties());
                String appId;
                if ((appId = properties.get(Constants.APP_ID)) != null) {
                    jobQueue.remove(id);
                    appQueue.add(appId);
                }
            }
            for (String appId : appQueue) {
                Map<String, String> resMap = parseAppLog(HttpUtils.doGet("http://172.30.68.120:5004/ws/v1/cluster/apps/" + appId, null));

            }

        }, 30, 30, TimeUnit.SECONDS);


    }

    @Override
    public void onExecute(JobElement element) {
        try {
            jobQueue.put(element.getHistoryId());
        } catch (InterruptedException e) {
            ErrorLog.error("添加历史ID异常", e);
        }
    }

    @Override
    public void onResponse(JobElement element) {
        if (jobQueue.contains(element.getHistoryId())) {
            jobQueue.remove(element.getHistoryId());
            HeraJobHistory history = historyService.findPropertiesById(element.getHistoryId());
            Map<String, String> properties = StringUtil.convertStringToMap(history.getProperties());
            String appId;
            if ((appId = properties.get(Constants.APP_ID)) != null) {
                Map<String, String> resMap = parseAppLog(HttpUtils.doGet("http://172.30.68.120:5004/ws/v1/cluster/apps/" + appId, null));


            }

        }
    }


    public Map<String, String> parseAppLog(String log) {
        if (StringUtils.isBlank(log)) {
            return null;
        }
        Map<String, String> res = new HashMap<>(7);
        JSONObject appEntity = JSONObject.parseObject(log).getJSONObject("app");
        res.put(APP_ID, appEntity.getString(APP_ID));
        res.put(ALLOCATED_MB, appEntity.getString(ALLOCATED_MB));
        res.put(ALLOCATED_V_CORES, appEntity.getString(ALLOCATED_V_CORES));
        res.put(CLUSTER_USAGE_PERCENTAGE, appEntity.getString(CLUSTER_USAGE_PERCENTAGE));
        res.put(QUEUE_USAGE_PERCENTAGE, appEntity.getString(QUEUE_USAGE_PERCENTAGE));
        res.put(MEMORY_SECONDS, appEntity.getString(MEMORY_SECONDS));
        res.put(V_CORE_SECONDS, appEntity.getString(V_CORE_SECONDS));
        res.put(NAME, appEntity.getString(NAME));
        return res;
    }

}
