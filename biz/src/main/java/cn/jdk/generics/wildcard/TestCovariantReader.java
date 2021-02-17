package cn.jdk.generics.wildcard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 *     通配符.
 *     Apples extends Fruit, 在泛型中，List<Fruit>与List<Apple>之间并没有任何的关系。
 *
 *     PECS: Producer Extends, Consumer Super
 * </pre>
 * 
 * Created by leslie on 2020/11/5.
 */
public class TestCovariantReader {

    public static void main(String[] args) {
        test1();
        test4();
        test5();
    }

    /**
     * <pre>
     *    List<? extends T>
     *        方法参数定义成这样，就可以处理类似 List<Apples>, List<Orange> 这种继承自Fruit的list了.
     * </pre>
     */
    private static void test1() {
        CovariantReader<Fruit> fruitReader = new CovariantReader<Fruit>();
        List<Apple> apples = Arrays.asList(new Apple());
        List<Fruit> fruit = Arrays.asList(new Fruit());
        Fruit f = fruitReader.readCovariant(fruit);
        // CovariantReader.readCovariant(List<T> list) 这种形式的话，这里的apples就会编译报错.
        Fruit a = fruitReader.readCovariant(apples);
    }

    /**
     * <pre>
     *     PECS 原则.
     *     List<? extends T> 这种形式的list, 可以list.get(), 但不可以list.add()。
     *     因为list可能是 List<Apple> List<Orange>,  这样的话,list.add() 就可能给List<Orange> add(Apple)
     *     所以，这种形式, 将它视为Producer向外提供(get)元素，而不能作为Consumer来对外获取(add)元素。
     * </pre>
     */
    private static void test2() {
        // Wildcards allow covariance:
        List<? extends Fruit> flist = new ArrayList<Apple>();
        // Compile Error: can't add any type of object:
        // flist.add(new Apple())
        // flist.add(new Orange())
        // flist.add(new Fruit())
        // flist.add(new Object())
        flist.add(null); // Legal but uninteresting

        // We Know that it returns at least Fruit:
        Fruit f = flist.get(0);
    }

    /**
     * <pre>
     *     PECS 原则.
     *     List<? super T>  这种形式的list, 可以list.add(), 但不可以list.get().
     *     因为list可能是 List<Apple> List<Orange>, 这样的话，list就可以get到不同对象了.
     * </pre>
     */
    private static void test3() {
        CovariantWriter<Apple> fruitWriter = new CovariantWriter<Apple>();
        List<Apple> apples = new ArrayList<>();
        List<Fruit> fruit = new ArrayList<>();
        fruitWriter.writeWithWildcard(apples, new Apple());
        fruitWriter.writeWithWildcard(fruit, new Apple());
    }

    /**
     * <pre>
     *     PE 和 CS 一起使用.
     * </pre>
     * 
     * @param dest
     * @param src
     * @param <T>
     */
    private static <T> void copy(List<? super T> dest, List<? extends T> src) {
        for (int i = 0; i < src.size(); i++) {
            dest.set(i, src.get(i));
        }
    }

    /**
     * <pre>
     *     泛型擦除带来的问题.
     * </pre>
     */
    private static void test4() {
        System.out.println("=======test4=======");
        // 1. 不允许创建泛型数组. 如果允许，JVM不知道stringLists[0], stringLists[1] 的实际类型， 如果出现问题， 这样的错误很难排查.
        // List<Integer>[] arrayOfLists = new List<Integer>[2];
        Object[] stringLists = new List[2]; // compiler error, but pretend it's allowed
        stringLists[0] = new ArrayList<String>(); // OK
        // 不会抛出异常。 An ArrayStoreException should be thrown, but the runtime can't detect it.
        stringLists[1] = new ArrayList<Integer>();

        Class c1 = new ArrayList<String>().getClass();
        Class c2 = new ArrayList<Integer>().getClass();
        System.out.println(c1 == c2); // true
    }

    /**
     * <pre>
     *     2, JVM 会生成bridge-method， 调用父类中的setData(String), 会强制的转成Integer.
     * </pre>
     */
    private static void test5() {
        System.out.println("======test5======");
        MyNode mn = new MyNode(5);
        // 这里没有使用 Node<Integer>, 如果用的话，编译期就会报错。
        Node n = mn; // A raw type - compiler throws an unchecked warning
        n.setData("Hello"); // Causes a ClassCastException to be thrown.
        // Integer x = mn.data;
    }

    /**
     * <pre>
     *     3, 无法直接使用类型参数创建实例.
     * </pre>
     * 
     * @param list
     * @param <E>
     */
    private static <E> void append(List<E> list) {
        // E elem = new E(); // compile-time error
        // list.add(elem);
    }

    // 使用反射, 同样可以生成类型参数的对象.
    private static <E> void append(List<E> list, Class<E> cls) throws Exception {
        E elem = cls.newInstance(); // OK
        list.add(elem);
    }

    /**
     * <pre>
     *     4, 无法使用instanceof.
     * </pre>
     */
    private static void test6() {
        List<Integer> list = new ArrayList<>();
        // if (list instanceof ArrayList<Integer>) { // compile-time error
        // }

        // 使用通配符可以.
        if (list instanceof ArrayList<?>) { // OK; instanceof requires a reifiable type
        }
    }

    public static class Node<T> {

        public T data;

        public Node(T data){
            this.data = data;
        }

        public void setData(T data) {
            System.out.println("Node.setData");
            this.data = data;
        }
    }

    public static class MyNode extends Node<Integer> {

        public MyNode(Integer data){
            super(data);
        }

        @Override
        public void setData(Integer data) {
            System.out.println("MyNode.setData");
            super.setData(data);
        }

    }
}
