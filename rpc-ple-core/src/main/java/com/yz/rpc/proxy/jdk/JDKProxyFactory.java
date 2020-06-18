package com.yz.rpc.proxy.jdk;

import com.yz.rpc.protocol.api.Invoker;
import com.yz.rpc.proxy.api.support.AbstractProxyFactory;

import java.lang.reflect.Proxy;

/**
 * JDK动态代理实现的代理工厂
 *
 * @author yz
 * create at 2020/3/28
 */
public class JDKProxyFactory extends AbstractProxyFactory {
    @Override
    @SuppressWarnings("unchecked")
    protected <T> T doCreateProxy(Invoker<T> invoker, Class<T> interfaceClass) {
        //生成代理对象
        return (T)Proxy.newProxyInstance(
                interfaceClass.getClassLoader(),
                new Class[]{interfaceClass},
                (proxy, method, args) ->
                    JDKProxyFactory.this.invokeProxyMethod(invoker,method,args)
        );
    }
}
