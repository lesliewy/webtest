package cn.jdk.io.serialize;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by leslie on 2020/9/7.
 */
public class DeserialTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        test1();
    }

    public static void test1() throws IOException, ClassNotFoundException {
        // 反编译时需要有一个同样的类.
        // 反编译时如果修改Person的serialVersionUID, 会抛出异常: java.io.InvalidClassException
        Person person;
        FileInputStream fis = new FileInputStream("Person.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        person = (Person) ois.readObject();
        ois.close();
        System.out.println("Person Deserial" + person);
    }

    /**
     * <pre>
     *     两处serialVersionUID一致，如果A端增加一个字段，然后序列化，而B端不变，然后反序列化
     *     执行序列化，反序列化正常，但是A端增加的字段丢失(被B端忽略)。
     * </pre>
     */
    public static void test2(){

    }

    /**
     * 两处serialVersionUID一致，如果B端减少一个字段，A端不变，
     * 序列化，反序列化正常，B端字段少于A端，A端多的字段值丢失(被B端忽略)
     */
    public static void test3(){

    }

    /**
     * 两处serialVersionUID一致，如果B端增加一个字段，A端不变
     * 序列化，反序列化正常，B端新增加的int字段被赋予了默认值0
     */
    public static void test4(){

    }
}
