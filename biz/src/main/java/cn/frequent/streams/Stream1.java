package cn.frequent.streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by leslie on 2019/12/22.
 */
public class Stream1 {

    public static void main(String[] args) {
        test1();
        List<String> list = Arrays.asList("a", "b", "A", "B");
        list.forEach(item -> {
            System.out.println(item);
        });

        Map<String, Integer> map = new HashMap<>();
        map.put("A", 10);
        map.put("B", 20);
        map.put("C", 30);
        map.forEach((k, v) -> {
            System.out.println("key:" + k + ",value:" + v);
        });

        test2();

        /**
         * <pre>
         * 一种类型List, 转为另一种类型.
         * List<SubAccountPO> listSubAccount = subAccountService.listSubAccount(accountId);
         * List<SubAccountsRepDTO> list = listSubAccount.stream().map(item -> {
         *                                            SubAccountsRepDTO dto = new SubAccountsRepDTO();
         *                                            BeanUtil.copy(item, dto);
         *                                            return dto;
         *                                        }).collect(Collectors.toList());
         * </pre>
         */

        // 优雅的将一种类型转为另一种类型.
        /**
         * <pre>
         * 
         * List<OrderDetail> orderDetailList = orderDetailService.listOrderDetails();
         * List<CartDTO> cartDTOList = orderDetailList.stream().map(e -> new CartDTO(e.getProductId(),
         *                                                                                   e.getProductQuantity())).collect(Collectors.toList());
         * </pre>
         */

        // 交集 (list1 + list2)
        /**
         * <pre>
         * 
         * List<T> intersect = list1.stream().filter(list2::contains).collect(Collectors.toList());
         * </pre>
         */

        // 差集
        /**
         * <pre>
         * 
         * // (list1 - list2)
         * List<String> reduce1 = list1.stream().filter(item -> !list2.contains(item)).collect(toList());
         * 
         * // (list2 - list1)
         * List<String> reduce2 = list2.stream().filter(item -> !list1.contains(item)).collect(toList());
         * </pre>
         */

        // 并集
        /**
         * <pre>
         * // 使用并行流
         * List<String> listAll = list1.parallelStream().collect(toList());
         * List<String> listAll2 = list2.parallelStream().collect(toList());
         * listAll.addAll(listAll2);
         * </pre>
         */

        // 去重并集
        /**
         * <pre>
         * 
         * List<String> listAllDistinct = listAll.stream().distinct().collect(toList());
         * </pre>
         */

        // 从list中过滤出一个元素
        /**
         * <Pre>
         * 
         * User match = users.stream().filter((user) -> user.getId() == 1).findAny().get();
         * </Pre>
         */

        // Collectors toList
        /**
         * <Pre>
         * streamArr.collect(Collectors.toList());
         * List<Integer> collectList = Stream.of(1, 2, 3, 4).collect(Collectors.toList());
         * System.out.println("collectList: " + collectList); // 打印结果 collectList: [1, 2, 3, 4]
         * </Pre>
         */

        // Collectors toMap
        /**
         * <pre>
         * // map value 为对象 student
         * Map<Integer, Student> map = list.stream().collect(Collectors.toMap(Student::getId, student -> student));
         * // 遍历打印结果
         * map.forEach((key, value) -> {
         *     System.out.println("key: " + key + "    value: " + value);
         * });
         * // map value 为对象中的属性
         * Map<Integer, String> map = list.stream().collect(Collectors.toMap(Student::getId, Student::getName));
         * map.forEach((key, value) -> {
         *     System.out.println("key: " + key + "    value: " + value);
         * });
         * </pre>
         */

        // List集合转 Map
        /**
         * <pre>
         * Map result = peopleList.stream().collect(Collectors.toMap(p -> p.name, p -> p.age, (k1, k2) -> k1));
         * // 其中Collectors.toMap方法的第三个参数为键值重复处理策略，如果不传入第三个参数，当有相同的键时，会抛出一个IlleageStateException。
         * // 或者
         * Map<Integer, String> result1 = list.stream().collect(Collectors.toMap(Hosting::getId, Hosting::getName));
         * // List<People> -> Map<String,Object>
         * List<People> peopleList = new ArrayList<>();
         * peopleList.add(new People("test1", "111"));
         * peopleList.add(new People("test2", "222"));
         * Map result = peopleList.stream().collect(HashMap::new, (map, p) -> map.put(p.name, p.age), Map::putAll);
         * </pre>
         */

        // List 转 Map<Integer,Apple>
        /**
         * List<Apple> -> Map<Integer,Apple> 需要注意的是： toMap 如果集合对象有重复的key，会报错Duplicate key .... apple1,apple12的id都为1。 可以用
         * (k1,k2)->k1 来设置，如果有重复的key,则保留key1,舍弃key2 Map<Integer, Apple> appleMap =
         * appleList.stream().collect(Collectors.toMap(Apple::getId, a -> a,(k1, k2) -> k1));
         */

        // 假设有一个User实体类，有方法getId(),getName(),getAge()等方法，现在想要将User类型的流收集到一个Map中
        /**
         * <pre>
         *
         * Stream<User> userStream = Stream.of(new User(0, "张三", 18), new User(1, "张四", 19),
         *                                           new User(2, "张五", 19), new User(3, "老张", 50));
         *
         * Map<Integer, User> userMap = userSteam.collect(Collectors.toMap(User::getId, item -> item));
         * </pre>
         */

        // 假设要得到按年龄分组的Map<Integer,List>
        /**
         * <pre>
         * Map<Integer, List<User>> ageMap = userStream.collect(Collectors.toMap(User::getAge,
         *                                                                       Collections::singletonList, (a, b) -> {
         *                                                                           List<User> resultList = new ArrayList<>(a);
         *                                                                           resultList.addAll(b);
         *                                                                           return resultList;
         *                                                                       }));
         *
         * Map<Integer, String> map = persons.stream().collect(Collectors.toMap(p -> p.age, p -> p.name,
         *                                                                      (name1, name2) -> name1 + ";" + name2));
         *
         * System.out.println(map);
         * // {18=Max, 23=Peter;Pamela, 12=David}
         * </pre>
         */

        // Map 转 另一个Map
        /**
         * <pre>
         * // 示例1 Map<String, List<String>> 转 Map<String,User>
         * Map<String, List<String>> map = new HashMap<>();
         * map.put("java", Arrays.asList("1.7", "1.8"));
         * map.entrySet().stream();
         *
         * &#64;Getter
         * &#64;Setter
         * &#64;AllArgsConstructor
         * public static class User {
         *
         *     private List<String> versions;
         * }
         *
         * Map<String, User> collect = map.entrySet().stream().collect(Collectors.toMap(item -> item.getKey(),
         *                                                                              item -> new User(item.getValue())));
         *
         * // 示例2 Map<String,Integer> 转 Map<String,Double>
         * Map<String, Integer> pointsByName = new HashMap<>();
         * Map<String, Integer> maxPointsByName = new HashMap<>();
         *
         * Map<String, Double> gradesByName = pointsByName.entrySet().stream().map(entry -> new AbstractMap.SimpleImmutableEntry<>(entry.getKey(),
         *                                                                                                                         ((double) entry.getValue()
         *                                                                                                                          / maxPointsByName.get(entry.getKey()))
         *                                                                                                                                         * 100d)).collect(Collectors.toMap(Map.Entry::getKey,
         *                                                                                                                                                                           Map.Entry::getValue));
         * </pre>
         */

        // List<String> 转String
        /**
         * <pre>
         * // java8 String.join 方式
         * List<String> webs = Arrays.asList("voidcc.com", "voidmvn.com", "voidtool.com");
         * // webs 必须是List<String>
         * String allwebs = String.join(",", webs);
         * System.out.println(allwebs);
         *
         * // stream
         * List<String> webs = Arrays.asList("voidcc.com", "voidmvn.com", "voidtool.com");
         * String allwebs = webs.stream().collect(Collectors.joining(","));
         * System.out.println(allwebs);
         * </pre>
         */

        // Collectors toSet
        /**
         * <Pre>
         * Set<String> result = Stream.of("aa", "bb", "cc", "aa").collect(HashSet::new, HashSet::add, HashSet::addAll);
         * // Collectors类中已经预定义好了toList，toSet，toMap，toCollection等方便使用的方法，所以以上代码还可以简化如下：
         * Set<String> result2 = Stream.of("aa", "bb", "cc", "aa").collect(Collectors.toSet());
         *
         * Set<Integer> collectSet = Stream.of(1, 2, 3, 4).collect(Collectors.toSet());
         * System.out.println("collectSet: " + collectSet);
         * // 打印结果 collectSet: [1, 2, 3, 4]
         *
         * Stack stack1 = stream.collect(Collectors.toCollection(Stack::new));
         * // collect toString
         * String str = stream.collect(Collectors.joining()).toString();
         * </Pre>
         */

        // 排序
        /**
         * <Pre>
         * // 按照自然顺序进行排序 如果要自定义排序sorted 传入自定义的 Comparator
         * list.stream().sorted().filter((s) -> s.startsWith("a")).forEach(System.out::println);
         *
         * // 对象排序比较 请重写对象的equals()和hashCode()方法
         * list.sorted((a, b) -> b.compareTo(a));
         *
         * Collections.sort(names, (a, b) -> b.compareTo(a));
         * </Pre>
         */

        // 比较
        /**
         * <Pre>
         * Comparator<Person> comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName);
         *
         * Person p1 = new Person("John", "Doe");
         * Person p2 = new Person("Alice", "Wonderland");
         *
         * comparator.compare(p1, p2); // > 0
         * comparator.reversed().compare(p1, p2); // < 0
         * </Pre>
         */

        // Collectors groupingBy 分组
        /**
         * <pre>
         *
         * Map<Integer, List<User>> ageMap2 = userStream.collect(Collectors.groupingBy(User::getAge));
         * </pre>
         */

        // 对集合按照多个属性分组 将多个字段拼接成一个新字段，然后再使用groupBy分组
        /**
         * <pre>
         *
         * Map<String, List<EntryDeliveryDetailywk>> detailmap = details.stream().collect(Collectors.groupingBy(this::fetchGroupKey));
         *
         * private String fetchGroupKey(EntryDeliveryDetailywk detail) {
         *     return detail.getSkuId().toString() + detail.getItemsName() + detail.getWarehouseId().toString()
         *            + detail.getSupplierId().toString();
         * }
         * </pre>
         */

        // groupingBy 分组后操作
        // Collectors中还提供了一些对分组后的元素进行downStream处理的方法：
        // counting方法返回所收集元素的总数；
        // summing方法会对元素求和；
        // maxBy和minBy会接受一个比较器，求最大值，最小值；
        // mapping函数会应用到downstream结果上，并需要和其他函数配合使用；
        /**
         * <pre>
         *
         * Map<Integer, Long> sexCount = userStream.collect(Collectors.groupingBy(User::getSex,
         *                                                                                  Collectors.counting()));
         *
         * Map<Integer, Integer> ageCount = userStream.collect(Collectors.groupingBy(User::getSex,
         *                                                                                  Collectors.summingInt(User::getAge)));
         *
         * Map<Integer, Optional<User>> ageMax = userStream.collect(Collectors.groupingBy(User::getSex,
         *                                                                                  Collectors.maxBy(Comparator.comparing(User::getAge))));
         *
         * Map<Integer, List<String>> nameMap = userStream.collect(Collectors.groupingBy(User::getSex,
         *                                                                                  Collectors.mapping(User::getName,
         *                                                                                                     Collectors.toList())));
         * </pre>
         */

        // groupingBy 根据年龄来分组.
        /**
         * <Pre>
         *
         * Map<Integer, List> peopleByAge = peoples.stream().filter(p -> p.age > 12).collect(Collectors.groupingBy(p -> p.age,
         *                                                                                                         Collectors.toList()));
         * </Pre>
         */

        // groupingBy 根据年龄分组，年龄对应的键值List存储的为Person的姓名
        /**
         * <Pre>
         *
         * Map<Integer, List> peopleByAge = people.stream().collect(Collectors.groupingBy(p -> p.age,
         *                                                                                Collectors.mapping((Person p) -> p.name,
         *                                                                                                   Collectors.toList())));
         * // mapping即为对各组进行投影操作，和Stream的map方法基本一致。
         * </Pre>
         */

        // groupingBy 根据姓名分组，获取每个姓名下人的年龄总和
        /**
         * <pre>
         * Map sumAgeByName = peoples.stream().collect(Collectors.groupingBy(p -> p.name,
         *                                                                   Collectors.reducing(0, (Person p) -> p.age,
         *                                                                                       Integer::sum)));
         * sumAgeByName = peoples.stream().collect(Collectors.groupingBy(p -> p.name,
         *                                                               Collectors.summingInt((Person p) -> p.age)));
         * </pre>
         */

        // groupingBy Boolean分组
        /**
         * <pre>
         * Map<Boolean, List<Integer>> collectGroup = Stream.of(1, 2, 3,
         *                                                      4).collect(Collectors.groupingBy(it -> it > 3));
         * System.out.println("collectGroup : " + collectGroup);
         * // 打印结果
         * // collectGroup : {false=[1, 2, 3], true=[4]}
         * </pre>
         */

        // groupingBy 按年龄分组
        /**
         * <pre>
         * Map<Integer, List<Person>> personsByAge = persons.stream().collect(Collectors.groupingBy(p -> p.age));
         * personsByAge.forEach((age, p) -> System.out.format("age %s: %s\n", age, p));
         * // age 18: [Max]
         * // age 23: [Peter, Pamela]
         * // age 12: [David]
         * </pre>
         */

        // Collectors partitioningBy
        // Collectors中还提供了partitioningBy方法，接受一个Predicate函数，该函数返回boolean值，用于将内容分为两组。假设User实体中包含性别信息getSex()，可以按如下写法将userStream按性别分组
        /**
         * <pre>
         *
         * Map<Boolean, List<User>> sexMap = userStream.collect(Collectors.partitioningBy(item -> item.getSex() > 0));
         * </pre>
         */

        // Collectors中还存在一个类似groupingBy的方法：partitioningBy，它们的区别是partitioningBy为键值为Boolean类型的groupingBy，这种情况下它比groupingBy更有效率。
        // partitioningBy 将数字的Stream分解成奇数集合和偶数集合。
        /**
         * <Pre>
         * Map<Boolean, List<Integer>> collectParti = Stream.of(1, 2, 3,
         *                                                      4).collect(Collectors.partitioningBy(it -> it
         *                                                                                                 % 2 == 0));
         * System.out.println("collectParti : " + collectParti);
         * // 打印结果
         * // collectParti : {false=[1, 3], true=[2, 4]}
         * </Pre>
         */

        // Collectors.joining 收集Stream中的值，该方法可以方便地将Stream得到一个字符串。joining函数接受三个参数，分别表示允（用以分隔元素）、前缀和后缀
        /**
         * <pre>
         * String names = peoples.stream().map(p -> p.name).collect(Collectors.joining(","));
         *
         * String strJoin = Stream.of("1", "2", "3", "4").collect(Collectors.joining(",", "[", "]"));
         * System.out.println("strJoin: " + strJoin);
         * // 打印结果
         * // strJoin: [1,2,3,4]
         *
         * // 字符串连接
         * String phrase = persons.stream().filter(p -> p.age >= 18).map(p -> p.name).collect(Collectors.joining(" and ",
         *                                                                                                       "In Germany ",
         *                                                                                                       " are of legal age."));
         * System.out.println(phrase);
         * // In Germany Max and Peter and Pamela are of legal age.
         * </pre>
         */

        // 组合 Collectors
        /**
         * <pre>
         * Map<Boolean, Long> partiCount = Stream.of(1, 2, 3,
         *                                           4).collect(Collectors.partitioningBy(it -> it.intValue() % 2 == 0,
         *                                                                                Collectors.counting()));
         * System.out.println("partiCount: " + partiCount);
         * // 打印结果
         * // partiCount: {false=2, true=2}
         * </pre>
         */

        // Collectors.summarizing(Int/Long/Double) 它可以一次行获取前面的所有结果，其返回值为(Int/Long/Double)SummaryStatistics
        /**
         * <pre>
         * DoubleSummaryStatistics dss = people.collect(Collectors.summarizingDouble((Person p) -> p.age));
         * double average = dss.getAverage();
         * double max = dss.getMax();
         * double min = dss.getMin();
         * double sum = dss.getSum();
         * double count = dss.getCount();
         *
         * IntSummaryStatistics ageSummary = persons.stream().collect(Collectors.summarizingInt(p -> p.age));
         *
         * System.out.println(ageSummary);
         * // IntSummaryStatistics{count=4, sum=76, min=12, average=19.000000, max=23}
         * </pre>
         */

        // 使用collect可以将Stream转换成值。maxBy和minBy允许用户按照某个特定的顺序生成一个值
        Optional<Integer> collectMaxBy = Stream.of(1, 2, 3,
                                                   4).collect(Collectors.maxBy(Comparator.comparingInt(o -> o)));
        System.out.println("collectMaxBy:" + collectMaxBy.get());
        // 打印结果
        // collectMaxBy:4

        // Collectors averagingInt 计算集合的平均年龄
        /**
         * <pre>
         * Double averageAge = persons.stream().collect(Collectors.averagingInt(p -> p.age));
         *
         * System.out.println(averageAge); // 19.0
         * </pre>
         */

        // 自定义 Collector
        /**
         * <pre>
         * Collector<Person, StringJoiner, String> personNameCollector = Collector.of(() -> new StringJoiner(" | "), // supplier
         *                                                                            (j,
         *                                                                             p) -> j.add(p.name.toUpperCase()), // accumulator
         *                                                                            (j1, j2) -> j1.merge(j2), // combiner
         *                                                                            StringJoiner::toString); // finisher
         *
         * String names = persons.stream().collect(personNameCollector);
         *
         * System.out.println(names); // MAX | PETER | PAMELA | DAVID
         * </pre>
         */
    }

