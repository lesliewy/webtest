package cn.wy.biz.bytecode.instrument.p2;

import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

/**
 * Created by leslie on 2020/4/30.
 */
public class Sender {

    private static final int SERVER_PORT = 9876;

    public static void send(Object response, Object[] request, String className, String methodName) {
        Message message = new Message(response, request, className, methodName);
        try {
            Socket socket = new Socket("localhost", SERVER_PORT);
            socket.getOutputStream().write(message.toString().getBytes());
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class Message {

        private Object   response;
        private Object[] request;
        private String   className;
        private String   methodName;

        public Message(Object response, Object[] request, String className, String methodName){
            this.response = response;
            this.request = request;
            this.className = className;
            this.methodName = methodName;
        }

        @Override
        public String toString() {
            return "Message{" + "response=" + response + ", request=" + Arrays.toString(request) + ", className='"
                   + className + '\'' + ", methodName='" + methodName + '\'' + '}';
        }
    }
}
