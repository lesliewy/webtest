package cn.wy.biz.netty.p2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * <pre>
 *     rdate -o <port> -p <host>
 *     编写netty client 来调用。
 * </pre>
 * 
 * Created by leslie on 2020/3/9.
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * <pre>
     *     该方法在连接建立后，处理数据前调用。
     * </pre>
     * 
     * @param ctx
     */
    @Override
    public void channelActive(final ChannelHandlerContext ctx) { // (1)
        // 4个字节的buffer.
        final ByteBuf time = ctx.alloc().buffer(4); // (2)
        // 不像jdk中的NIO，写入前需要调用flip()。 byteBuffer 直接写入.
        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));

        // Netty 中的操作都是异步执行的。
        // channelFuture 标识一个还未发生的IO操作，关闭context需要在该IO操作完成之后来执行, 所以需要addListener, 不能直接ctx.close()
        /**
         * <pre>
         * final ChannelFuture f = ctx.writeAndFlush(time); // (3)
         * f.addListener(new ChannelFutureListener() {
         * 
         *     &#64;Override
         *     public void operationComplete(ChannelFuture future) {
         *         assert f == future;
         *         // 同样，不会马上执行，返回一个channelFuture.
         *         ctx.close();
         *     }
         * }); // (4)
         * </pre>
         */

        // server 端使用POJO 代替bytebuffer.
        ChannelFuture f = ctx.writeAndFlush(new UnixTime());
        f.addListener(ChannelFutureListener.CLOSE);
        System.out.println("serverhandler...");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
