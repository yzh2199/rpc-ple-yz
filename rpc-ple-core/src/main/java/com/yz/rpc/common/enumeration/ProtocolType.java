package com.yz.rpc.common.enumeration;

import com.yz.rpc.common.enumeration.support.ExtensionBaseType;
import com.yz.rpc.protocol.api.Protocol;
import com.yz.rpc.protocol.arc10.ArcProtocol;

/**
 * 协议枚举类
 * 目前只实现了Arc协议
 *
 * @author yz
 * create at 2020/4/3
 */
public enum ProtocolType implements ExtensionBaseType<Protocol> {
    ARC(new ArcProtocol());     //Arc协议

    private Protocol protocol;

    ProtocolType(Protocol protocol){
        this.protocol = protocol;
    }

    @Override
    public Protocol getInstance() {
        return protocol;
    }
}
