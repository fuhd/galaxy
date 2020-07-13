package com.dfire.core.tool.pool;

import com.dfire.config.HeraGlobalEnv;
import com.dfire.logs.ErrorLog;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;

/**
 * @Description : Druid连接池
 * @Author ： HeGuoZi
 * @Date ： 10:04 2018/8/24
 * @Modified :
 */
public abstract class AbstractDataSourcePool {

    private volatile boolean isClose;

    private HikariDataSource dataSource;

    public AbstractDataSourcePool() {
        dataSource = new HikariDataSource();
        dataSource.setDriverClassName(HeraGlobalEnv.getSparkDriver());
        dataSource.setJdbcUrl(HeraGlobalEnv.getSparkAddress());
        dataSource.setUsername(HeraGlobalEnv.getSparkUser());
        dataSource.setPassword(HeraGlobalEnv.getSparkPassword());
        dataSource.setMinimumIdle(5);
        dataSource.setIdleTimeout(180000);
        dataSource.setMaximumPoolSize(5);
        dataSource.setAutoCommit(true);
        dataSource.setMaxLifetime(1800000);
        dataSource.setConnectionTimeout(30000);
        dataSource.setPoolName("GalaxySparkHikariCP");
        isClose = false;
    }

    public Connection getConnection() {
        if (isClose || dataSource == null) {
            return null;
        }
        try {
            return dataSource.getConnection();
        } catch (Exception e) {
            ErrorLog.error("获取连接失败", e);
            return null;
        }
    }

    public void close() {
        if (!isClose) {
            isClose = true;
            if (dataSource != null) {
                dataSource.close();
                dataSource = null;
            }
        }
    }

    public boolean isClose() {
        return isClose;
    }

}
