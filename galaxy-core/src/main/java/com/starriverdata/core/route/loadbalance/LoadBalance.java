package com.starriverdata.core.route.loadbalance;


import com.starriverdata.common.exception.HostGroupNotExistsException;
import com.starriverdata.common.vo.JobElement;
import com.starriverdata.core.netty.master.MasterContext;
import com.starriverdata.core.netty.master.MasterWorkHolder;

/**
 * 负载均衡器
 * @author xiaosuda
 */
public interface LoadBalance {

    /**
     * 选择work
     * @param jobElement
     * @param masterContext
     * @return
     */
    MasterWorkHolder select(JobElement jobElement, MasterContext masterContext) throws HostGroupNotExistsException;

}
