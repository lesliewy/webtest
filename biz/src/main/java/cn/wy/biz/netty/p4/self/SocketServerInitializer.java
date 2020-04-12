package cn.wy.biz.netty.p4.self;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * Created by leslie on 2020/3/10.
 */
public class SocketServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 测试粘包时，注释掉。
//        pipeline.addLast(new SelfDefineEncodeHandler());
        pipeline.addLast(new BusinessServerHandler());
    }
}
