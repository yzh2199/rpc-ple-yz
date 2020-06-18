package com.yz.rpc.common.enumeration;

import com.yz.rpc.common.enumeration.support.ExtensionBaseType;
import com.yz.rpc.serialize.api.Serializer;
import com.yz.rpc.serialize.hessian.HessianSerializer;
import com.yz.rpc.serialize.jdk.JDKSerializer;
import com.yz.rpc.serialize.json.JsonSerializer;
import com.yz.rpc.serialize.protostuff.ProtostuffSerializer;

public enum SerializeType implements ExtensionBaseType<Serializer> {
    JDK(new JDKSerializer()),                   //jdk原生序列化
    HESSIAN(new HessianSerializer()),           //hessian序列化
    PROTOSTUFF(new ProtostuffSerializer()),     //protoStuff序列化
    JSON(new JsonSerializer());                 //Json序列化

    private Serializer serializer;

    SerializeType(Serializer serializer){
        this.serializer = serializer;
    }

    @Override
    public Serializer getInstance() {
        return serializer;
    }
}
