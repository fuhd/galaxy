package com.starriverdata.core.job;

import com.alibaba.fastjson.JSONObject;
import com.starriverdata.common.constants.Constants;
import com.starriverdata.config.HeraGlobalEnv;
import com.starriverdata.logs.MonitorLog;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DownloadHadoopFileJob extends ProcessJob {

    private String hadoopPath;
    private String localPath;

    public DownloadHadoopFileJob(JobContext jobContext, String hadoopPath, String localPath) {
        super(jobContext);
        this.hadoopPath = hadoopPath;
        this.localPath = localPath;
    }

    @Override
    public List<String> getCommandList() {
        List<String> commands = new ArrayList<>();
        if (HeraGlobalEnv.isEmrJob()) {
            File file = new File(localPath);
            //创建文件  + copyToLocal 放在一行
            String workDir = Constants.TMP_PATH + File.separator + "hera";
            String dirAndCopyToLocal = getProperty(Constants.EMR_SELECT_WORK) + " \"" +
                    "mkdir -p " + workDir +
                    " & " + "hadoop fs -copyToLocal " + hadoopPath + " " + workDir + File.separator + file.getName() + "\"";
            commands.add(dirAndCopyToLocal);
        } else {
            if (StringUtils.isNotBlank(HeraGlobalEnv.getKerberosKeytabPath()) && StringUtils.isNotBlank(HeraGlobalEnv.getKerberosPrincipal())) {
                commands.add("kinit -kt " + HeraGlobalEnv.getKerberosKeytabPath() + " " + HeraGlobalEnv.getKerberosPrincipal());
            }
            commands.add("hadoop fs -copyToLocal " + hadoopPath + " " + localPath);
        }
        MonitorLog.debug("组装后的命令为:" + JSONObject.toJSONString(commands));
        return commands;
    }
}
