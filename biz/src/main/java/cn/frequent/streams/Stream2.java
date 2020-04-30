package cn.frequent.streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections.CollectionUtils;

import com.google.common.collect.Lists;

/**
 * Created by leslie on 2019/12/22.
 */
public class Stream2 {

    public static void main(String[] args) {

        Stream2 s2 = new Stream2();
        // s2.testFilter1();
        // s2.testMap1();
        // s2.testMap2();
        // s2.testFlatMap1();

        // s2.testSorted1();
        // s2.testDistinct();
//        s2.testLimit();
        s2.testPeek();
    }

    class Student {

        private String name;

        private String sex;

        public Student(String sex){
            this.sex = sex;
        }

        public Student(String name, String sex){
            this.name = name;
            this.sex = sex;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        @Override
        public String toString() {
            return "name: " + name + "; sex: " + sex;
        }
    }

    /**
     * filter 过滤:
     */
    private void testFilter1() {
        String[] strArr = new String[] { "aa", "bb", "cc" };
        Stream<String> streamArr = Stream.of(strArr);
        streamArr.filter(str -> str.startsWith("a")).forEach(System.out::println);

        List<Student> list = Arrays.asList(new Student("G"), new Student("G"), new Student("M"));
        list.stream().filter(student -> student.getSex().equals("G")).forEach(student -> System.out.println(student.toString()));

        List nums = Arrays.asList(1, 3, null, 8, 7, 8, 13, 10);
        nums.stream().filter(num -> num != null).distinct().forEach(System.out::println);
        Stream.of(1, 2, 3, 4, 5).filter(item -> item > 3).forEach(System.out::println);

        /*
         * list.stream().filter(TestObject::isLeader).collect(Collectors.toList()); String result =
         * maps.entrySet().stream().filter(map -> "something".equals(map.getValue())).map(map ->
         * map.getValue()).collect(Collectors.joining());
         */

        // Map -> Stream -> Filter -> MAP
        Map<Integer, String> maps = new HashMap() {

            {
                put(1, "a");
                put(2, "b");
                put(3, "c");
            }
        };
        Map<Integer, String> collect = maps.entrySet().stream().filter(map -> map.getKey() == 2).collect(Collectors.toMap(p -> p.getKey(),
                                                                                                                          p -> p.getValue()));
        System.out.println(collect);
    }

    /**
     * map 遍历和转换操作
     */
    private void testMap1() {
        String[] strArr = new String[] { "aa", "bb", "cc" };
        Stream<String> streamArr = Stream.of(strArr);
        streamArr.map(String::toLowerCase);
        List<TestObject> list = new ArrayList<>();
        TestObject testObject1 = new TestObject("a1", 12);
        TestObject testObject2 = new TestObject("b23", 13);
        TestObject testObject3 = new TestObject("c232", 14);
        list.add(testObject1);
        list.add(testObject2);
        list.add(testObject3);

        // 获取对象中的某个属性, 可以多个map对属性进行转换, 最后组装成list.
        list.stream().map(TestObject::getName).collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("======");
        list.stream().map(TestObject::getName).map(String::length).collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("======");
        Stream.of("a", "b", "hello").map(item -> item.toUpperCase()).forEach(System.out::println);
        System.out.println("======");

        List<Integer> nums = Arrays.asList(1, 2, 3, 4);
        List<Integer> squareNums = nums.stream().map(n -> n * n).collect(Collectors.toList());
        System.out.println(squareNums);
    }

    private void testMap2() {
        // List<Student> 提取其中的sex 信息, Map<name, sex>
        List<Student> students = Arrays.asList(new Student("wang", "M"), new Student("zhang", "M"),
                                               new Student("chen", "F"));
        Map<String, String> sexMap = students.stream().collect(Collectors.toMap(s -> s.getName(), s -> s.getSex()));
        System.out.println(sexMap);

        /*
         * List<Student> 修改某个Student的sex 信息, 最后一定要加collect, 否则修改不成功. map() 需要返回值.
         */
        students.stream().filter(s -> s.getName().equals("wang")).map(s -> {
            s.setSex("F");
            return null;
        }).collect(Collectors.toList());
        students.stream().forEach(s -> System.out.println(s));
    }

