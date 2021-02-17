package cn.algo.trick.bit;

/**
 * Created by leslie on 2021/1/21.
 */
public class Bit1 {

    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test1() {
        System.out.println("=====test1=====");
        // ('a' | ' ') = 'a'
        System.out.println((char) ('a' | ' '));
        // ('A' | ' ') = 'a'
        System.out.println((char) ('A' | ' '));

        // ('b' & '_') = 'B'
        System.out.println((char) ('b' & '_'));
        // ('B' & '_') = 'B'
        System.out.println((char) ('B' & '_'));

        // ('d' ^ ' ') = 'D'
        System.out.println((char) ('d' ^ ' '));
        // ('D' ^ ' ') = 'd'
        System.out.println((char) ('D' ^ ' '));
    }

    /**
     * <pre>
     *     判断是否异号.
     *     1, 使用if/else   麻烦.
     *     2, 利用乘积或者商来判断两个数是否异号，但是这种处理方式可能造成溢出
     * </pre>
     */
    private static void test2() {
        System.out.println("=====test2=====");
        int x = -1, y = 2;
        boolean f = ((x ^ y) < 0); // true
        System.out.println(f);

        int a = 3, b = 2;
        boolean g = ((a ^ b) < 0); // false
        System.out.println(g);
    }
}
