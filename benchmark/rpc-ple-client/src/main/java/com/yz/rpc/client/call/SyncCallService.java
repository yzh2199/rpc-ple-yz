package com.yz.rpc.client.call;

import com.yz.rpc.api.domain.User;
import com.yz.rpc.api.server.SimpleRPCService;
import com.yz.rpc.autoconfig.annotation.RPCReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 同步调用测试
 *
 * @author yz
 * create at 2020/4/5
 */
@Component
@Slf4j
public class SyncCallService {

    private SimpleRPCService rpcService;    //远程服务的本地代理

    public void syncCallTest(){
        String result = rpcService.helloRPC(new User("yzhyzh"));
        log.info("同步调用结果:{}",result);
    }
}
