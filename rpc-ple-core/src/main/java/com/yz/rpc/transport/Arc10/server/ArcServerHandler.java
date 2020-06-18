package com.yz.rpc.transport.Arc10.server;

import com.yz.rpc.common.domain.Message;
import com.yz.rpc.transport.Arc10.constance.ArcConstant;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 面向Arc协议的服务端逻辑处理器
 *
 * @author yz
 * create at 2020/3/27
 */
@Slf4j
public class ArcServerHandler extends SimpleChannelInboundHandler<Message> {

    private ArcServer arcServer;

    private AtomicInteger timeoutCount = new AtomicInteger(0);  //原子类,重试次数

    ArcServerHandler(ArcServer arcServer){
        this.arcServer = arcServer;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message message) {
        //收到消息,第一件事将计数器置0
        timeoutCount.set(0);
        log.info("收到客户端消息,消息类型:{}",message.getType());
        byte type = message.getType();  //消息类型
        if(type==Message.PING){
            log.info("收到客户端PING心跳");
            ctx.writeAndFlush(Message.PONG_MSG);    //收到客户端的PING心跳后,回复一个PONG心跳
        }else if(type==Message.REQUEST){
            log.info("收到rpc请求:{}",message.getRequest());
            arcServer.handlerRPCRequest(message.getRequest(),ctx);
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            //空闲事件被触发
            if(timeoutCount.getAndIncrement()> ArcConstant.HEART_BEAT_TIMEOUT_MAX_TIMES){
                log.info("超过丢失心跳最大次数,关闭服务端连接...");
                ctx.close();
            }else{
                log.error("规定时间内服务器未接收到客户端消息或心跳信息...");
            }
        }else{
            super.userEventTriggered(ctx, evt);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        //出现异常时,先打印异常信息,再关闭服务端连接
        try{
            cause.printStackTrace();
        }finally {
            ctx.close();
        }
    }
}