    // flatMap 将流展开
    private void testFlatMap1() {
        List<TestObject> list = new ArrayList<>();
        TestObject testObject1 = new TestObject("a1", 12);
        TestObject testObject2 = new TestObject("b23", 13);
        TestObject testObject3 = new TestObject("c232", 14);
        list.add(testObject1);
        list.add(testObject2);
        list.add(testObject3);
        List<GoodInfo> goodsList = new ArrayList<>();
        GoodInfo good1 = new GoodInfo("id1");
        GoodInfo good2 = new GoodInfo("id2");
        goodsList.add(good1);
        goodsList.add(good2);
        testObject3.setGoods(goodsList);
        // 对象中带有list, 获取该list中的对象的属性.
        List<String> goodIds = list.stream().filter(t1 -> CollectionUtils.isNotEmpty(t1.getGoods())).flatMap(goods -> goods.getGoods().stream()).map(o2 -> o2.getId()).collect(Collectors.toList());
        for (String id : goodIds) {
            System.out.println("good ids: " + id);
        }

        List<String> list1 = new ArrayList<>();
        list1.add("aa");
        list1.add("bb");
        List<String> list2 = new ArrayList<>();
        list2.add("cc");
        list2.add("dd");
        Stream.of(list1, list2).flatMap(str -> str.stream()).collect(Collectors.toList());

        Stream<List<Integer>> inputStream = Stream.of(Arrays.asList(1), Arrays.asList(2, 3), Arrays.asList(4, 5, 6));
        Stream<Integer> outputStream = inputStream.flatMap((childList) -> childList.stream());

        Optional.of(new Outer()).flatMap(o -> Optional.ofNullable(o.nested)).flatMap(n -> Optional.ofNullable(n.inner)).flatMap(i -> Optional.ofNullable(i.foo)).ifPresent(System.out::println);
    }

    class Outer {

        Nested nested;
    }

    class Nested {

        Inner inner;
    }

    class Inner {

        String foo;
    }

    private void testLimit() {
        // limit 提取子流 最大长度限制
        Stream.of(1, 2, 3, 4, 5).limit(2).forEach(System.out::println);
    }

    /**
     * peek(): 中间操作. peek 的参数是Cosumer，是不带返回值的, 对stream中的元素进行某些操作，操作之后并不放回stream中。
     * map(): 也是中间操作，参数是Function, 是带返回值, 对stream中的元素操作后会放回stream中.
     */
    private void testPeek() {
        // peek 产生相同的流，支持每个元素调用一个函数
        System.out.println("1. ================");
        String[] strArr = new String[] { "aa", "bb", "cc" };
        Stream<String> streamArr = Stream.of(strArr);
        // 并不会变成大写, map 可以.
//        streamArr.peek(u -> u.toUpperCase()).forEach(System.out::println);
        streamArr.map(u -> u.toUpperCase()).forEach(System.out::println);

        // peek 主要用于debug, 在终止操作前输出中间值.
        System.out.println("2. ================");
        Stream.of("one", "two", "three","four").filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
    }

    private void testDistinct() {
        Stream.of("aa", "bb", "aa").distinct();
        Stream.of(1, 2, 3, 1, 2, 3).distinct().forEach(System.out::println);

        // 根据id去重
        // List<Person> unique = appleList.stream().collect(collectingAndThen(toCollection(() -> new
        // TreeSet<>(comparingLong(Apple::getId))),ArrayList::new));
    }

    /**
     * sorted 按照list 中对象的某个属性对list排序.
     */
    private void testSorted1() {
        System.out.println("1 =============");
        List<TestObject> humans = Lists.newArrayList(new TestObject("Sarah", 10), new TestObject("Jack", 12));
        Collections.sort(humans, Comparator.comparing(TestObject::getName));
        humans.stream().forEach(s -> System.out.println(s));

        // 反转排序
        System.out.println("2 =============");
        Collections.sort(humans, Comparator.comparing(TestObject::getName).reversed());
        humans.stream().forEach(s -> System.out.println(s));

        System.out.println("3 =============");
        humans.sort((h1, h2) -> h1.getName().compareTo(h2.getName()));
        humans.stream().forEach(s -> System.out.println(s));

        // 反转排序
        System.out.println("4 =============");
        humans.sort((h1, h2) -> h2.getName().compareTo(h1.getName()));
        humans.stream().forEach(s -> System.out.println(s));

        // 反转排序
        System.out.println("5 =============");
        Comparator<TestObject> comparator = (h1, h2) -> h1.getName().compareTo(h2.getName());
        humans.sort(comparator.reversed());
        humans.stream().forEach(s -> System.out.println(s));

        // 多条件排序
        System.out.println("6 =============");
        humans.add(new TestObject("Sarah", 11));
        humans.sort((lhs, rhs) -> {
            if (lhs.getName().equals(rhs.getName())) {
                return lhs.getAge() - rhs.getAge();
            } else {
                return lhs.getName().compareTo(rhs.getName());
            }
        });
        humans.stream().forEach(s -> System.out.println(s));
        System.out.println("7 =============");
        humans.sort(Comparator.comparing(TestObject::getAge).thenComparing(TestObject::getName));
        humans.stream().forEach(System.out::println);

    }

    class TestObject {

        private String         name;
        private int            age;
        private List<GoodInfo> goods;

        public TestObject(String name, int age){
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

        public List<GoodInfo> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodInfo> goods) {
            this.goods = goods;
        }

        @Override
        public String toString() {
            return "name: " + name + "; age: " + age;
        }
    }

    class GoodInfo {

        private String id;

        public GoodInfo(String id){
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

}
