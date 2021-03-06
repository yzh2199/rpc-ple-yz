package com.yz.rpc.protocol.arc10;

import com.yz.rpc.common.domain.RPCRequest;
import com.yz.rpc.common.domain.RPCResponse;
import com.yz.rpc.protocol.api.support.AbstractRemoteInvoker;

import java.util.concurrent.Future;
import java.util.function.Function;

/**
 * 具体协议的调用者
 * 面向Arc协议的Invoker
 *
 * @author yz
 * create at 2020/3/21
 */
public class ArcInvoker<T> extends AbstractRemoteInvoker<T> {
    /**
     * 函数式编程,返回一个函数对象
     * 该函数的输入为RPC调用请求,输出为计算RPC调用结果的Future对象
     * 由具体的invoker来重写此方法,实现函数的内部逻辑
     *
     * @return Function
     */
    @Override
    protected Function<RPCRequest, Future<RPCResponse>> getProcessor() {
        //返回一个和传输层交互的函数
        return request -> getClient().submit(request);
    }
}
