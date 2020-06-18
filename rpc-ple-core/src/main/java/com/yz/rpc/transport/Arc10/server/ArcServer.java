package com.yz.rpc.transport.Arc10.server;

import com.yz.rpc.transport.Arc10.codec.ArcDecoder;
import com.yz.rpc.transport.Arc10.codec.ArcEncoder;
import com.yz.rpc.transport.Arc10.constance.ArcConstant;
import com.yz.rpc.transport.api.support.netty.AbstractNettyServer;
import com.yz.rpc.transport.constance.CommunicationConstant;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * 面向Arc协议的服务端
 * 提供初始化pipeline的方法实现
 *
 * @author yz
 * create at 2020/3/27
 */
public class ArcServer extends AbstractNettyServer {
    @Override
    protected ChannelInitializer initPipeline() {
        return new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel ssc) {
                ssc.pipeline()
                        //空闲检测逻辑处理器,服务端的最长等待时间为{最大重试次数×重试时间间隔}
                        .addLast("IdleStateHandler", new IdleStateHandler(ArcConstant.IDLE_INTERVAL*ArcConstant.HEART_BEAT_TIMEOUT_MAX_TIMES, 0,0))
                        //在消息头部加上消息长度字段
                        .addLast("LengthFieldPrepender",new LengthFieldPrepender(CommunicationConstant.LENGTH_FIELD_LENGTH,CommunicationConstant.LENGTH_ADJUSTMENT))
                        //编码器
                        .addLast("JamesEncoder", new ArcEncoder(getGlobalConfig().getSerializer()))
                        //基于长度域的拆包器,解决粘包/半包问题
                        .addLast("LengthFieldBasedFrameDecoder",
                                new LengthFieldBasedFrameDecoder(CommunicationConstant.MAX_FRAME_LENGTH,
                                        CommunicationConstant.LENGTH_FIELD_OFFSET,
                                        CommunicationConstant.LENGTH_FIELD_LENGTH,
                                        CommunicationConstant.LENGTH_ADJUSTMENT,
                                        CommunicationConstant.INITIAL_BYTES_TO_STRIP))
                        //解码器
                        .addLast("JamesDecoder", new ArcDecoder(getGlobalConfig().getSerializer()))
                        //业务逻辑+心跳检测处理器,因为业务逻辑比较单一,所以没有分功能写handler,而是内部用if/else区分
                        .addLast("JamesServerHandler",new ArcServerHandler(ArcServer.this));
            }
        };
    }
}
