package com.starriverdata.core.emr;

public interface Emr {

    /**
     * 获得登录命令
     */
    String getLogin(String user,String owner);

    /**
     * 获得固定集群的登录方式
     */
    String getFixLogin(String host);

    /**
     * 随机获得一台IP
     */
    String getIp(String owner);

}
