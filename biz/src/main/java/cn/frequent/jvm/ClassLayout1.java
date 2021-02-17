package cn.frequent.jvm;

import java.nio.ByteOrder;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 * Created by leslie on 2020/11/30.
 */
public class ClassLayout1 {

    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test1() {
        System.out.println("=====test1=====");
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        String a = "abc";
        System.out.println(ClassLayout.parseInstance(a).toPrintable());

        User user = new User("wy", 21);
        System.out.println(ClassLayout.parseInstance(user).toPrintable());
    }

    private static void test2() {
        System.out.println("=====test2=====");
        Object object = new Object();

        // 查看字节序
        System.out.println(ByteOrder.nativeOrder());
        // 打印当前jvm信息
        System.out.println(VM.current().details());
        // 对象布局.  如果不调用hashCode()， layout中的hashcode 是空的.
        System.out.println(object.hashCode());
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
    }

    //     00000000 00000000 00000000 01110111 00001100 00101110 01101011 00000001

    private static class User {

        private String name;
        private int    age;

        public User(String name, int age){
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
