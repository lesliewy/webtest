package cn.frequent.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections.CollectionUtils;

/**
 * Created by leslie on 2019/12/22.
 */
public class Stream2 {

    public static void main(String[] args) {

        /*
         * String[] strArr = new String[] { "aa", "bb", "cc" }; Stream<String> streamArr = Stream.of(strArr);
         * Stream<String> streamArr2 = Arrays.stream(strArr); List<String> list = new ArrayList<>(); Stream<String>
         * streamList = list.stream(); Map<String, String> map = new HashMap<String, String>() { { put("a", "b"); } };
         * Stream stream = map.entrySet().stream(); Stream<String> streamList2 = list.parallelStream();
         */
        Stream2 s2 = new Stream2();
        // s2.testMap1();
        s2.testFlatMap1();
    }

    // filter 过滤:
    /**
     * <pre>
     * streamArr.filter(str -> str.startsWith("a"));
     * list.stream().filter(student -> student.getSex().equals("G")).forEach(student -> System.out.println(student.toString()));
     * List nums = Arrays.asList(1, 3, null, 8, 7, 8, 13, 10);
     * nums.stream().filter(num -> num != null).distinct().forEach(System.out::println);
     * Stream.of(1, 2, 3, 4, 5).filter(item -> item > 3).forEach(System.out::println);
     * list.stream().filter(TestObject::isLeader).collect(Collectors.toList());
     * 
     * String result = maps.entrySet().stream().filter(map -> "something".equals(map.getValue())).map(map -> map.getValue()).collect(Collectors.joining());
     * 
     * // Map -> Stream -> Filter -> MAP
     * Map<Integer, String> collect = maps.entrySet().stream().filter(map -> map.getKey() == 2).collect(Collectors.toMap(p -> p.getKey(),
     *                                                                                                                   p -> p.getValue()));
     * </pre>
     */

    // map 遍历和转换操作
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
        list.stream().map(TestObject::getName).map(String::length).collect(Collectors.toList()).forEach(System.out::println);
        Stream.of("a", "b", "hello").map(item -> item.toUpperCase()).forEach(System.out::println);

        List<Integer> nums = Arrays.asList(1, 2, 3, 4);
        List<Integer> squareNums = nums.stream().map(n -> n * n).collect(Collectors.toList());
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
    /*
     * <pre> // limit 提取子流 最大长度限制 streamArr.limit(1); Stream.of(1, 2, 3, 4, 5).limit(2).forEach(System.out::println); //
     * 打印结果 1,2 // skip 跳过 streamArr.skip(1); Stream.of(1, 2, 3, 4, 5).skip(2).forEach(System.out::println); // 打印结果
     * 3,4,5 // peek 产生相同的流，支持每个元素调用一个函数 streamArr.peek(str -> System.out.println("item:" + str)); Stream.of(1, 2, 3, 4,
     * 5).peek(integer -> System.out.println("accept:" + integer)).forEach(System.out::println);
     */
    // 打印结果: accept:1
    // 1
    // accept:2
    // 2
    // accept:3
    // 3
    // accept:4
    // 4
    // accept:5
    // 5

    // distinct 去重
    /**
     * <pre>
     * Stream.of("aa", "bb", "aa").distinct();
     * Stream.of(1, 2, 3, 1, 2, 3).distinct().forEach(System.out::println); // 打印结果：1，2，3
     * 
     * // 根据id去重
     * List<Person> unique = appleList.stream().collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparingLong(Apple::getId))),
     *                                                                    ArrayList::new));
     * </pre>
     */

    // sorted.

    /**
     * <pre>
     *
     * List<Human> humans = Lists.newArrayList(new Human("Sarah", 10), new Human("Jack", 12));
     * Collections.sort(humans, Comparator.comparing(Human::getName));
     * Assert.assertThat(humans.get(0), equalTo(new Human("Jack", 12)));
     * humans.sort((h1, h2) -> h1.getName().compareTo(h2.getName()));
     * </pre>
     */

    // 反转排序
    /**
     * <pre>
     * Comparator<Human> comparator = (h1, h2) -> h1.getName().compareTo(h2.getName());
     * humans.sort(comparator.reversed());
     * </pre>
     */

    // 多条件排序.
    /**
     * <pre>
     * humans.sort((lhs, rhs) -> {
     *     if (lhs.getName().equals(rhs.getName())) {
     *         return lhs.getAge() - rhs.getAge();
     *     } else {
     *         return lhs.getName().compareTo(rhs.getName());
     *     }
     * });
     * Assert.assertThat(humans.get(0), equalTo(new Human("Sarah", 10)));
     * </pre>
     */

    // 多条件组合排序.
    /**
     * <pre>
     *
     * humans.sort(Comparator.comparing(Human::getName).thenComparing(Human::getAge));
     * Assert.assertThat(humans.get(0), equalTo(new Human("Sarah", 10)));
     * students.sort((Student s1, Student s2) -> {
     *     return Integer.compare(s1.getHeight(), s2.getHeight());
     * });
     * students.sort((s1, s2) -> Integer.compare(s1.getHeight(), s2.getHeight()));
     * students.sort(Comparator.comparingDouble(Student::getScore));
     * Stream.of("aaa", "bb", "c").sorted(Comparator.comparing(String::length).reversed());
     * 
     * Stream.of(1, 2, 3, 4, 5).sorted().forEach(System.out::println);
     * // 打印结果 5, 4, 3, 2, 1
     * </pre>
     */

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
