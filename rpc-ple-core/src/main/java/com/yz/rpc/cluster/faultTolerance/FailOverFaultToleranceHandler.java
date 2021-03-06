package com.yz.rpc.cluster.faultTolerance;

import com.yz.rpc.cluster.api.FaultToleranceHandler;
import com.yz.rpc.cluster.api.support.ClusterInvoker;
import com.yz.rpc.common.constant.ClusterConstant;
import com.yz.rpc.common.context.RPCThreadLocalContext;
import com.yz.rpc.common.domain.RPCResponse;
import com.yz.rpc.common.enumeration.ExceptionEnum;
import com.yz.rpc.common.exception.RPCException;
import com.yz.rpc.protocol.api.InvokeParam;
import com.yz.rpc.protocol.api.Invoker;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 集群容错机制之失败自动切换
 * RPC调用在一台服务器上出现异常后,快速将请求切换到别的可用服务器上,通常用于读操作
 *
 * @author yz
 * create at 2020/4/1
 */
@Slf4j
public class FailOverFaultToleranceHandler implements FaultToleranceHandler {

    /**
     * 集群容错处理方法,配置不同的容错机制有不同的实现
     *
     * @param clusterInvoker ClusterInvoker对象
     * @param invokeParam    invoker参数
     * @param e              调用过程抛出的异常
     * @return 经容错机制处理后的调用结果
     */
    @Override
    @SuppressWarnings("unchecked")
    public RPCResponse handle(ClusterInvoker clusterInvoker, InvokeParam invokeParam, RPCException e) {
        //取出当前线程对应的Invoker,这是出现异常的Invoker对象,要从可用服务器列表中删除
        Invoker failedInvoker = RPCThreadLocalContext.getContext().getInvoker();
        Map<String,Invoker> excludedInvokers = new HashMap<>();     //失败invoker集合
        excludedInvokers.put(failedInvoker.getServiceURL().getServiceAddress(),failedInvoker);
        for(int retry=0;retry< ClusterConstant.FAILOVER_RETRY_TIMES;retry++){
            List<Invoker> invokers = clusterInvoker.getAvailableInvokers();     //拿到可用服务列表
            Iterator<Invoker> it = invokers.iterator();
            while (it.hasNext()){
                //将可用服务列表中出现在失败服务列表的invoker删除
                if(excludedInvokers.containsKey(it.next().getServiceURL().getServiceAddress())){
                    it.remove();
                }
            }
            if(invokers.size()==0){
                //没有可用服务了
                e.printStackTrace();
                throw new RPCException(ExceptionEnum.NO_AVAILABLE_SERVICE,"NO_AVAILABLE_SERVICE");
            }
            try{
                log.info("failover正在重试...");
                //todo:这里为什么不把invokeForFaultTolerance方法的实现放在本类中而要放在clusterinvoker类中
                return clusterInvoker.invokeForFaultTolerance(invokers,invokeParam);
            }catch (RPCException e1){
                e1.printStackTrace();
                log.error("第{}次重试失败...",retry+1);
                Invoker fInvoker = RPCThreadLocalContext.getContext().getInvoker();
                //将失败的invoker放入不可用服务列表中
                excludedInvokers.put(fInvoker.getServiceURL().getServiceAddress(),fInvoker);
            }
        }
        //指定重试次数之内还没有成功
        throw new RPCException(ExceptionEnum.EXCEEDED_RETRIES,"EXCEEDED_RETRIES");
    }
}
