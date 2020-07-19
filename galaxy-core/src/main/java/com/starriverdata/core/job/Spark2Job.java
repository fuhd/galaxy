package com.starriverdata.core.job;

import com.starriverdata.common.constants.RunningJobKeyConstant;
import com.starriverdata.core.tool.pool.JdbcDataSourcePool;
import com.starriverdata.logs.ErrorLog;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 * Thrift spark job
 */
public class Spark2Job extends ProcessJob {

    private static JdbcDataSourcePool jdbcDataSourcePool;

    private final int maxOutputNum = 2000;

    public Spark2Job(JobContext jobContext) {
        super(jobContext);
        jobContext.getProperties().setProperty(RunningJobKeyConstant.JOB_RUN_TYPE, "Spark2Job");
    }

    @Override
    public int run() throws Exception {
        return runInner();
    }

    private Integer runInner() {
        String script = getProperties().getLocalProperty(RunningJobKeyConstant.JOB_SCRIPT);
        int last = 0;
        for (int now = 0; now < script.length() && last < script.length(); now++) {
            if (last <= now - 1) {
                if (";".equals(script.substring(now, now + 1))) {
                    if (!executeAndPrint(script, last, now)) {
                        return -999;
                    }
                    last = now + 1;
                } else if (now == script.length() - 1) {
                    if (!executeAndPrint(script, last, now + 1)) {
                        return -999;
                    }
                }
            }
        }
        return 0;
    }

    private boolean executeAndPrint(String script, int startPoint, int endPoint) {
        if (jdbcDataSourcePool == null) {
            synchronized (Spark2Job.class) {
                if (jdbcDataSourcePool == null) {
                    jdbcDataSourcePool = new JdbcDataSourcePool();
                }
            }
        }
        Connection connection = jdbcDataSourcePool.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(script.substring(startPoint, endPoint));
            int columnCount = resultSet.getMetaData().getColumnCount();
            int rowNum = 0;
            while (resultSet.next()) {
                String line = "";
                for (int i = 1; i <= columnCount; i++) {
                    line += resultSet.getString(i) + " ";
                }
                logConsole(line);
                rowNum++;
                if (rowNum == maxOutputNum) {
                    logConsole("The part of rows exceed " + maxOutputNum + ", won't be displayed");
                    break;
                }
            }
            stmt.close();
            resultSet.close();
        } catch (Exception e) {
            log("执行或打印结果错误");
            return false;
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                ErrorLog.error("连接归还失败", e);
            }
        }
        return true;
    }

    @Override
    public List<String> getCommandList() {
        //该方法此处无用(不会被调到)
        return null;
    }
}
