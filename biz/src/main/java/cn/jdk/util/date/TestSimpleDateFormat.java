package cn.jdk.util.date;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * Created by leslie on 2020/11/30.
 */
public class TestSimpleDateFormat {

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
    }

    private static void test1() {
        System.out.println("=====test1=======");
        final String source = "2019-01-11";
        System.out.println(SimpleDateFormat1.parse(source));
    }

    /**
     * <pre>
     *     多线程时出现异常.
     * </pre>
     */
    private static void test2() {
        System.out.println("=====test2=======");
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        final String source = "2019-01-11";
        System.out.println(":: parsing date string ::");
        IntStream.rangeClosed(0,
                              50).forEach((i) -> executorService.submit(() -> System.out.println(SimpleDateFormat1.parse(source))));
        executorService.shutdown();
    }

    /**
     * <pre>
     *     使用ThreadLocal。
     * </pre>
     */
    private static void test3() {
        System.out.println("=====test3=======");
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        final String source = "2019-01-11";
        System.out.println(":: parsing date string ::");
        IntStream.rangeClosed(0,
                              50).forEach((i) -> executorService.submit(() -> System.out.println(SimpleDateFormatThreadLocal.parse(source))));
        executorService.shutdown();
    }

    /**
     * <pre>
     * java8
     * </pre>
     */
    private static void test4() {
        System.out.println("=====test4=======");
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        final String source = "2019-01-11";
        System.out.println(":: parsing date string ::");
        IntStream.rangeClosed(0,
                              50).forEach((i) -> executorService.submit(() -> System.out.println(DateTimeFormatter1.parse(source))));
        executorService.shutdown();
    }

}
