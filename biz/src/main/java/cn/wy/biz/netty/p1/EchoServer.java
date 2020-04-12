package cn.wy.biz.netty.p1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Created by leslie on 2020/3/9.
 */
public class EchoServer {

    private final int port;

    public EchoServer(int port){
        this.port = port;
    }

    public void run() throws Exception {

        // Configure the server. 通常bossGroup用于accept客户端连接.
        EventLoopGroup bossGroup = new NioEventLoopGroup();

        // workerGroup 用于处理客户端数据的读写。
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // serverBootstrap 用于启动服务的辅助类.
            ServerBootstrap b = new ServerBootstrap();

            // 指定channel 用于接收客户端请求.
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
             // 添加handler
             .option(ChannelOption.SO_BACKLOG,
                     100).handler(new LoggingHandler(LogLevel.INFO)).childHandler(new ChannelInitializer<SocketChannel>() {

                         // (4)
                         @Override
                         public void initChannel(SocketChannel ch) throws Exception {
                             ch.pipeline().addLast( // new LoggingHandler(LogLevel.INFO),
                                                    new EchoServerHandler());
                         }
                     });

            // Start the server.
            ChannelFuture f = b.bind(port).sync();
            // (5)
            // Wait until the server socket is closed.
            f.channel().closeFuture().sync();
        } finally {
            // Shut down all event loops to terminate all threads.
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port;

        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8080;
        }

        new EchoServer(port).run();

    }

}
