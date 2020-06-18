package com.yz.rpc.common.enumeration;

import com.yz.rpc.cluster.api.LoadBalancer;
import com.yz.rpc.cluster.loadBalance.*;
import com.yz.rpc.common.enumeration.support.ExtensionBaseType;

/**
 * 负载均衡枚举类
 * 根据配置信息加载对应的负载均衡策略
 *
 * @author yz
 * create at 2020/4/3
 */
public enum LoadBalanceType implements ExtensionBaseType<LoadBalancer> {
    CONSISTENTHASH(new ConsistentHashLoadBalancer()),   //一致性哈希
    LEASTACTIVE(new LeastActiveLoadBalancer()),         //最小活跃度
    RANDOM(new RandomLoadBalancer()),                   //随机
    WEIGHTEDRANDOM(new WeightedRandomLoadBalancer()),   //加权随机
    ROUNDROBIN(new RoundRobinLoadBalancer());           //轮询

    private LoadBalancer loadBalancer;

    LoadBalanceType(LoadBalancer loadBalancer){
        this.loadBalancer = loadBalancer;
    }

    @Override
    public LoadBalancer getInstance() {
        return loadBalancer;
    }
}
