package cn.wy.biz.service.grpc.test2;

import java.util.concurrent.TimeUnit;

import cn.wy.grpc.service.TestRpcServiceGrpc;
import cn.wy.grpc.service.model.TestModel;
import io.grpc.ManagedChannel;
import io.grpc.netty.NettyChannelBuilder;

/**
 * Created by leslie on 2019/12/27.
 */
public class TestClient {

    private final TestRpcServiceGrpc.TestRpcServiceBlockingStub client;

    public TestClient(String host, int port){
        ManagedChannel channel = NettyChannelBuilder.forAddress(host, port).usePlaintext(true).build();
        client = TestRpcServiceGrpc.newBlockingStub(channel).withDeadlineAfter(60000, TimeUnit.MILLISECONDS);
    }

    public String sayHello(String name, Integer id) {
        TestModel.TestRequest request = TestModel.TestRequest.newBuilder().setId(id).setName(name).build();
        TestModel.TestResponse response = client.sayHello(request);
        return response.getMessage();
    }
}
