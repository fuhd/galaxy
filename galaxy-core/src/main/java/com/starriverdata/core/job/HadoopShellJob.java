package com.starriverdata.core.job;

import com.starriverdata.common.constants.RunningJobKeyConstant;

public class HadoopShellJob extends ShellJob {

    public HadoopShellJob(JobContext jobContext) {
        super(jobContext);
        jobContext.getProperties().setProperty(RunningJobKeyConstant.JOB_RUN_TYPE, "HadoopShellJob");
    }

    @Override
    public int run() throws Exception {
        return super.run();
    }
}
