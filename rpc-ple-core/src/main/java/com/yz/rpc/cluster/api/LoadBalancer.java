package com.yz.rpc.cluster.api;

import com.yz.rpc.common.domain.RPCRequest;
import com.yz.rpc.config.ReferenceConfig;
import com.yz.rpc.protocol.api.Invoker;

import java.util.List;

/**
 * 负载均衡器
 * 利用指定的负载均衡策略，在服务列表中选择一个
 *
 * @author yz
 * create at 2020/3/18
 */
public interface LoadBalancer {
    /**
     * 根据referenceConfig创建一个对应的ClusterInvoker
     */
    //todo:这个函数为什么要放在本类中，放入ClusterConfig不是更好吗
    <T> Invoker<T> referCluster(ReferenceConfig<T> referenceConfig);

    /**
     * 利用某种负载均衡策略选择一个抽象服务调用者
     * @param availableInvokers 可用服务列表
     *
     * @return Invoker对象
     */
    Invoker select(List<Invoker> availableInvokers, RPCRequest request);
}
