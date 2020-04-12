package cn.wy.biz.netty.p2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by leslie on 2020/3/9.
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    private ByteBuf buf;

    /**
     * <pre>
     *     handler 生命周期. 可以用来做一些初始化操作。这里是定义定长的bytebuffer.
     * </pre>
     * 
     * @param ctx
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        buf = ctx.alloc().buffer(4); // (1)
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        buf.release(); // (1)
        buf = null;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // ByteBuf m = (ByteBuf) msg; // (1)

        // 可能会出现拆包、粘包现象，没有预定义bytebuffer
        /*
         * try { long currentTimeMillis = (m.readUnsignedInt() - 2208988800L) * 1000L; System.out.println(new
         * Date(currentTimeMillis)); ctx.close(); } finally { m.release(); }
         */

        /*
         * 拆包、粘包解决：固定长度的报文。 可以放在handler 中，也可以使用单独的decoder.
         */
        /**
         * <pre>
         * buf.writeBytes(m); // (2) m.release(); if (buf.readableBytes() >= 4) { // (3)
         * long currentTimeMillis = (buf.readUnsignedInt() - 2208988800L) * 1000L;
         * System.out.println(new Date(currentTimeMillis));
         * ctx.close();
         * </pre>
         */

        // 使用POJO代替bytebuffer, decoder中也需要修改。
        UnixTime m = (UnixTime) msg;
        System.out.println(m);
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
