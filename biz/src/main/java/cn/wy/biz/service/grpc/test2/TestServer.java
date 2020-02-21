package cn.wy.biz.service.grpc.test2;

import cn.wy.grpc.service.TestRpcServiceGrpc;
import io.grpc.internal.ServerImpl;
import io.grpc.netty.NettyServerBuilder;

/**
 * Created by leslie on 2019/12/27.
 */
public class TestServer {

    public static void main(String[] args) throws Exception {

        /*
        ServerImpl server = NettyServerBuilder.forPort(50010).addService(TestRpcServiceGrpc.bindService(new TestServiceImpl())).build();
        server.start();
        server.awaitTermination();// 阻塞直到退出
        */
    }
}
