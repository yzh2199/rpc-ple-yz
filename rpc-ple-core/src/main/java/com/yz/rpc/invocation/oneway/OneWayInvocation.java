package com.yz.rpc.invocation.oneway;

import com.yz.rpc.common.domain.RPCRequest;
import com.yz.rpc.common.domain.RPCResponse;
import com.yz.rpc.config.ReferenceConfig;
import com.yz.rpc.invocation.api.support.AbstractInvocation;

import java.util.concurrent.Future;
import java.util.function.Function;

/**
 * oneWay方式调用,即调用方不关心调用结果,提交完请求就直接返回null
 *
 * @author yz
 * create at 2020/3/25
 */
public class OneWayInvocation extends AbstractInvocation {
    @Override
    protected RPCResponse doInvoke(RPCRequest request, ReferenceConfig referenceConfig, Function<RPCRequest, Future<RPCResponse>> requestProcessor) throws Throwable {
        requestProcessor.apply(request);
        return null;
    }
}
