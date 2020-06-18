package com.yz.rpc.config;

import com.yz.rpc.cluster.api.FaultToleranceHandler;
import com.yz.rpc.cluster.api.LoadBalancer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 集群配置类，配置以下属性
 * 1)负载均衡策略
 * 2)集群容错策略
 * 3)负载均衡器实例
 * 4)集群容错处理器实例
 *
 * @author yz
 * create at 2020/3/20
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClusterConfig {

    private String loadBalance;

    private String clusterFaultTolerance;

    private LoadBalancer loadBalancerInstance;

    private FaultToleranceHandler faultToleranceHandlerInstance;
}
