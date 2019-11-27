package cn.frequent.initialization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by leslie on 2019/11/27.
 */
public class Initialization1 {

    public static void main(String[] args) {

    }

    /**
     * <p>
     * 第一层括弧实际是定义了一个匿名内部类 (Anonymous Inner Class)，第二层括弧实际上是一个实例初始化块 (instance initializer block)，
     * </p>
     * <p>
     * 这个块在内部匿名类构造时被执行。然后这边还有一点需要明白，实例初始化块的代码在编译器编译过后，是放在类的构造函数里面的，并且是在原构造函数代码的前面。
     * </p>
     * <p>
     * 1.此种方式是匿名内部类的声明方式，所以引用中持有着外部类的引用。所以当时串行化这个集合时外部类也会被不知不觉的串行化，当外部类没有实现serialize接口时，就会报错。
     * 2.上例中，其实是声明了一个继承自HashMap的子类。然而有些串行化方法，例如要通过Gson串行化为json，或者要串行化为xml时，类库中提供的方式，是无法串行化Hashset或者HashMap的子类的，从而导致串行化失败。解决办法：重新初始化为一个HashMap对象：
     * new HashMap(map); 这样就可以正常初始化了
     * </p>
     */
    public void map1() {
        Map<String, String> m = new HashMap<String, String>() {

            {
                // 匿名内部类可以直接调用HashMap中的方法.
                put("a", "1");
                put("b", "1");
                put("c", "1");
            }

        };
        System.out.println(m);
    }

    public void map2() {
        // jdk8 不支持，不知道哪个版本支持.
        // Map<String, String> m2 = {"a":"1"};
    }

    public void arrayList1() {
        List<String> names = new ArrayList<String>() {

            {
                for (int i = 0; i < 10; i++) {
                    add("A" + i);
                }
            }
        };
    }

    public void arrayList2() {
        // List<String> a = ["a"];
    }
}
