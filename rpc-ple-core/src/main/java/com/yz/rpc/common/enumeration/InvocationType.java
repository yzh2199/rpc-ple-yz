package com.yz.rpc.common.enumeration;

import com.yz.rpc.config.ReferenceConfig;
import com.yz.rpc.invocation.api.Invocation;
import com.yz.rpc.invocation.async.AsyncInvocation;
import com.yz.rpc.invocation.callback.CallbackInvocation;
import com.yz.rpc.invocation.oneway.OneWayInvocation;
import com.yz.rpc.invocation.sync.SyncInvocaton;
import com.yz.rpc.protocol.api.InvokeParam;
import com.yz.rpc.protocol.api.support.RPCInvokeParam;

/**
 * 调用方式枚举类
 * 根据@RPCReference注解属性来选择枚举类
 *
 * @author yz
 * create at 2020/3/30
 */
public enum InvocationType {
    SYNC(new SyncInvocaton()),              //同步调用
    ASYNC(new AsyncInvocation()),           //异步调用
    CALLBACK(new CallbackInvocation()),     //回调方式
    ONEWAY(new OneWayInvocation());         //oneWay调用

    private Invocation invocation;

    InvocationType(Invocation invocation){
        this.invocation = invocation;
    }

    public static Invocation get(InvokeParam invokeParam) {
        ReferenceConfig referenceConfig = ((RPCInvokeParam)invokeParam).getReferenceConfig();
        if(referenceConfig.isAsync()){
            return ASYNC.invocation;
        }else if(referenceConfig.isCallback()){
            return CALLBACK.invocation;
        }else if(referenceConfig.isOneWay()){
            return ONEWAY.invocation;
        }else{
            return SYNC.invocation;
        }
    }
}