    private static void test1() {
        System.out.println("======test1======");
        Optional<String> a = Optional.of("a");
        System.out.println("isPresent(): " + a.isPresent() + "  get(): " + a.get());
        // 无值情况下不可以调用get(), 否则: java.util.NoSuchElementException
        a = Optional.ofNullable(null);
        Optional<String> b = Optional.empty();
        System.out.println("a.isPresent(): " + a.isPresent());
        System.out.println("b.isPresent(): " + b.isPresent());

        //
        System.out.println(a.orElse("a"));
        System.out.println(b.orElseGet(() -> "c"));
    }

    /**
     * <pre>
     * map集合转为list
     * </pre>
     */
    private static void test2() {
        System.out.println("==========test2==========");
        Person wang = new Person("wang", 12);
        Person zhang = new Person("zhang", 13);
        Person zhao = new Person("zhao", 14);
        Person li = new Person("li", 15);
        Map<String, Integer> map = new HashMap();
        map.put("wang", 12);
        map.put("zhang", 13);
        map.put("zhao", 14);
        map.put("li", 15);

        List<Person> list = map.entrySet().stream().sorted(Comparator.comparing(e -> e.getKey())).map(e -> new Person(e.getKey(),
                                                                                                                      e.getValue())).collect(Collectors.toList());
        System.out.println(list);
        list = map.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue)).map(e -> new Person(e.getKey(),
                                                                                                             e.getValue())).collect(Collectors.toList());
        System.out.println(list);
        list = map.entrySet().stream().sorted(Map.Entry.comparingByKey()).map(e -> new Person(e.getKey(),
                                                                                              e.getValue())).collect(Collectors.toList());
        System.out.println(list);

        List<String> list4 = map.entrySet().stream().sorted(Comparator.comparing(e -> e.getValue())).map(e -> e.getKey()).collect(Collectors.toList());
        System.out.println("4: " + list4);
        List<String> list5 = map.entrySet().stream().sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())).map(e -> e.getKey()).collect(Collectors.toList());
        System.out.println("5: " + list5);
    }

    private static class Person {

        private String name;
        private int    age;

        public Person(String name, int age){
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

        @Override
        public String toString() {
            return "{name: " + name + "; age:" + age + "}";
        }
    }
}
