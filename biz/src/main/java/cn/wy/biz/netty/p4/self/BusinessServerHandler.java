package cn.wy.biz.netty.p4.self;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * <pre>
 *     报文格式: len head body
 * </pre>
 * 
 * Created by leslie on 2020/3/10.
 */
public class BusinessServerHandler extends ChannelInboundHandlerAdapter {

    private static int count = 0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 测试拆包.
        System.out.println("BusinessServerHandler call count=" + ++count);

        ByteBuf buf = (ByteBuf) msg;
        int length = buf.readInt();
        assert length == (8);

        byte[] head = new byte[4];
        buf.readBytes(head);
        String headString = new String(head);
        assert "head".equals(headString);

        byte[] body = new byte[length - 4];
        buf.readBytes(body);
        String bodyString = new String(body);
        assert "body".equals(bodyString);

        System.out.println("server BusinessServerHandler channelRead()...");
    }
}
