package com.yz.rpc.cluster.loadBalance;

import com.yz.rpc.cluster.api.support.AbstractLoadBalancer;
import com.yz.rpc.common.domain.RPCRequest;
import com.yz.rpc.protocol.api.Invoker;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机负载均衡算法
 *
 * @author yz
 * create at 2020/3/27
 */
public class RandomLoadBalancer extends AbstractLoadBalancer {
    @Override
    protected Invoker doSelect(List<Invoker> availableInvokers, RPCRequest request) {
        return availableInvokers.get(ThreadLocalRandom.current().nextInt(availableInvokers.size()));
    }
}
