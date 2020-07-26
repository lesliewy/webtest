package cn.frequent.singleton.destroy;

import java.io.*;

/**
 * Created by leslie on 2020/6/19.
 */
public class DestroyByDeserialize {

    /**
     * <pre>
     *     反序列化得到的对象是一个新对象。
     * </pre>
     * 
     * @param args
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        test1();
        test2();
        test3();
    }

    private static void test1() throws IOException, ClassNotFoundException {
        SerializableElvis elvis1 = SerializableElvis.INSTANCE;
        FileOutputStream fos = new FileOutputStream("a.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(elvis1);
        oos.flush();
        oos.close();

        SerializableElvis elvis2 = null;
        FileInputStream fis = new FileInputStream("a.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        elvis2 = (SerializableElvis) ois.readObject();
        System.out.println("elvis1 == elvis2 ? ===>" + (elvis1 == elvis2));
    }

    private static void test2() throws IOException, ClassNotFoundException {
        SerializableElvisImprove1 elvis1 = SerializableElvisImprove1.INSTANCE;
        FileOutputStream fos = new FileOutputStream("a.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(elvis1);
        oos.flush();
        oos.close();

        SerializableElvisImprove1 elvis2 = null;
        FileInputStream fis = new FileInputStream("a.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        elvis2 = (SerializableElvisImprove1) ois.readObject();
        System.out.println("elvis1 == elvis2 ? ===>" + (elvis1 == elvis2));
    }

    private static void test3() throws IOException, ClassNotFoundException {
        ElvisEnum elvis1 = ElvisEnum.INSTANCE.INSTANCE;
        FileOutputStream fos = new FileOutputStream("a.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(elvis1);
        oos.flush();
        oos.close();

        ElvisEnum elvis2 = null;
        FileInputStream fis = new FileInputStream("a.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        elvis2 = (ElvisEnum) ois.readObject();
        System.out.println("elvis1 == elvis2 ? ===>" + (elvis1 == elvis2));

    }
}
