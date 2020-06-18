package com.yz.rpc.transport.Arc10.client;

import com.yz.rpc.transport.Arc10.codec.ArcDecoder;
import com.yz.rpc.transport.Arc10.codec.ArcEncoder;
import com.yz.rpc.transport.Arc10.constance.ArcConstant;
import com.yz.rpc.transport.api.support.netty.AbstractNettyClient;
import com.yz.rpc.transport.constance.CommunicationConstant;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * 面向Arc协议的客户端
 * 不同协议只需要用不同的handler来处理,所以面向具体协议的客户端只需要编写自己的pipeline
 *
 * @author yz
 * create at 2020/3/26
 */
public class ArcClient extends AbstractNettyClient {
    @Override
    protected ChannelInitializer initPipeline() {
        return new ChannelInitializer<>() {
            @Override
            protected void initChannel(Channel channel) {
                channel.pipeline()
                        //空闲检测逻辑处理器
                        .addLast("IdleStateHandler", new IdleStateHandler(0, ArcConstant.IDLE_INTERVAL,0))
                        //在消息头部加上消息长度字段
                        .addLast("LengthFieldPrepender",new LengthFieldPrepender(CommunicationConstant.LENGTH_FIELD_LENGTH,CommunicationConstant.LENGTH_ADJUSTMENT))
                        //编码器
                        .addLast("ArcEncoder",new ArcEncoder(getGlobalConfig().getSerializer()))
                        //基于长度域的拆包器,解决粘包/半包问题
                        .addLast("LengthFieldBasedFrameDecoder",
                                new LengthFieldBasedFrameDecoder(CommunicationConstant.MAX_FRAME_LENGTH,
                                        CommunicationConstant.LENGTH_FIELD_OFFSET,
                                        CommunicationConstant.LENGTH_FIELD_LENGTH,
                                        CommunicationConstant.LENGTH_ADJUSTMENT,
                                        CommunicationConstant.INITIAL_BYTES_TO_STRIP))
                        //解码器
                        .addLast("ArcDecoder",new ArcDecoder(getGlobalConfig().getSerializer()))
                        //业务逻辑+心跳检测处理器,因为业务逻辑比较单一,所以没有分功能写handler,而是内部用if/else区分
                        .addLast("ArcClientHandler",new ArcClientHandler(ArcClient.this));
            }
        };
    }
}
