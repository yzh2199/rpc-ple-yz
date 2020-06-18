package com.yz.rpc.protocol.api.support;

import com.yz.rpc.common.domain.RPCResponse;
import com.yz.rpc.protocol.api.InvokeParam;
import com.yz.rpc.protocol.api.Invoker;

/**
 * 封装了Filter链的Invoker对象
 * 提供一个抽象的invoke方法,用于操作Filter链
 *
 * @author yz
 * create at 2020/3/21
 */
public abstract class DelegateInvoker<T> extends AbstractInvoker<T>{
    private Invoker<T> delegate;

    public DelegateInvoker(Invoker<T> invoker){
        this.delegate = invoker;
    }

    public Invoker<T> getDelegate(){
        return delegate;
    }

    /* 用于处理 filter 链的抽象invoke方法 */
    public abstract RPCResponse invoke(InvokeParam invokeParam);
}
