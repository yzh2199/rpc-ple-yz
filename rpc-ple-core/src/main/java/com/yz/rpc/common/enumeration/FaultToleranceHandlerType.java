package com.yz.rpc.common.enumeration;

import com.yz.rpc.cluster.api.FaultToleranceHandler;
import com.yz.rpc.cluster.faultTolerance.FailFastFaultToleranceHandler;
import com.yz.rpc.cluster.faultTolerance.FailOverFaultToleranceHandler;
import com.yz.rpc.cluster.faultTolerance.FailSafeFaultToleranceHandler;
import com.yz.rpc.common.enumeration.support.ExtensionBaseType;

/**
 * 集群容错枚举类
 * 根据配置信息加载对应的集群容错处理器
 *
 * @author yz
 * create at 2020/4/3
 */
public enum FaultToleranceHandlerType implements ExtensionBaseType<FaultToleranceHandler> {
    FAILOVER(new FailOverFaultToleranceHandler()),      //失败切换
    FAILFAST(new FailFastFaultToleranceHandler()),      //快速失败
    FAILSAFE(new FailSafeFaultToleranceHandler());      //安全失败

    private FaultToleranceHandler faultToleranceHandler;

    FaultToleranceHandlerType(FaultToleranceHandler faultToleranceHandler){
        this.faultToleranceHandler = faultToleranceHandler;
    }

    @Override
    public FaultToleranceHandler getInstance() {
        return faultToleranceHandler;
    }
}
