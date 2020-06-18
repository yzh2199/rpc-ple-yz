package com.yz.rpc.server.service;

import com.yz.rpc.api.callback.CallbackInterface;
import com.yz.rpc.api.domain.User;
import com.yz.rpc.api.server.RPCServiceWithCallback;
import com.yz.rpc.autoconfig.annotation.RPCService;
import org.springframework.stereotype.Component;

/**
 * 带回调参数的服务接口实现类
 *
 * @author yz
 * create at 2020/4/5
 */
@RPCService(interfaceClass = RPCServiceWithCallback.class,callback = true,callbackMethod = "getInfoFromClient")
public class RPCServiceWithCallbackImpl implements RPCServiceWithCallback {
    @Override
    public void hello(User user, CallbackInterface callbackInterface) {
        String result = "i am RPCServer,i'm calling "+user.getUserName()+" back...";
        callbackInterface.getInfoFromClient(result);
    }
}
