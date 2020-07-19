package com.starriverdata.core.netty.master;

import com.starriverdata.common.vo.JobElement;

public interface RunJob {

    /**
     * 任务执行
     * @param workHolder    选择的机器
     * @param element       任务
     */
    void run( MasterWorkHolder workHolder, JobElement element);

}
