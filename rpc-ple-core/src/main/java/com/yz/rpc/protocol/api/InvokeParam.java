package com.yz.rpc.protocol.api;

/**
 * 调用服务的参数接口
 * 定义了调用一个服务需要提供哪些参数
 *
 * @author yz
 * create at 2020/3/17
 */
public interface InvokeParam {
    //获取服务接口名
    String getInterfaceName();

    //获取调用方法名
    String getMethodName();

    //获取方法参数类型集合
    Class<?>[] getParameterTypes();

    //获取方法的参数列表
    Object[] getParameters();

    //获取请求编号
    String getRequestID();
}
