package com.yz.rpc.cluster.loadBalance;

import com.yz.rpc.cluster.api.support.AbstractLoadBalancer;
import com.yz.rpc.common.domain.RPCRequest;
import com.yz.rpc.protocol.api.Invoker;

import java.util.List;

/**
 * 轮询负载均衡算法
 *
 * @author yz
 * create at 2020/3/27
 */
public class RoundRobinLoadBalancer extends AbstractLoadBalancer {
    private int index = 0;     //轮询负载均衡算法中,当前应当被选择的服务器索引

    @Override
    protected synchronized Invoker doSelect(List<Invoker> availableInvokers, RPCRequest request) {
        //定义为同步方法,保证index的正确性,防止多线程同时请求轮询时并发更新index
        Invoker invoker = availableInvokers.get(index);
        index = (index+1)%availableInvokers.size();
        return invoker;
    }

}
