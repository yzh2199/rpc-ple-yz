package com.yz.rpc.common.enumeration;

import com.yz.rpc.common.enumeration.support.ExtensionBaseType;
import com.yz.rpc.proxy.api.RPCProxyFactory;
import com.yz.rpc.proxy.jdk.JDKProxyFactory;

/**
 * 代理工厂枚举类
 * 目前只实现了jdk方式
 *
 * @author yz
 * create at 2020/4/3
 */
public enum ProxyFactoryType implements ExtensionBaseType<RPCProxyFactory> {
    JDK(new JDKProxyFactory());     //jdk实现的动态代理工厂

    private RPCProxyFactory proxyFactory;

    ProxyFactoryType(RPCProxyFactory proxyFactory){
        this.proxyFactory = proxyFactory;
    }

    @Override
    public RPCProxyFactory getInstance() {
        return proxyFactory;
    }
}
