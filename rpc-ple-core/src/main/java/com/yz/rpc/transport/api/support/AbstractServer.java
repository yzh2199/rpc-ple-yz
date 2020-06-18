package com.yz.rpc.transport.api.support;

import com.yz.rpc.config.GlobalConfig;
import com.yz.rpc.transport.api.Server;

/**
 * 抽象的服务端
 *
 * @author yz
 * create at 2020/3/24
 */
public abstract class AbstractServer implements Server {

    private GlobalConfig globalConfig;  //全局配置类,必不可少

    public void init(GlobalConfig globalConfig){
        this.globalConfig = globalConfig;
        doInit();
    }

    protected GlobalConfig getGlobalConfig(){
        return globalConfig;
    }

    protected abstract void doInit();
}
