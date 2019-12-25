package cn.frequent.streams;

/**
 * Created by leslie on 2019/12/22.
 */
public class Stream3 {

    public static void main(String[] args) {
        // forEach
        /**
         * <pre>
         *
         * streamArr.forEach(System.out::println);
         * int ids[] = new int[] { 1, 2, 3, 4 };
         * Arrays.stream(ids).forEach(System.out::println);
         * </pre>
         */

        // forEachOrdered 如果希望顺序执行并行流，请使用该方法
        /**
         * <pre>
         * streamArr.parallel().forEachOrdered(System.out::println);
         * Stream.of(5, 2, 1, 4, 3).forEachOrdered(integer -> {
         *     System.out.println("integer:" + integer);
         * });
         * // 打印结果 integer:5
         * // integer:2
         * // integer:1
         * // integer:4
         * // integer:3
         * </pre>
         */

        // toArray 收集到数组中
        /**
         * <pre>
         * streamArr.filter(str -> str.startsWith("a")).toArray(String[]::new);
         * </pre>
         */

        // min 取最小值
        /**
         * <pre>
         *
         * IntStream.of(1, 2, 3, 4).min();
         * Stream.of(arr).min(String::compareTo);
         * 
         * Optional<Integer> min = Stream.of(1, 2, 3, 4, 5).min((o1, o2) -> o1 - o2);
         * System.out.println("min:" + min.get());
         * // 打印结果：min:5
         * </pre>
         */

        // reduce 聚合操作
        /**
         * <pre>
         * streamArr.reduce((str1, str2) -> str1 + str2);
         * int sum2 = numbers.stream().reduce(0, (a, b) -> a + b);
         * // reduce 求最大值
         * Optional<Integer> max = numbers.stream().reduce(Integer::max);
         * // reduce 求最小值
         * Optional<Integer> min = numbers.stream().reduce(Integer::min);
         * </pre>
         */

        // max 取最大值.
        /**
         * <pre>
         * IntStream.of(1, 2, 3, 4).max();
         * Stream.of(arr).max(String::compareTo);
         * Optional<Integer> max = Stream.of(1, 2, 3, 4, 5).max((o1, o2) -> o2 - o1);
         * System.out.println("max:" + max.get());
         * // 打印结果：max:1
         * </pre>
         */

        // count 计算总量.
        /**
         * <pre>
         * streamArr.count();
         * long count = Stream.of(1, 2, 3, 4, 5).count();
         * System.out.println("count:" + count); // 打印结果：count:5
         * </pre>
         */

        // anyMatch 匹配 判断流中是否含有匹配元素
        /**
         * <pre>
         * boolean hasMatch = streamArr.anyMatch(str -> str.startsWith("a"));
         * boolean anyMatch = Stream.of(1, 2, 3, 4).anyMatch(integer -> integer > 3);
         * System.out.println("anyMatch: " + anyMatch); // 打印结果：anyMatch: true
         * 
         * if (list.stream().anyMatch(u -> u.getName().equals("Ron"))) {
         *     System.out.println("Ron已经到了");
         * }
         * </pre>
         */

        // allMatch 匹配 判断流中是否全部匹配
        /**
         * <pre>
         * boolean hasMatch = streamArr.allMatch(str -> str.startsWith("a"));
         * boolean allMatch = Stream.of(1, 2, 3, 4).allMatch(integer -> integer > 0);
         * System.out.println("allMatch: " + allMatch); // 打印结果：allMatch: true
         * </pre>
         */

        // noneMatch 判断流中是否全部不匹配
        /**
         * <pre>
         * boolean hasMatch = streamArr.noneMatch(str -> str.startsWith("a"));
         * boolean noneMatch = Stream.of(1, 2, 3, 4, 5).noneMatch(integer -> integer > 10);
         * System.out.println("noneMatch:" + noneMatch); // 打印结果 noneMatch:true
         * 
         * boolean noneMatch_ = Stream.of(1, 2, 3, 4, 5).noneMatch(integer -> integer < 3);
         * System.out.println("noneMatch_:" + noneMatch_); // 打印结果 noneMatch_:false
         * </pre>
         */

        // findFirst 找到第一个就返回
        /**
         * <pre>
         * streamArr.filter(str -> str.startsWith("a")).findFirst();
         * // 需要找到第一个isLeader为ture的对象并打印其名字，就可以按照如下的代码操作。
         * list.stream().filter(u -> u.isLeader()).findFirst().ifPresent(u -> System.out.println(u.getName()));
         * </pre>
         */

        // findAny 找到任意一个就返回
        /**
         * <pre>
         * streamArr.filter(str -> str.startsWith("a")).findAny();
         * list.stream().filter(u -> u.getName().equals("Ron")).findAny().ifPresent(u -> System.out.println(u.getName()));
         * </pre>
         */

        // Concat 联接两个Stream:
        /**
         * <pre>
         * Stream.concat(Stream.of(1, 2, 3), Stream.of(4, 5)).forEach(integer -> System.out.print(integer + "")); // 打印结果:
         *                                                                                                        // 1 2
         *                                                                                                        // 3 4
         *                                                                                                        // 5
         * </pre>
         */

        // Stream流复用

        /**
         * <pre>
         *
         * Supplier<Stream<String>> streamSupplier = () -> Stream.of("d2", "a2", "b1", "b3",
         *                                                           "c").filter(s -> s.startsWith("a"));
         * 
         * streamSupplier.get().anyMatch(s -> true); // ok
         * streamSupplier.get().noneMatch(s -> true); // ok
         * </pre>
         */

        // 文件 Stream 把单词挑出来
        /**
         * <pre>
         * 
         * List<String> output = reader.lines().flatMap(line -> Stream.of(line.split(REGEXP))).filter(word -> word.length() > 0).collect(Collectors.toList());
         * </pre>
         */

        // 异步编排CompletableFuture和Stream结合用法： 单条流水线
        /**
         * <pre>
         *     public List<String> findPricesFutureOnePipeline(String product) {
         List<String> result = shops.stream()
         .map(shop -> CompletableFuture.supplyAsync(() -> shop.getName() + " price is "
         + shop.getPrice(product), executor))
         .map(CompletableFuture::join)
         .collect(Collectors.toList());
         return result;
         }
        
         // 单条流水线执行结果．
         [BestPrice price is 227.53480147033423, LetsSaveBig price is 200.89398407500244, MyFavoriteShop price is 161.14747297059597, BuyItAll price is 155.9041805933185]
         one pipeline composed CompletableFuture done in 4004 msecs
         * </pre>
         */

        // 组合两条流水线
        /**
         * <pre>
         *     public List<String> findPricesFuture(String product) {
         List<CompletableFuture<String>> priceFutures =
         shops.stream()
         .map(shop -> CompletableFuture.supplyAsync(() -> shop.getName() + " price is "
         + shop.getPrice(product), executor)) // 指定线程池执行生产者方法，如果不配置此选项，
         .collect(Collectors.toList());  // 得到一个List<CompletableFuture<String>>，列表中的每个CompletableFuture对象在计算完成后都包含商店的String类型的名称。
        
         // 要得到List<String>,需要等待所有的future执行完毕，将其包含的值抽取出来，填充到列表中才能返回。使用join方法获取CompletableFuture包含的值．
         List<String> prices = priceFutures.stream()
         .map(CompletableFuture::join) // 等待所有异步操作结束
         .collect(Collectors.toList());
         return prices;
         }
         // 组合两条流水线执行结果
         [BestPrice price is 171.10524235618578, LetsSaveBig price is 168.59369176671822, MyFavoriteShop price is 174.79155890558252, BuyItAll price is 154.82955565763797]
         //　书上显示是2000ms左右，这里貌似跟直接并行流差不多．
         composed CompletableFuture done in 1006 msecs
         * </pre>
         */

        // 多条流水线组合异步+同步+Stream组合
        /**
         * <pre>
         *     public List<String> findPricesFuture(String product) {
         List<CompletableFuture<String>> priceFutures = findPricesStream(product)
         .collect(Collectors.<CompletableFuture<String>>toList());
        
         return priceFutures.stream()
         // 等待流中的所有Future执行完毕，并提取各自的返回值
         .map(CompletableFuture::join)
         .collect(Collectors.toList());
         }
        
         public Stream<CompletableFuture<String>> findPricesStream(String product) {
        
         return shops.stream()
         // 以异步方式取得每个shop中指定产品的原始价格, 返回值　Stream<CompletableFuture<String>>
         .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
         // 不需要异步, 因为只是price+折扣字符串->Quota对象，返回　Stream<CompletableFuture<Quote>>
         .map(future -> future.thenApply(Quote::parse)) // 取future中的string进行解析.
         // 使用另一个异步任务构造期望的Future，申请折扣
         .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)));
         }
        
         // 执行时间
         [BestPrice price is 204.78, LetsSaveBig price is 190.85, MyFavoriteShop price is 128.92, BuyItAll price is 140.31, ShopEasy price is 166.1]
         composed CompletableFuture done in 2009 msecs
         * </pre>
         */

        // Predicate 合并 甚至可以用and()、or()和xor()逻辑函数来合并Predicate,例如要找到所有以J开始，长度为四个字母的名字，你可以合并两个Predicate并传入
        /**
         * <pre>
         *     Predicate<String> startsWithJ = (n) -> n.startsWith("J");
         *     Predicate<String> fourLetterLong = (n) -> n.length() == 4;
         *     names.stream().filter(startsWithJ.and(fourLetterLong))
         * </pre>
         */

        // 自定义filter
        /**
         * <pre>
         * 
         * public static void filter(List names, Predicate condition) {
         *     names.stream().filter((name) -> (condition.test(name))).forEach((name) -> {
         *         System.out.println(name + " ");
         *     });
         * }
         * </pre>
         */

        // 综合用法
        /**
         * <pre>
         * 
         * private List<String> afterJava8(List<Dish> dishList) {
         *     return dishList.stream().filter(d -> d.getCalories() < 400) // 筛选出卡路里小于400的菜肴
         *                    .sorted(comparing(Dish::getCalories)) // 根据卡路里进行排序
         *                    .map(Dish::getName) // 提取菜肴名称
         *                    .collect(Collectors.toList()); // 转换为List
         * }
         * 
         * // 对数据库查询到的菜肴根据菜肴种类进行分类，返回一个Map<Type, List<Dish>>的结果
         * private static Map<Type, List<Dish>> afterJdk8(List<Dish> dishList) {
         *     return dishList.stream().collect(groupingBy(Dish::getType));
         * }
         * </pre>
         */
    }
}
