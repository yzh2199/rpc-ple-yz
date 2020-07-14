package com.yz.rpc.client.call;

import com.yz.rpc.api.domain.User;
import com.yz.rpc.api.server.SimpleRPCService;
import com.yz.rpc.autoconfig.annotation.RPCReference;
import com.yz.rpc.common.context.RPCThreadLocalContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * 异步调用测试
 *
 * @author yz
 * create at 2020/4/5
 */
@Component
@Slf4j
public class AsyncCallService {
//    @RPCReference(async = true)
    private SimpleRPCService service;

    public void asyncCall() throws Exception{
        service.helloRPC(new User("211"));
        Future<String> future = RPCThreadLocalContext.getContext().getFuture();
        log.info("异步调用结果:{}",future.get());
    }
}
