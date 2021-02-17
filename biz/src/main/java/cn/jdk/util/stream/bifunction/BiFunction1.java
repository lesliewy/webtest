package cn.jdk.util.stream.bifunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

/**
 * <pre>
 *     JDK中只提供单参数和两个参数的函数式接口.   需要更多参数,参阅柯里化获得更多想法.？？？？
 *     单参数:
 *        Function
 *        比如: List<String> mapped = Stream.of("hello", "world").map(word -> word + "!").collect(Collectors.toList());
 *     双参数:
 *        BiFunction
 * </pre>
 * 
 * Created by leslie on 2020/9/22.
 */
public class BiFunction1 {

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
    }

    public static void test1() {
        System.out.println("===test1===");
        List<String> list1 = Arrays.asList("a", "b", "c");
        List<Integer> list2 = Arrays.asList(1, 2, 3);
        List<String> result = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            result.add(list1.get(i) + list2.get(i));
        }
        System.out.println(result);
    }

    public static void test2() {
        System.out.println("===test2===");
        List<String> list1 = Arrays.asList("a", "b", "c");
        List<Integer> list2 = Arrays.asList(1, 2, 3);
        List<String> result = listCombiner(list1, list2, (a, b) -> a + b);
        System.out.println(result);
    }

    public static void test3() {
        System.out.println("===test3===");
        List<Double> list1 = Arrays.asList(1.0d, 2.1d, 3.3d);
        List<Float> list2 = Arrays.asList(0.1f, 0.2f, 4f);
        List<Boolean> result = listCombiner(list1, list2, (a, b) -> a > b);
        System.out.println(result);
    }

    /**
     * <pre>
     *   带泛型的BiFunction.
     *   combiner 参数即调用方的lambda表达式的函数, 由使用者指定.
     * </pre>
     */
    private static <T, U, R> List<R> listCombiner(List<T> list1, List<U> list2, BiFunction<T, U, R> combiner) {
        List<R> result = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            // 调用 BiFunction 的 apply 方法 得到结果
            result.add(combiner.apply(list1.get(i), list2.get(i)));
        }
        return result;
    }

    public static void test4() {
        System.out.println("===test4===");
        List<Double> list1 = Arrays.asList(1.0d, 2.1d, 3.3d);
        List<Float> list2 = Arrays.asList(0.1f, 0.2f, 4f);
        // 将lambda抽象成独立的方法.
        List<Boolean> result = listCombiner(list1, list2, BiFunction1::firstIsGreaterThanSecond);
        System.out.println(result);
    }

    private static boolean firstIsGreaterThanSecond(Double a, Float b) {
        return a > b;
    }

    public static void test5() {
        System.out.println("===test5===");
        List<Double> list1 = Arrays.asList(1.0d, 2.1d, 3.3d);
        List<Double> list2 = Arrays.asList(0.1d, 0.2d, 4d);
        // 返回值类型改变.
        List<Integer> result = listCombiner(list1, list2, Double::compareTo);
        System.out.println(result);
    }

    public static void test6() {
        System.out.println("===test6===");
        List<Double> list1 = Arrays.asList(1.0d, 2.1d, 3.3d);
        List<Double> list2 = Arrays.asList(0.1d, 0.2d, 4d);
        // 使用 BiFunction.andThen来继续操作.
        List<Boolean> result = listCombiner(list1, list2, asBiFunction(Double::compareTo).andThen(i -> i > 0));
        System.out.println(result);
    }

    /**
     * <pre>
     * 必须显式的将lambda转为BiFunction
     * </pre>
     * 
     * @return
     */
    private static <T, U, R> BiFunction<T, U, R> asBiFunction(BiFunction<T, U, R> function) {
        return function;
    }

}
