package com.yz.rpc.client.call;

import com.yz.rpc.api.domain.User;
import com.yz.rpc.api.server.RPCServiceWithCallback;
import com.yz.rpc.autoconfig.annotation.RPCReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 回调测试
 *
 * @author yz
 * create at 2020/4/5
 */
@Component
@Slf4j
public class CallbackService {

    @RPCReference(callback = true, callbackMethod = "getInfoFromClient")
    private RPCServiceWithCallback rpcServiceWithCallback;

    public void callBackTest(){
        rpcServiceWithCallback.hello(new User("yzh"), this::getInfoFromClient);
    }

    public void getInfoFromClient(String result){
//        log.info("回调调用结果:{}",result);
        System.out.println(result);
    }
}
