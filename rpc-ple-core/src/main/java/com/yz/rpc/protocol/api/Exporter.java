package com.yz.rpc.protocol.api;

import com.yz.rpc.config.ServiceConfig;

/**
 * 服务暴露之后的抽象调用接口,实质上就是将Invoker和ServiceConfig进行了封装
 *
 * @author yz
 * create at 2020/3/18
 */
public interface Exporter<T> {

    Invoker<T> getInvoker();

    ServiceConfig<T> getServiceConfig();
}
