package com.yz.rpc.client.filter;

import com.yz.rpc.common.domain.RPCResponse;
import com.yz.rpc.common.exception.RPCException;
import com.yz.rpc.protocol.api.InvokeParam;
import com.yz.rpc.protocol.api.Invoker;

/**
 * 过滤器接口
 * 用于在请求处理前或处理后做一些通用的逻辑,本项目中,Filter链在引用服务时被创建
 *
 * @author yz
 * create at 2020/3/18
 */
public interface Filter {
    /**
     * 过滤器invoke方法,实际上不处理业务逻辑,只是在真正的invoke前后增加一些前处理/后处理
     * @param invoker 真正的业务逻辑invoker
     * @param invokeParam 调用参数
     * @return RPC调用结果
     */
    RPCResponse invoke(Invoker invoker, InvokeParam invokeParam) throws RPCException;
}
