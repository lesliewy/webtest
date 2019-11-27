package cn.frequent.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by leslie on 2019/11/26.
 */
public class Arrays1 {

    public static void main(String[] args) {
        System.out.println("===asList:");
        asList1();
        System.out.println("===collections:");
        collections1();
        System.out.println("===stream:");
        stream1();
    }

    /**
     * Arrays.asList()
     */
    public static void asList1() {
        Integer[] a = new Integer[2];
        a[0] = 0;
        a[1] = 1;
        List<Integer> b = Arrays.asList(a);
        b.get(0);
        // UnsupportedOperationException, Arrays.asList()返回的是java.util.Arrays.ArrayList, Arrays 中静态类. 不能增删.
        // b.add(1);

        // 再包装下, 就可以增删.
        b = new ArrayList<>(Arrays.asList(a));
        b.add(1);

        int[] c = new int[] { 1, 2 };
    }

    public static void asList2() {

    }

    public static void collections1() {
        Integer[] a = new Integer[] { 2, 3 };
        List<Integer> b = new ArrayList<>();
        Collections.addAll(b, a);
        System.out.println(b);
    }

    public static void stream1() {
        Integer[] a = new Integer[] { 1, 2 };
        List<Integer> b = Arrays.stream(a).collect(Collectors.toList());
        System.out.println(b);

        // 还可以包装.
        int[] c = new int[] { 1, 2, 3 };
        List<Integer> c1 = Arrays.stream(c).boxed().collect(Collectors.toList());

        System.out.println(c1);

        String d1 = "1,2,3,4";
        List<Integer> d2 = Arrays.stream(d1.split(",")).map(i -> Integer.valueOf(i)).collect(Collectors.toList());
        System.out.println(d2);
    }
}
