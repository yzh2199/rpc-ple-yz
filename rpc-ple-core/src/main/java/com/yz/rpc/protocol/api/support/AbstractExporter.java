package com.yz.rpc.protocol.api.support;

import com.yz.rpc.config.ServiceConfig;
import com.yz.rpc.protocol.api.Exporter;
import com.yz.rpc.protocol.api.Invoker;

/**
 * 抽象的Exporter对象
 * 封装:
 * 1)暴露之后的服务调用者
 * 2)该服务对应的ServiceConfig
 *
 * @author yz
 * create at 2020/3/20
 */
public abstract class AbstractExporter<T> implements Exporter<T> {

    protected Invoker<T> invoker;

    protected ServiceConfig<T> serviceConfig;

    public void setInvoker(Invoker<T> invoker) {
        this.invoker = invoker;
    }

    public void setServiceConfig(ServiceConfig<T> serviceConfig) {
        this.serviceConfig = serviceConfig;
    }

    @Override
    public Invoker<T> getInvoker() {
        return invoker;
    }

    @Override
    public ServiceConfig<T> getServiceConfig() {
        return serviceConfig;
    }
}
