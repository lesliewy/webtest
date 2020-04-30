package cn.wy.biz.bytecode.instrument.p2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

/**
 * Created by leslie on 2020/4/30.
 */
public class TraceAgentMain {

    private static final int SERVER_PORT = 9876;

    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException,
                                           AgentInitializationException {
        new Server().start();
        // attach的进程
        VirtualMachine vm = VirtualMachine.attach("1214");
        // 加载agent并指明需要采集信息的类和方法
        vm.loadAgent("/Users/leslie/MyProjects/GitHub/webtest/biz/src/main/java/cn/wy/biz/bytecode/instrument/p2/wyagent.jar",
                     "cn.wy.biz.bytecode.instrument.p2.HelloTraceAgent.sayHi");
        vm.detach();
    }

    private static class Server implements Runnable {

        @Override
        public void run() {
            try {
                ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
                while (true) {
                    Socket socket = serverSocket.accept();
                    InputStream input = socket.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    System.out.println("receive message:" + reader.readLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void start() {
            Thread thread = new Thread(this);
            thread.start();
        }
    }
}
