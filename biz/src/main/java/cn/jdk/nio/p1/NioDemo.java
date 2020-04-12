package cn.jdk.nio.p1;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 参考: https://blog.csdn.net/forezp/article/details/88414741
 * Channel: 类似于传统IO中的stream.
 *   FileChannel:  文件.
 *   DatagramChannel: UDP
 *   SocketChannel:  TCP client端.
 *   ServerSocketChannel: TCP Server 端.
 *
 * buffer:  capacity, position, limit
 *    flip(), clear(), compact(), mark()/reset(),  rewind(): rewind将position设置为0，可以重读buffer中数据.
 *    分配空间:
 *        ByteBuffer.allocate(1024);
 *        allocateDirector
 *    向buffer中写:
 *        fileChannel.read(buf);   :  这里的read 是针对channel 来说的. 读channel, 意味着要写入buffer.   write同.
 *        buf.put(…)
 *    从buffer中读:
 *        channel.write(buf);
 *        buf.get()
 *
 *
 * Created by leslie on 2019/11/20.
 */
public class NioDemo {

    private static final String filePath = "/Users/leslie/MyProjects/Test3/pom.xml";

    public static void method1() {
        RandomAccessFile aFile = null;
        try {
            aFile = new RandomAccessFile(filePath, "rw");
            // FileChannel fileChannel = aFile.getChannel();
            // 也可以通过 FileInputStream 来获取channel.
            FileChannel fileChannel = new FileInputStream(filePath).getChannel();
            ByteBuffer buf = ByteBuffer.allocate(1024);
            // 将数据从channel 读入 buffer, 即写入buffer.
            int bytesRead = fileChannel.read(buf);
            System.out.println(bytesRead);
            while (bytesRead != -1) {
                // 调整position, limit位置, 准备读取数据. position 和 limit 之间的就是可以被读取的. 也就是从写模式跳转到读模式.
                buf.flip();
                while (buf.hasRemaining()) {
                    // 从buffer中读出数据.
                    System.out.print((char) buf.get());
                }
                // 将未读数据数据移至buffer开始处, position 移至最后一个未读数据, limit 移至末尾, 此时buffer可以接收写入数据了，不会覆盖未读数据.
                buf.compact();
                bytesRead = fileChannel.read(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (aFile != null) {
                    aFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 传统IO.
     */
    public static void method2() {
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(filePath));
            byte[] buf = new byte[1024];
            int bytesRead = in.read(buf);
            while (bytesRead != -1) {
                for (int i = 0; i < bytesRead; i++) {
                    System.out.print((char) buf[i]);
                }
                bytesRead = in.read(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
         method1();
//        method2();
    }
}
