package com.starriverdata.core.route.loadbalance;

import com.starriverdata.common.entity.vo.HeraHostGroupVo;
import com.starriverdata.common.exception.HostGroupNotExistsException;
import com.starriverdata.common.vo.JobElement;
import com.starriverdata.config.HeraGlobalEnv;
import com.starriverdata.core.message.HeartBeatInfo;
import com.starriverdata.core.netty.master.MasterContext;
import com.starriverdata.core.netty.master.MasterWorkHolder;
import com.starriverdata.core.route.check.ResultReason;
import com.starriverdata.logs.MasterLog;

/**
 * 任务执行worker选择路由
 */
public abstract class AbstractLoadBalance implements LoadBalance {

    @Override
    public MasterWorkHolder select(JobElement jobElement, MasterContext masterContext) throws HostGroupNotExistsException {
        if (masterContext.getHostGroupCache() != null) {
            HeraHostGroupVo hostGroup = masterContext.getHostGroupCache().get(jobElement.getHostGroupId());
            if (hostGroup == null) {
                throw new HostGroupNotExistsException("机器组ID:" + jobElement.getHostGroupId() + "不存在,任务Id:" + jobElement.getJobId());
            }
            if (hostGroup.getHosts() == null || hostGroup.getHosts().size() == 0) {
                throw new HostGroupNotExistsException("机器组:[" + hostGroup.getName() + "]无存活的work,请在work管理页面配置work,并启动它,任务Id:" + jobElement.getJobId());
            }
            return doSelect(hostGroup, masterContext);
        }
        return null;
    }

    protected boolean check(MasterWorkHolder worker) {
        if (worker == null) {
            MasterLog.warn(ResultReason.NULL_WORKER.getMsg());
            return false;
        }
        if (worker.getHeartBeatInfo() == null) {
            MasterLog.warn(ResultReason.NULL_HEART.getMsg());
            return false;
        }
        HeartBeatInfo heartBeatInfo = worker.getHeartBeatInfo();

        if (heartBeatInfo.getMemRate() == null || heartBeatInfo.getMemRate() > HeraGlobalEnv.getMaxMemRate()) {
            MasterLog.warn(ResultReason.MEM_LIMIT.getMsg() + ":{}, host:{}", heartBeatInfo.getMemRate(), heartBeatInfo.getHost());
            return false;
        }
        if (heartBeatInfo.getCpuLoadPerCore() == null || heartBeatInfo.getCpuLoadPerCore() > HeraGlobalEnv.getMaxCpuLoadPerCore()) {
            MasterLog.warn(ResultReason.LOAD_LIMIT.getMsg() + ":{}, host:{}", heartBeatInfo.getCpuLoadPerCore(), heartBeatInfo.getHost());
            return false;
        }

        // 配置计算数量
        Float assignTaskNum = (heartBeatInfo.getMemTotal() - HeraGlobalEnv.getSystemMemUsed()) / HeraGlobalEnv.getPerTaskUseMem();
        int sum = heartBeatInfo.getDebugRunning().size() + heartBeatInfo.getManualRunning().size() + heartBeatInfo.getRunning().size();
        if (sum > assignTaskNum.intValue()) {
            MasterLog.warn(ResultReason.TASK_LIMIT.getMsg() + ":{}, host:{}", sum, heartBeatInfo.getHost());
            return false;
        }
        return true;
    }

    protected abstract MasterWorkHolder doSelect(HeraHostGroupVo hostGroup, MasterContext masterContext);
}
