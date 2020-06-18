package com.yz.rpc.invocation.api.support;

import com.yz.rpc.common.domain.RPCRequest;
import com.yz.rpc.common.domain.RPCResponse;
import com.yz.rpc.common.enumeration.ExceptionEnum;
import com.yz.rpc.common.exception.RPCException;
import com.yz.rpc.config.ReferenceConfig;
import com.yz.rpc.invocation.api.Invocation;
import com.yz.rpc.protocol.api.InvokeParam;
import com.yz.rpc.protocol.api.support.RPCInvokeParam;

import java.util.concurrent.Future;
import java.util.function.Function;

/**
 * 抽象调用方式
 *
 * @author yz
 * create at 2020/3/25
 */
public abstract class AbstractInvocation implements Invocation {
    /**
     * 参数为RPC请求和逻辑处理函数
     * 利用不同的调用方式来调用逻辑处理函数返回的Future对象,从而达到不同的调用效果
     *
     * @param invokeParam      调用参数
     * @param requestProcessor 请求逻辑处理函数
     * @return 调用结果
     * @throws RPCException 异常
     */
    @Override
    public RPCResponse invoke(InvokeParam invokeParam, Function<RPCRequest, Future<RPCResponse>> requestProcessor) throws RPCException {
        RPCResponse response;
        //从调用参数中取出RPC请求对象和引用服务配置对象
        RPCRequest request = ((RPCInvokeParam)invokeParam).getRpcRequest();
        ReferenceConfig referenceConfig = ((RPCInvokeParam)invokeParam).getReferenceConfig();
        try{
            response = doInvoke(request,referenceConfig,requestProcessor);
        }catch (Throwable t){
            t.printStackTrace();
            throw new RPCException(ExceptionEnum.TRANSPORT_FAILURE,"TRANSPORT层出现异常");
        }
        return response;
    }

    /* 具体调用方法 */
    protected abstract RPCResponse doInvoke(RPCRequest request,ReferenceConfig referenceConfig,Function<RPCRequest, Future<RPCResponse>> requestProcessor) throws Throwable;
}
