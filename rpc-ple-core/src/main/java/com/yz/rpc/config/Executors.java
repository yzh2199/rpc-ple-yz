package com.yz.rpc.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 服务端和客户端的通用executor配置类，配置以下属性
 * 1)隶属于provider的ExecutorConfig--server
 * 2)隶属于consumer的ExecutorConfig--client
 *
 * @author yz
 * create at 2020/3/18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Executors {

    private ExecutorConfig server;

    private ExecutorConfig client;

    public void close(){
        if(server!=null)
            server.close();
        if(client!=null)
            client.close();
    }
}
