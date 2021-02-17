package cn.jdk.generics.genericclass;

/**
 * Created by leslie on 2020/11/5.
 */
public class TestGenericClass {

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        System.out.println("======test1=======");
        Box<Integer> ibox = new Box<>();
        ibox.set(3);
        System.out.println(ibox.get());

        Box<String> sbox = new Box<>();
        sbox.set("s");
        System.out.println(sbox.get());
    }
}
