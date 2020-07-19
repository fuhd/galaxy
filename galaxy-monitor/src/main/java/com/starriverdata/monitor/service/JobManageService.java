package com.starriverdata.monitor.service;


import com.starriverdata.common.entity.model.JsonResponse;

public interface JobManageService {



    /**
     * 进入任务详情查询
     */
    JsonResponse findJobHistoryByStatus(String status, String begindt,String enddt);
    
    /**
     * 查询任务运行时长top10
     */
    JsonResponse findJobRunTimeTop10();

    /**
     * 今日所有任务状态，首页饼图
     */
    JsonResponse findAllJobStatus();

    /**
     * 今日任务详情明细，初始化曲线图
     */
    JsonResponse findAllJobStatusDetail();
}
