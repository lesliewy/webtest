package cn.wy.biz.netty.p5;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * Created by leslie on 2020/3/11.
 */
public class SelfDefineEncodeHandler extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf bufferIn, List<Object> out) throws Exception {
        if (bufferIn.readableBytes() < 4) {
            return;
        }

        int beginIndex = bufferIn.readerIndex();
        int length = bufferIn.readInt();

        if (bufferIn.readableBytes() < length) {
            bufferIn.readerIndex(beginIndex);
            return;
        }

        bufferIn.readerIndex(beginIndex + 4 + length);
        ByteBuf otherByteBufRef = bufferIn.slice(beginIndex, 4 + length);
        otherByteBufRef.retain();
        out.add(otherByteBufRef);
    }
}
