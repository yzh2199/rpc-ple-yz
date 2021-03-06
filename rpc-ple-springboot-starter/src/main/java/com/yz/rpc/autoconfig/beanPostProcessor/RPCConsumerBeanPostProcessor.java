package com.yz.rpc.autoconfig.beanPostProcessor;

import com.yz.rpc.autoconfig.annotation.RPCReference;
import com.yz.rpc.common.ExtensionLoader;
import com.yz.rpc.config.ReferenceConfig;
import com.yz.rpc.client.filter.Filter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;

import java.lang.reflect.Field;

/**
 * RPC客户端后置处理器,用于创建ReferenceConfig实例
 *
 * @author yz
 * create at 2020/4/4
 */
@Slf4j
public class RPCConsumerBeanPostProcessor extends AbstractBeanPostProcessor{
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        //扫描bean的所有字段
        Field[] fields = beanClass.getDeclaredFields();
        for(Field field:fields){
            field.setAccessible(true);  //将所有字段设为可访问的
            //获取字段类型(对引用服务来说就是接口类型)
            Class<?> interfaceClass = field.getType();
            //获取字段的@RPCReference注解
            RPCReference reference = field.getAnnotation(RPCReference.class);
            if(reference!=null){
                //字段有@RPCReference注解,使用注解配置ReferenceConfig对象
                ReferenceConfig referenceConfig = ReferenceConfig.createReferenceConfig(
                        interfaceClass.getName(),
                        interfaceClass,
                        reference.async(),
                        reference.oneWay(),
                        reference.callback(),
                        reference.callbackMethod(),
                        reference.callbackParamIndex(),
                        reference.timeout(),
                        false,
                        ExtensionLoader.getINSTANCE().load(Filter.class)
                );
                initConfig(referenceConfig);    //将全局配置对象注入referenceConfig
                try{
                    field.set(bean,referenceConfig.get());  //创建一个远程服务的代理对象,将其注入到字段中
                    log.info("成功注入远程服务的本地代理依赖:{}",interfaceClass);
                }catch (IllegalAccessException e){
                    e.printStackTrace();
                }
            }
        }
        return bean;
    }
}
