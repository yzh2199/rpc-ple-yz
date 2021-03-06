package com.yz.rpc.protocol.arc10;

import com.yz.rpc.client.filter.Filter;
import com.yz.rpc.common.enumeration.ExceptionEnum;
import com.yz.rpc.common.exception.RPCException;
import com.yz.rpc.config.ReferenceConfig;
import com.yz.rpc.config.ServiceConfig;
import com.yz.rpc.protocol.api.Exporter;
import com.yz.rpc.protocol.api.Invoker;
import com.yz.rpc.protocol.api.support.AbstractRemoteProtocol;
import com.yz.rpc.registry.api.ServiceURL;
import com.yz.rpc.transport.Arc10.client.ArcClient;
import com.yz.rpc.transport.Arc10.server.ArcServer;
import com.yz.rpc.transport.api.Client;
import com.yz.rpc.transport.api.Server;

import java.util.List;

/**
 * 具体协议对象
 * 自定义协议:Arc
 *
 * @author yz
 * create at 2020/3/21
 */
public class ArcProtocol extends AbstractRemoteProtocol {
    /**
     * 抽象方法:目标服务器并未与本机连接时,由具体的protocol实例来创建连接并返回客户端
     *
     * @param serviceURL 包含目标服务信息的ServiceURL
     * @return 客户端
     */
    @Override
    protected Client doInitClient(ServiceURL serviceURL) {
        ArcClient arcClient = new ArcClient();
        arcClient.init(serviceURL,getGlobalConfig());
        return arcClient;
    }

    /**
     * 开启服务端连接
     */
    @Override
    protected Server doOpenServer() {
        ArcServer arcServer = new ArcServer();
        arcServer.init(getGlobalConfig());
        arcServer.run();
        return arcServer;
    }

    /**
     * 利用在注册中心获得的serviceURL和用户的配置来引用远程服务,返回的是一个InvokerDelegate,封装了filter
     *
     * @param serviceURL 服务发现获取的serviceURL
     * @param referenceConfig 引用服务的配置类
     * @param <T> 服务接口类型
     * @return InvokerDelegate对象
     */
    @Override
    public <T> Invoker<T> refer(ServiceURL serviceURL, ReferenceConfig<T> referenceConfig) {
        //创建一个面向Arc协议的调用者,负责提供getProcessor()方法与transport层交互
        ArcInvoker<T> invoker = new ArcInvoker<>();
        invoker.setInterfaceName(referenceConfig.getInterfaceName());
        invoker.setInterfaceClass(referenceConfig.getInterfaceClass());
        invoker.setGlobalConfig(getGlobalConfig());
        invoker.setClient(initClient(serviceURL));
        List<Filter> filters = referenceConfig.getFilters();
        if(filters.size()==0){
            //没有配置过滤器,直接返回invoker
            return invoker;
        }
        //配置了过滤器,则要构建过滤链
        return invoker.buildFilterChain(filters);
    }

    /**
     * 利用服务的本地抽象调用者和服务配置对象来暴露服务,将服务注册到注册中心
     *
     * @param localInvoker 服务的本地调用者
     * @param serviceConfig 服务配置对象
     * @param <T> 服务接口类型
     * @return 服务暴露之后的抽象调用者,封装invoker和ServiceConfig
     */
    @Override
    public <T> Exporter<T> export(Invoker<T> localInvoker, ServiceConfig<T> serviceConfig) {
        ArcExporter<T> exporter = new ArcExporter<>();
        exporter.setInvoker(localInvoker);
        exporter.setServiceConfig(serviceConfig);
        //将暴露的服务放入缓存中
        putExporter(localInvoker.getInterfaceClass(),exporter);
        //暴露服务之前先开启服务端连接,防止出现服务端连接还未开启客户端就拿到服务地址并请求建立连接的情况
        openServer();
        try{
            //将服务暴露到注册中心
            serviceConfig.getRegistryConfig().getServiceRegistryInstance().register(
                    //主机IP+端口号
                    "192.168.1.116"+":"+getGlobalConfig().getPort(),
                    localInvoker.getInterfaceName(),
                    localInvoker.getInterfaceClass());
        }catch (Exception e){
            //获取本地主机IP地址失败
            throw new RPCException(ExceptionEnum.FAIL_TO_GET_LOCALHOST_ADDRESS,"FAIL_TO_GET_LOCALHOST_ADDRESS");
        }
        return exporter;
    }

}
