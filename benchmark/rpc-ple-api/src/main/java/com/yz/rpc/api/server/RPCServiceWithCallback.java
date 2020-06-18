package com.yz.rpc.api.server;

import com.yz.rpc.api.callback.CallbackInterface;
import com.yz.rpc.api.domain.User;

/**
 * 带回调参数的服务接口
 *
 * @author yz
 * create at 2020/4/5
 */
public interface RPCServiceWithCallback {
    void hello(User user, CallbackInterface callbackInterface);
}
