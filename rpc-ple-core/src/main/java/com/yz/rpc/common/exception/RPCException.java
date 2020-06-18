package com.yz.rpc.common.exception;

import com.yz.rpc.common.enumeration.ExceptionEnum;

/**
 * 自定义RPC异常
 *
 * @author yz
 * create at 2020/3/21
 */
public class RPCException extends RuntimeException{

    /**
     * 异常枚举类实例
     */
    private ExceptionEnum exceptionEnum;

    public RPCException(ExceptionEnum exceptionEnum, String message){
        super(message);
        this.exceptionEnum = exceptionEnum;
    }

    public RPCException(Throwable throwable, ExceptionEnum exceptionEnum, String message){
        super(message,throwable);
        this.exceptionEnum = exceptionEnum;
    }
}
