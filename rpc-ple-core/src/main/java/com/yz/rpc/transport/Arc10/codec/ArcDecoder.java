package com.yz.rpc.transport.Arc10.codec;

import com.yz.rpc.common.domain.Message;
import com.yz.rpc.common.domain.RPCRequest;
import com.yz.rpc.common.domain.RPCResponse;
import com.yz.rpc.serialize.api.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 面向Arc协议的解码器
 *
 * @author yz
 * create at 2020/3/26
 */
public class ArcDecoder extends ByteToMessageDecoder {

    /**
     * 单例模式,多个pipeline共享handler
     */
//    private static volatile JamesDecoder INSTANCE = null;

    private Serializer serializer;  //序列化算法

    public ArcDecoder(Serializer serializer){
        this.serializer = serializer;
    }

    //双重检测锁实现单例的延迟加载
//    public static ArcDecoder getInstance(Serializer serializer){
//        if(INSTANCE==null){
//            synchronized (JamesDecoder.class){
//                if(INSTANCE==null){
//                    synchronized (JamesDecoder.class){
//                        INSTANCE = new JamesDecoder(serializer);
//                    }
//                }
//            }
//        }
//        return INSTANCE;
//    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> list) {
        byte type = byteBuf.readByte();
        if(type== Message.PING){
            //PING心跳信息
            list.add(Message.PING_MSG);
        }else if(type==Message.PONG){
            //PONG心跳信息
            list.add(Message.PONG_MSG);
        }else{
            byte[] bytes = new byte[byteBuf.readableBytes()];
            byteBuf.readBytes(bytes);
            if(type==Message.REQUEST){
                list.add(Message.buildRequest(serializer.deSerialize(bytes,RPCRequest.class)));
            }else if(type==Message.RESPONSE){
                list.add(Message.buildResponse(serializer.deSerialize(bytes, RPCResponse.class)));
            }
        }
    }
}
