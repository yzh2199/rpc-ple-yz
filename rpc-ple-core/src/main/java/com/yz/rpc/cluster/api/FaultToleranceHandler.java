package com.yz.rpc.cluster.api;

import com.yz.rpc.cluster.api.support.ClusterInvoker;
import com.yz.rpc.common.domain.RPCResponse;
import com.yz.rpc.common.exception.RPCException;
import com.yz.rpc.protocol.api.InvokeParam;

/**
 * 集群容错处理器
 * 调用失败后的处理机制
 * 常见的机制有failover(失败自动切换)、failfast(快速失败)、failsafe(安全失败)等
 * 注意:容错机制只对同步调用有效,因为异步、callback调用和oneWay调用都是直接返回null
 *
 * @author yz
 * create at 2020/3/17
 */
public interface FaultToleranceHandler {

    /**
     * 集群容错处理方法,配置不同的容错机制有不同的实现
     * @param clusterInvoker ClusterInvoker对象
     * @param invokeParam invoker参数
     * @param e 调用过程抛出的异常
     * @return 经容错机制处理后的调用结果
     */
    RPCResponse handle(ClusterInvoker clusterInvoker, InvokeParam invokeParam, RPCException e);
}
