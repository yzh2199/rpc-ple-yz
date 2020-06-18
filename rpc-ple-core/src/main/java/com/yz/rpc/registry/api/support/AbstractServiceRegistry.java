package com.yz.rpc.registry.api.support;

import com.yz.rpc.config.RegistryConfig;
import com.yz.rpc.registry.api.ServiceRegistry;

/**
 * 抽象的服务注册中心,只提供registry配置对象
 *
 * @author yz
 * create at 2020/3/26
 */
public abstract class AbstractServiceRegistry implements ServiceRegistry {

    protected RegistryConfig registryConfig;

    public void setRegistryConfig(RegistryConfig registryConfig){
        this.registryConfig = registryConfig;
    }
}
