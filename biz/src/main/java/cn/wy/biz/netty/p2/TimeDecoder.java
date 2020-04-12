package cn.wy.biz.netty.p2;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * Created by leslie on 2020/3/9.
 */
public class TimeDecoder extends ByteToMessageDecoder {

    /**
     * <pre>
     *     新的数据到达时调用该方法。
     * </pre>
     * @param ctx
     * @param in
     * @param out
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) { // (2)
        // 从buffer中读取数据，不足时，不做处理. out 中没有变化。
        if (in.readableBytes() < 4) {
            return; // (3)
        }

        // out 发生变化时，buffer中将对应的数据删除。
//        out.add(in.readBytes(4)); // (4)

        // 使用了自定义的protocol
        out.add(new UnixTime(in.readUnsignedInt()));
        System.out.println("decoder");
    }
}
