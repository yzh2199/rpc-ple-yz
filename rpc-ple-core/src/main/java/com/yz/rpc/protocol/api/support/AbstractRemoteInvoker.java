package com.yz.rpc.protocol.api.support;

import com.yz.rpc.registry.api.ServiceURL;
import com.yz.rpc.transport.api.Client;

/**
 * 抽象的远程服务调用者,继承自AbstractInvoker
 *
 * @param <T> 服务接口类型
 *
 * @author yz
 * create at 2020/3/21
 */
public abstract class AbstractRemoteInvoker<T> extends AbstractInvoker<T> {

    /**
     * 因为是远程调用,所以必须有一个与远程服务器交互的客户端实例
     */
    private Client client;

    @Override
    public ServiceURL getServiceURL() {
        return getClient().getServiceURL();
    }

    protected Client getClient(){
        return client;
    }

    @Override
    public boolean isAvailable() {
        return getClient().isAvailable();
    }

    public void setClient(Client client){
        this.client = client;
    }
}
