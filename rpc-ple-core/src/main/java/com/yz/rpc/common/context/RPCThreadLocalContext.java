package com.yz.rpc.common.context;

import com.yz.rpc.protocol.api.Invoker;

import java.util.concurrent.Future;

/**
 * 一个线程私有的类,存储一些线程私有的对象(使用ThreadLocal实现):
 * 1)protocol层面的invoker
 * 2)每个invoker对应的Future对象
 *
 * @author yz
 * create at 2020/3/19
 */
public class RPCThreadLocalContext {
    private static final ThreadLocal<RPCThreadLocalContext> RPC_CONTEXT = new ThreadLocal<>(){
        @Override
        protected RPCThreadLocalContext initialValue() {
            return new RPCThreadLocalContext();
        }
    };

    //从threadLocal中取出当前线程对应的RPCThreadLocalContext对象
    public static RPCThreadLocalContext getContext(){
        return RPC_CONTEXT.get();
    }

    private Invoker invoker;

    private Future future;

    public void setInvoker(Invoker invoker){
        this.invoker = invoker;
    }

    public void setFuture(Future future){
        this.future = future;
    }

    public Invoker getInvoker(){
        return invoker;
    }

    public Future getFuture(){
        return future;
    }
}
