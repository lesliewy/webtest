package cn.frequent.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

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
        asList2();
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
     * <pre>
     *    数组转成ArrayList: Arrays.asList()
     *    返回的是 java.util.Arrays$ArrayList，  Arrays 的内部类ArrayList, 而不是 java.util.ArrayList;
     *    java.util.Arrays$ArrayList: 不能使用add, remove 等操作.
     *    java.util.Arrays$ArrayList:  和原始数组共用一个数组, 修改的话会相互影响.
     * </pre>
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

        // 和原数组相互影响.
        Integer[] c = new Integer[] { 1, 2 };
        List<Integer> cList = Arrays.asList(c);
        cList.set(0, 111);
        c[1] = 222;
        System.out.println("changed c: " + Arrays.toString(c));
        System.out.println("changed cList: " + cList);

        // 简写. 如果是对象，直接在() 内new YourObject(), "," 分隔.
        List<Integer> c1 = Arrays.asList(1, 2, 3);
        System.out.println(c1);
    }

    /**
     * <pre>
     *    使用guava
     *    可以add,remove;
     *    和原数组不影响;
     * </pre>
     */
    public static void asList2() {
        System.out.println("===========asList2=============");
        Integer[] a = new Integer[] { 1, 2, 3 };
        List<Integer> aList = Lists.newArrayList(a);
        aList.add(4);
        aList.set(0, 111);
        System.out.println(Arrays.toString(a));
        System.out.println(aList);
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
