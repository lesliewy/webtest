package cn.wy.biz.netty.p2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * <pre>
 *    netty user guide 中例子.
 *    ChannelInboundHandler 接口提供各种handler 方法。
 * </pre>
 *
 * Created by leslie on 2020/3/9.
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * <pre>
     *     读取客户端数据，自动调用方法.
     * </pre>
     * 
     * @param ctx
     * @param msg
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
        // Discard the received data silently.
        // ((ByteBuf) msg).release(); // (3)

        /*
         * ByteBuf in = (ByteBuf) msg; try { while (in.isReadable()) { // (1) System.out.print((char) in.readByte());
         * System.out.flush(); } } finally { ReferenceCountUtil.release(msg); // (2) }
         */

        // echo handler
        ctx.write(msg); // (1)
        ctx.flush(); // (2)
    }

    /**
     * <pre>
     *     IO操作异常调用方法。
     * </pre>
     * 
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
