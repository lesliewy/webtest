package cn.jdk.generics.genericmethod;

/**
 * Created by leslie on 2020/11/5.
 */
public class TestGenericMethod {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        GenericMethodPair<Integer, String> p1 = new GenericMethodPair<>(1, "apple");
        GenericMethodPair<Integer, String> p2 = new GenericMethodPair<>(2, "pear");
        boolean same = GenericMethodUtil.<Integer, String> compare(p1, p2);
        System.out.println(same);

        // jdk7, 8 支持 type inference, 类型推导. 调用泛型方法时不需要添加参数类型.
        same = GenericMethodUtil.compare(p1, p2);
        System.out.println(same);
    }
}
