package com.yz.rpc.cluster.loadBalance;

import com.yz.rpc.cluster.api.support.AbstractLoadBalancer;
import com.yz.rpc.common.context.RPCStatus;
import com.yz.rpc.common.domain.RPCRequest;
import com.yz.rpc.protocol.api.Invoker;

import java.util.List;

/**
 * 最小活跃度负载均衡算法
 *
 *
 * @author yz
 * create at 2020/3/28
 */
public class LeastActiveLoadBalancer extends AbstractLoadBalancer {
    @Override
    protected Invoker doSelect(List<Invoker> availableInvokers, RPCRequest request) {
        Invoker target = null;
        int leastActivity = 0;
        for(Invoker invoker:availableInvokers){
            int curActivity = RPCStatus.getActivity(request.getInterfaceName(),
                    request.getMethodName(),
                    invoker.getServiceURL().getServiceAddress());
            if(target==null || curActivity<leastActivity){
                //找到活跃度最小的invoker
                target = invoker;
                leastActivity = curActivity;
            }
        }
        return target;
    }
}
