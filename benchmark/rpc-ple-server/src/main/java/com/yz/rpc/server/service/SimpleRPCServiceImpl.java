package com.yz.rpc.server.service;

import com.yz.rpc.api.domain.User;
import com.yz.rpc.api.server.SimpleRPCService;
import com.yz.rpc.autoconfig.annotation.RPCService;
import org.springframework.stereotype.Component;

/**
 * 简单RPC接口的实现类
 *
 * @author yz
 * create at 2020/4/5
 */
//@RPCService(interfaceClass = SimpleRPCService.class)
public class SimpleRPCServiceImpl implements SimpleRPCService {
    @Override
    public String helloRPC(User user) {

        return "remote_call success!";
    }
}
