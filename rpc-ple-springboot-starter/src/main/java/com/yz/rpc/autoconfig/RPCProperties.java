package com.yz.rpc.autoconfig;

import com.yz.rpc.config.ApplicationConfig;
import com.yz.rpc.config.ClusterConfig;
import com.yz.rpc.config.ProtocolConfig;
import com.yz.rpc.config.RegistryConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 基于properties配置文件的方式配置bean
 */
@ConfigurationProperties(prefix = "rpc")
@Data
public class RPCProperties {

    private ApplicationConfig applicationConfig;

    private ProtocolConfig protocolConfig;

    private ClusterConfig clusterConfig;

    private RegistryConfig registryConfig;
}
