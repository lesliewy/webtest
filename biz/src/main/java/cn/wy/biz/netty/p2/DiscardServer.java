package cn.wy.biz.netty.p2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * <pre>
 *     telnet localhost 8080
 * </pre>
 *
 * Created by leslie on 2020/3/9.
 */
public class DiscardServer {

    private int port;

    public DiscardServer(int port){
        this.port = port;
    }

    public void run() throws Exception {
        // eventLoopGroup 是线程池。
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap(); // (2)
            // group()中parentGroup接收客户端连接请求, childGroup处理连接的数据，将连接注册给worker.
            // channel() 实例化一个channel 来接收客户端连接。
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class) // (3)
             // ChannelInitializer 辅助配置handler
             .childHandler(new ChannelInitializer<SocketChannel>() { // (4)

                 @Override
                 public void initChannel(SocketChannel ch) throws Exception {
                     // ch.pipeline().addLast(new DiscardServerHandler());
                     // ch.pipeline().addLast(new TimeServerHandler());

                     // 需要注意handler的顺序问题，先encoder, 在serverhandler.
                     // ChannelHandlerContext.writeAndFlush() -> MessageToByteEncoder.encode() ->
                     // ch.pipeline().addLast( new TimeServerHandler(), new TimeEncoder());
                     ch.pipeline().addLast(new TimeEncoder2(), new TimeServerHandler());

                 }
             }).option(ChannelOption.SO_BACKLOG, 128) // (5) 配置接收连接请求的channel
             .childOption(ChannelOption.SO_KEEPALIVE, true); // (6) 配置被accepted 的channel

            // Bind and start to accept incoming connections.
            ChannelFuture f = b.bind(port).sync(); // (7)

            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync();
        } finally {
            // 关闭Netty, 即关闭eventLoopGroup
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        new DiscardServer(port).run();
    }
}
