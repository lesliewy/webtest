package cn.wy.biz.netty.p2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * <pre>
 *     netty 客户端.
 *     和server端主要不同就在于bootstrap 和 channel 的配置.
 * </pre>
 * 
 * Created by leslie on 2020/3/9.
 */
public class TimeClient {

    public static void main(String[] args) throws Exception {
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // Bootstrap 用于非服务端channel.
            Bootstrap b = new Bootstrap(); // (1)
            // 只指定一个group, boss, worker 共用。
            b.group(workerGroup); // (2)
            // 指定客户端channel.
            b.channel(NioSocketChannel.class); // (3)
            // 直接option(), 没有childOption(), 因为没有parent group.
            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
            b.handler(new ChannelInitializer<SocketChannel>() {

                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    // 顺序不能错，先decode(), 再channelRead()
                    ch.pipeline().addLast(new TimeDecoder(), new TimeClientHandler());
                    // ch.pipeline().addLast( new TimeClientHandler(),new TimeDecoder());
                }
            });

            // Start the client.
            ChannelFuture f = b.connect(host, port).sync(); // (5)

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
