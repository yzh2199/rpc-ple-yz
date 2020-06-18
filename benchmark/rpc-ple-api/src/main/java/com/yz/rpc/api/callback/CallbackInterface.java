package com.yz.rpc.api.callback;

/**
 * 回调接口
 *
 * @author yz
 * create at 2020/4/5
 */
public interface CallbackInterface {
    /**
     * 回调方法
     * @param result RPC请求返回的结果
     */
    void getInfoFromClient(String result);
}
