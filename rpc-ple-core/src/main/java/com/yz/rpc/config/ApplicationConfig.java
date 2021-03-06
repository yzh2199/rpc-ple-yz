package com.yz.rpc.config;

import com.yz.rpc.proxy.api.RPCProxyFactory;
import com.yz.rpc.serialize.api.Serializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目应用配置类，配置以下属性
 * 1)应用名
 * 2)序列化算法
 * 3)代理对象工厂
 * 4)序列化算法实例
 *
 * @author yz
 * create at 2020/3/20
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationConfig {

    private String applicationName;

    private String serializer;

    private String proxyFactoryName;

    private Serializer serializerInstance;

    private RPCProxyFactory RPCProxyFactoryInstance;
}
