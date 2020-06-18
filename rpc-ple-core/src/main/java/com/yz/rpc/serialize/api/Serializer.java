package com.yz.rpc.serialize.api;

import com.yz.rpc.common.exception.RPCException;

/**
 * 序列化/反序列化接口
 *
 * @author yz
 * create at 2020/3/20
 */
public interface Serializer {
    /**
     * 序列化
     * @param o 待序列化对象
     * @param <T> 对象类型
     * @return 序列化字节数组
     */
    <T> byte[] serialize(T o) throws RPCException;

    /**
     * 反序列化
     * @param bytes 字节数组
     * @param clazz 目标对象类类型
     * @param <T> 目标对象类型
     * @return 目标对象
     */
    <T> T deSerialize(byte[] bytes, Class<T> clazz) throws RPCException;
}
