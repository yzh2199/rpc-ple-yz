package com.yz.rpc.config;

import com.yz.rpc.registry.api.ServiceRegistry;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 注册中心配置类，配置以下属性
 * 1)注册中心类型
 * 2)注册中心地址
 * 3)注册中心实例
 *
 * @author yz
 * create at 2020/3/17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistryConfig {

    private String type;

    private String address;

    private ServiceRegistry serviceRegistryInstance;

    public void init(){
        if(serviceRegistryInstance!=null){
            serviceRegistryInstance.init();
        }
    }

    /**
     * 关闭注册中心客户端
     */
    public void close(){
        if(serviceRegistryInstance!=null){
            serviceRegistryInstance.close();
        }
    }
}
