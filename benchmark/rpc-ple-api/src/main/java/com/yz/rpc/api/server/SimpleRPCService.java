package com.yz.rpc.api.server;

import com.yz.rpc.api.domain.User;

/**
 * 简单的RPC服务接口
 *
 * @author yz
 * create at 2020/4/5
 */
public interface SimpleRPCService {
    String helloRPC(User user);
}
