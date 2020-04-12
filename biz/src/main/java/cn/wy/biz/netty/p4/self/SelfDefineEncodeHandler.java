package cn.wy.biz.netty.p4.self;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * Created by leslie on 2020/3/10.
 */
public class SelfDefineEncodeHandler extends ByteToMessageDecoder {

    private static int count = 0;

    /**
     * <pre>
     *     客户端发送1604个字节，decode 会被调用2次，第一次是1024个字节，还不清楚这个1024是怎么配置的， 第二次是完整的1604个字节。
     *     BusinessServerHandler 只被调用了一次。
     * </pre>
     * @param ctx
     * @param bufferIn
     * @param out
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf bufferIn, List<Object> out) throws Exception {
        // 测试拆包.
        System.out.println("decode call count="+ ++count);
        System.out.println("bufferIn.readableBytes()="+bufferIn.readableBytes());

        // 消息长度部分是否完整。
        if (bufferIn.readableBytes() < 4) {
            return;
        }
        int beginIndex = bufferIn.readerIndex();
        System.out.println("beginIndex="+beginIndex);

        // readInt() 之后, readerIndex() 会自动增加4.
        int length = bufferIn.readInt();
        // 长度不足时，直接return, out中并未增加内容, 也不会转至下一个handler(BusinessServerHandler).
        if ((bufferIn.readableBytes() + 1) < length) {
            // 重置readerIndex.
            bufferIn.readerIndex(beginIndex);
            return;
        }
        // 将readerIndex设置为最大， 这样buffer.isReadable() 为false, 读取完毕。
        // 对于拆包来说，是将readerIndex 增加4+length.
        bufferIn.readerIndex(beginIndex + 4 + length);
        ByteBuf otherByteBufRef = bufferIn.slice(beginIndex, 4 + length);
        // buffer的引用计数加1，不释放buffer, 后面的操作仍然可以读写buffer.
        otherByteBufRef.retain();
        out.add(otherByteBufRef);

        System.out.println("server SelfDefineEncodeHandler decode()...");
    }
}
