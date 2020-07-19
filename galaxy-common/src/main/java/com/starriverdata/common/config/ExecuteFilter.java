package com.starriverdata.common.config;

import com.starriverdata.common.vo.JobElement;

public interface ExecuteFilter {

    void onExecute(JobElement element);

    void onResponse(JobElement element);

}
