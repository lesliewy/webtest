package cn.frequent.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 数组和ArrayList之间的转换.
 * </p>
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

        System.out.println("===toArray1:");
        toArray1();
        System.out.println("===toArray2:");
        toArray2();
    }

    /**
     * 数组转成ArrayList: Arrays.asList()
     */
    public static void asList1() {
        Integer[] a = new Integer[2];
        a[0] = 0;
        a[1] = 1;
        List<Integer> b = Arrays.asList(a);
        System.out.println(b);

        b.get(0);
        // UnsupportedOperationException, Arrays.asList()返回的是java.util.Arrays.ArrayList, Arrays 中静态类. 不能增删.
        // b.add(1);

        // 再包装下, 就可以增删.
        b = new ArrayList<>(Arrays.asList(a));
        b.add(1);

        int[] c = new int[] { 1, 2 };

        // 简写. 如果是对象，直接在() 内new YourObject(), "," 分隔.
        List<Integer> c1 = Arrays.asList(
                1,2,3
        );
        System.out.println(c1);
    }

    public static void asList2() {

    }

    /**
     * 数组转成ArrayList: Collections
     */
    public static void collections1() {
        Integer[] a = new Integer[] { 2, 3 };
        List<Integer> b = new ArrayList<>();
        Collections.addAll(b, a);
        System.out.println(b);
    }

    /**
     * 数组转成ArrayList: Arrays.stream() 方式
     */
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

    /**
     * ArrayList 转成数组: arrayList.toArray();
     */
    public static void toArray1() {
        List<Integer> arrayList = new ArrayList<Integer>() {

            {
                add(1);
                add(2);
            }
        };
        // 可以装成对象数组, 但不能转为int[]
        Integer[] arr = arrayList.toArray(new Integer[arrayList.size()]);
        System.out.println(arr);
    }

    /**
     *
     */
    public static void toArray2() {
        List<Integer> arrayList = new ArrayList<Integer>() {

            {
                add(1);
                add(2);
            }
        };
        int[] arr = arrayList.stream().mapToInt(i -> i.intValue()).toArray();
        System.out.println(arr);
    }
}
