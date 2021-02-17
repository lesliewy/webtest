package cn.frequent.jvm;

/**
 * <pre>
 *     打印NMT信息: -XX:NativeMemoryTracking=summary -XX:+UnlockDiagnosticVMOptions -XX:+PrintNMTStatistics   应用退出时输出.
 * </pre>
 * 
 * Created by leslie on 2020/12/12.
 */
public class NativeMemoryTracking {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        System.out.println("hello world.");
    }
}
