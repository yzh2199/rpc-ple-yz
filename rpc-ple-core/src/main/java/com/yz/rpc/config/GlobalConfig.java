package com.yz.rpc.config;

import com.yz.rpc.cluster.api.FaultToleranceHandler;
import com.yz.rpc.cluster.api.LoadBalancer;
import com.yz.rpc.executor.api.TaskExecutor;
import com.yz.rpc.protocol.api.Protocol;
import com.yz.rpc.proxy.api.RPCProxyFactory;
import com.yz.rpc.registry.api.ServiceRegistry;
import com.yz.rpc.serialize.api.Serializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 全局配置(单例)
 * 用来维护几个核心配置类(也是单例)的实例
 *
 * @author yz
 * create at 2020/3/18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GlobalConfig {

    private ApplicationConfig applicationConfig;

    private ClusterConfig clusterConfig;

    private ProtocolConfig protocolConfig;

    private RegistryConfig registryConfig;

    public Protocol getProtocol(){
        return protocolConfig.getProtocolInstance();
    }

    /* ----- 获取各个核心配置类中维护的实例 ----- */

    public int getPort(){
        return protocolConfig.getPort();
    }

    public TaskExecutor getClientExecutor(){
        return protocolConfig.getExecutors().getClient().getTaskExecutorInstance();
    }

    public TaskExecutor getServerExecutor(){
        return protocolConfig.getExecutors().getServer().getTaskExecutorInstance();
    }

    public LoadBalancer getLoadBalancer(){
        return clusterConfig.getLoadBalancerInstance();
    }

    public FaultToleranceHandler getFaultToleranceHandler(){
        return clusterConfig.getFaultToleranceHandlerInstance();
    }

    public Serializer getSerializer(){
        return applicationConfig.getSerializerInstance();
    }

    public ServiceRegistry getServiceRegistry(){
        return registryConfig.getServiceRegistryInstance();
    }

    public RPCProxyFactory getProxyFactory(){
        return applicationConfig.getRPCProxyFactoryInstance();
    }
}
