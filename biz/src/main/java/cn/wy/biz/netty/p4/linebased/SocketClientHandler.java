package cn.wy.biz.netty.p4.linebased;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by leslie on 2020/3/10.
 */
public class SocketClientHandler extends ChannelInboundHandlerAdapter {

    private int    counter;
    private byte[] req;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 未使用 LineBasedFrameDecoder StringDecoder, 直接用bytebuffer.
        /**<pre>
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];

        buf.readBytes(req);
        String body = new String(req, "UTF-8");
         </pre>
         */

        String body = (String)msg;
        System.out.println("Now is : " + body + " ; the counter is :" + ++counter);
    }

    /**
     * <pre>
     *     未使用LineBasedFrameDecoder, StringDecoder时：
     *     连接建立后，发送100次"Query TIME ORDER\n", 在服务端输出了100次, 但是counter 为2, 说明仅仅接收了2次请求;
     *     服务端发送2次 "BAD ORDER", 但是客户端的conter为1；
     *     说明服务端和客户端都发生了粘包。
     * </pre>
     * 
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf message = null;
        for (int i = 0; i < 100; i++) {
            message = Unpooled.buffer(req.length);
            message.writeBytes(req);
            ctx.writeAndFlush(message);
        }
    }

    public SocketClientHandler(){
        req = ("QUERY TIME ORDER" + "\n").getBytes();
    }
}
