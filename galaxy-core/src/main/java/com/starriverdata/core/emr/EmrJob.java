package com.starriverdata.core.emr;

public interface EmrJob {

    /**
     * 添加任务接口
     */
    void addJob(String owner);

    /**
     * 移除任务
     */
    void removeJob(String owner);

}
