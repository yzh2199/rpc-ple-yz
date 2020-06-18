package com.yz.rpc.cluster.faultTolerance;

import com.yz.rpc.cluster.api.FaultToleranceHandler;
import com.yz.rpc.cluster.api.support.ClusterInvoker;
import com.yz.rpc.common.domain.RPCResponse;
import com.yz.rpc.common.exception.RPCException;
import com.yz.rpc.protocol.api.InvokeParam;
import lombok.extern.slf4j.Slf4j;

/**
 * 集群容错机制之快速失败
 * RPC调用失败后立即报错且不再重试,通常用于非幂等性的写操作,如新增记录
 *
 * @author yz
 * create at 2020/3/28
 */
@Slf4j
public class FailFastFaultToleranceHandler implements FaultToleranceHandler {
    /**
     * 集群容错处理方法,配置不同的容错机制有不同的实现
     *
     * @param clusterInvoker ClusterInvoker对象
     * @param invokeParam    invoker参数
     * @param e              调用过程抛出的异常
     * @return 经容错机制处理后的调用结果
     */
    @Override
    public RPCResponse handle(ClusterInvoker clusterInvoker, InvokeParam invokeParam, RPCException e) {
        log.error("调用出现异常(集群容错:failfast),requestID:{}",invokeParam.getRequestID());
        throw e;
    }
}
