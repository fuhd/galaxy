package com.starriverdata.core.route.loadbalance;

import com.starriverdata.config.HeraGlobalEnv;
import com.starriverdata.core.route.loadbalance.impl.RandomLoadBalance;
import com.starriverdata.core.route.loadbalance.impl.RoundRobinLoadBalance;


/**
 * 负载均衡实例工厂
 */
public class LoadBalanceFactory {

    public static LoadBalance getLoadBalance() {

        if (RoundRobinLoadBalance.NAME.equals(HeraGlobalEnv.getLoadBalance())) {
            return new RoundRobinLoadBalance();
        }

        if (RandomLoadBalance.NAME.equals(HeraGlobalEnv.getLoadBalance())) {
            return new RandomLoadBalance();
        }

        return new RoundRobinLoadBalance();
    }

}
