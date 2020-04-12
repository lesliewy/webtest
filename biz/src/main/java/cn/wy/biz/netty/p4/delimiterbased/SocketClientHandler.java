package cn.wy.biz.netty.p4.delimiterbased;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by leslie on 2020/3/10.
 */
public class SocketClientHandler extends ChannelInboundHandlerAdapter {

    private int           counter;
    private static String req = "Hi Sam.Welcome to Netty home.$_";

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        System.out.println("This is " + ++counter + " times receive from server:" + body + "");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++) {
            ctx.writeAndFlush(Unpooled.copiedBuffer(req.getBytes()));
        }
    }
}
