package com.yz.rpc.common.enumeration;

import com.yz.rpc.common.enumeration.support.ExtensionBaseType;
import com.yz.rpc.executor.api.TaskExecutor;
import com.yz.rpc.executor.threadPool.ThreadPoolTaskExecutor;

/**
 * 任务执行器的枚举类
 * springboot的autoConfiguration根据配置文件信息选择加载对应的实现类
 * 属于应用内的依赖注入,所以使用简单的枚举单例
 *
 * @author yz
 * create at 2020/3/18
 */
public enum ExecutorType implements ExtensionBaseType<TaskExecutor> {
    THREADPOOL(new ThreadPoolTaskExecutor());   //目前只实现了线程池的方式

    private TaskExecutor executor;

    ExecutorType(TaskExecutor executor) {
        this.executor = executor;
    }

    @Override
    public TaskExecutor getInstance() {
        return executor;
    }
}
