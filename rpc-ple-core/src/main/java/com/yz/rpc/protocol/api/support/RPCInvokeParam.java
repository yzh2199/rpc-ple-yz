package com.yz.rpc.protocol.api.support;

import com.yz.rpc.common.domain.RPCRequest;
import com.yz.rpc.config.ReferenceConfig;
import com.yz.rpc.protocol.api.InvokeParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 封装RPC请求对象和引用配置对象的类
 *
 * @author yz
 * create at 2020/3/23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RPCInvokeParam implements InvokeParam {

    protected RPCRequest rpcRequest;

    protected ReferenceConfig referenceConfig;

    @Override
    public String getInterfaceName() {
        return rpcRequest.getInterfaceName();
    }

    @Override
    public String getMethodName() {
        return rpcRequest.getMethodName();
    }

    @Override
    public Class<?>[] getParameterTypes() {
        return rpcRequest.getParameterTypes();
    }

    @Override
    public Object[] getParameters() {
        return rpcRequest.getParameters();
    }

    @Override
    public String getRequestID() {
        return rpcRequest.getRequestID();
    }

    @Override
    public String toString(){
        return "RPCInvokeParam{"+
                rpcRequest+
                "}";
    }
}
