package com.starriverdata.core.job;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DownLoadJob extends AbstractJob {

    public DownLoadJob(JobContext jobContext) {
        super(jobContext);
    }

    @Override
    public int run() throws Exception {
        List<Job> jobList = new ArrayList<>();
        for (Map<String, String> map : jobContext.getResources()) {
            if (map.get("uri") != null) {
                String uri = map.get("uri");
                String name = map.get("name");
                String localPath = name == null ? uri.substring(uri.lastIndexOf("/") + 1) : name;
                jobList.add(new DownloadHadoopFileJob(jobContext, uri, localPath));
            }
        }
        int exitCode = 0;
        for (Job job : jobList) {
            if (!canceled) {
                exitCode = job.run();
            }
        }
        return exitCode;
    }

    @Override
    public void cancel() {
        canceled = true;
    }

}
