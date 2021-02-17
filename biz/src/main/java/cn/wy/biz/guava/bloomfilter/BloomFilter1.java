package cn.wy.biz.guava.bloomfilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * Created by leslie on 2020/11/16.
 */
public class BloomFilter1 {

    public static void main(String[] args) {
        test1();
    }

    /**
     * <Pre>
     *     BloomFilter.create(): 会通过你预计的数量以及误报率帮你计算出你应当会使用的数组大小numBits, 以及需要计算几次 Hash 函数numHashFunctions
     * </Pre>
     */
    public static void test1() {
        System.out.println("=====test1=====");
        long begin = System.currentTimeMillis();
        // 数据量, 可接受的误报率.
        BloomFilter<Integer> filter = BloomFilter.create(Funnels.integerFunnel(), 1000_0000, 0.01);
        for (int i = 0; i < 100_0000; i++) {
            filter.put(i);
        }
        System.out.println(filter.mightContain(1));
        System.out.println(filter.mightContain(100));
        System.out.println(filter.mightContain(100000));
        System.out.println(filter.mightContain(999_9999));
        long end = System.currentTimeMillis();
        System.out.println("takes: " + (end - begin) + " ms.");
    }
}
