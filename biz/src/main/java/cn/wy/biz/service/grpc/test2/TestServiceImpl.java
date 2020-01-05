package cn.wy.biz.service.grpc.test2;

import cn.wy.grpc.service.TestRpcServiceGrpc;
import cn.wy.grpc.service.model.TestModel;
import io.grpc.stub.StreamObserver;

/**
 * Created by leslie on 2019/12/27.
 */
public class TestServiceImpl
//        implements
//        TestRpcServiceGrpc.TestRpcService
{

//    @Override
    public void sayHello(TestModel.TestRequest request, StreamObserver<TestModel.TestResponse> responseObserver) {
        String result = request.getName() + request.getId();
        TestModel.TestResponse response = TestModel.TestResponse.newBuilder().setMessage(result).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
