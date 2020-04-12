package cn.wy.biz.netty.p4.self;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by leslie on 2020/3/10.
 */
public class SocketClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    }

    // @Override
    public void channelActive2(ChannelHandlerContext ctx) throws Exception {
        UnpooledByteBufAllocator allocator = new UnpooledByteBufAllocator(false);
        ByteBuf buffer = allocator.buffer(20);
        buffer.writeInt(8);
        buffer.writeBytes("head".getBytes());
        buffer.writeBytes("body".getBytes());

        ctx.writeAndFlush(buffer);
        System.out.println("client channelActive()...");
    }

    /**
     * <pre>
     *     测试拆包.
     *     发送1608个字节长度的消息， 看下服务端selfDefineEncodeHandler、BusinessServerHandler的调用次数。
     *     不足1608时不会传至BusinessServerHandler.
     * </pre>
     * 
     * @param ctx
     * @throws Exception
     */
    // @Override
    public void channelActive3(ChannelHandlerContext ctx) throws Exception {
        UnpooledByteBufAllocator allocator = new UnpooledByteBufAllocator(false);
        ByteBuf buffer = allocator.buffer(20);
        buffer.writeInt(1604);
        buffer.writeBytes("head".getBytes());
        for (int i = 0; i < 400; i++) {
            buffer.writeBytes("body".getBytes());
        }

        ctx.writeAndFlush(buffer);
        System.out.println("client channelActive()...");
    }

    /**
     * <pre>
     *     测试粘包.
     *     客户端连续发送20次请求，服务端需要去掉selfDefineEncodeHandler.  观察businessServerHandler的调用次数.
     *     businessServerHandler 只会被调用一次。
     * </pre>
     * 
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 20; i++) {
            UnpooledByteBufAllocator allocator = new UnpooledByteBufAllocator(false);
            ByteBuf buffer = allocator.buffer(20);
            buffer.writeInt(8);
            buffer.writeBytes("head".getBytes());
            buffer.writeBytes("body".getBytes());

            ctx.writeAndFlush(buffer);
            System.out.println("client channelActive()...");
        }
    }
}
