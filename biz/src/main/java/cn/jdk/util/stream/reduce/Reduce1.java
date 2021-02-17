package cn.jdk.util.stream.reduce;

import java.util.stream.Stream;

import org.springframework.util.Assert;

/**
 * Created by leslie on 2020/9/22.
 */
public class Reduce1 {

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        String result = Stream.of("hello", "world").reduce("", (a, b) -> b + "-" + a);
        System.out.println(result);
        Assert.isTrue(result.equalsIgnoreCase("world-hello-"));

        result = Stream.of("a", "b").reduce("", (a, b) -> combineWithoutTrailingDash(a, b));
        System.out.println(result);

        // 方法引用.  如果是对象方法的话，可以使用 this::combineWithoutTrailingDash
        result = Stream.of("1", "2").reduce("", Reduce1::combineWithoutTrailingDash);
        System.out.printf(result);
    }

    private static String combineWithoutTrailingDash(String a, String b) {
        if (a.isEmpty()) {
            return b;
        }
        return b + "-" + a;
    }
}
