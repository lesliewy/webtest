package cn.wy.biz.netty.p2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * <pre>
 *     encoder 相比decoder 更简单，因为无需处理粘包、拆包问题。
 *     这里使用ChannelOutboundHandlerAdapter.write(), 直接写入.
 * </pre>
 * Created by leslie on 2020/3/10.
 */
public class TimeEncoder extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
        UnixTime m = (UnixTime) msg;
        ByteBuf encoded = ctx.alloc().buffer(4);
        encoded.writeInt((int) m.value());
        ctx.write(encoded, promise); // (1)
        System.out.println("encoder...");
    }
}
