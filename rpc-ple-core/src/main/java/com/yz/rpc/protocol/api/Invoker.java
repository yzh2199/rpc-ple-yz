package com.yz.rpc.protocol.api;

import com.yz.rpc.common.domain.RPCResponse;
import com.yz.rpc.common.exception.RPCException;
import com.yz.rpc.registry.api.ServiceURL;

/**
 * 抽象调用者
 * @param <T>
 *
 * @author yz
 * create at 2020/3/16
 */
public interface Invoker<T> {
    Class<T> getInterfaceClass();

    String getInterfaceName();

    ServiceURL getServiceURL();

    /**
     * 调用服务，返回调用结果
     * @param invokeParam 参数
     * @return RPCResponse对象
     */
    RPCResponse invoke(InvokeParam invokeParam) throws RPCException;

    boolean isAvailable();
}
