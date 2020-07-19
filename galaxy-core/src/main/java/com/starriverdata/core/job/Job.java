package com.starriverdata.core.job;


/**
 * 统一job类型接口
 */
public interface Job {

    int run() throws Exception;

    void cancel();

    boolean isCanceled();

    JobContext getJobContext();


}
