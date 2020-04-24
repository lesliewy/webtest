package cn;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by leslie on 2018/5/16.
 */
public class Test {

    @org.junit.Test
    public void test1() {
        Float a = 1234567000.12f;
        DecimalFormat b = new DecimalFormat("0.00");
        System.out.println(String.valueOf(a));
        System.out.println(b.format(a));

        BigDecimal c = new BigDecimal("123456789000.00");
        System.out.println(c);
    }

    /*
    @org.junit.Test
    public void test2() {
        System.out.println(org.apache.commons.lang3.StringUtils.containsAny("aaaaaa", "aaaaaaa", "b"));
        System.out.println(org.apache.commons.lang3.StringUtils.containsAny("200", "500", "600"));
    }
    */

    @org.junit.Test
    public void test3() {
        String[] cards = { "6217001210024455220", "6217001210024455221", "6228483868586908177", "6228483868586908178",
                           "6214855716759598", "6228480322776602714" };
        for (String card : cards) {
            System.out.println(card + ": " + matchLuhn(card));
        }
    }

    /**
     * 匹配Luhn算法：可用于检测银行卡卡号
     * 
     * @param cardNo
     * @return
     */
    public static boolean matchLuhn(String cardNo) {
        int[] cardNoArr = new int[cardNo.length()];
        for (int i = 0; i < cardNo.length(); i++) {
            cardNoArr[i] = Integer.valueOf(String.valueOf(cardNo.charAt(i)));
        }
        for (int i = cardNoArr.length - 2; i >= 0; i -= 2) {
            cardNoArr[i] <<= 1;
            cardNoArr[i] = cardNoArr[i] / 10 + cardNoArr[i] % 10;
        }
        int sum = 0;
        for (int i = 0; i < cardNoArr.length; i++) {
            sum += cardNoArr[i];
        }
        return sum % 10 == 0;
    }

    @org.junit.Test
    public void test4(){
        Set<String> a = new HashSet<>();
        a.add("/a/b/c_@");
        a.add("/a/b/c_");
        a.add("/a/b/c");
        a.add("/a/b/c/d");
        a.add("/a/b/caaa_@");
        a.add("/a/b/caaa_@1");
        a.add("/a/b/caaa_@81");
        a.add("/");
        a.add("/a");
        System.out.println(a.stream().anyMatch(s -> s.split("_@")[0].equals("/a/b/c")));
        System.out.println(a.stream().anyMatch(s -> s.split("_@")[0].equals("/a/b")));
        System.out.println(a.stream().anyMatch(s -> s.split("_@")[0].equals("/a/b/caaa")));
        System.out.println(a.stream().anyMatch(s -> s.split("_@")[0].equals("/a/b/caaa_")));
//        System.out.println(a.contains("/a/b/c_@"));
//        System.out.println(a.contains("/a/b/c_@1"));
//        System.out.println(a.contains("/a/b/c"));
//        System.out.println(a.contains("/a/b"));
//        System.out.println(a.contains("/a"));

        String url1 = "/a/b/caaa_@";
        System.out.println(url1.split("_@")[0]);
        url1 = "/a/b/caaa_@1";
        System.out.println(url1.split("_@")[0]);
        url1 = "/a/b/caaa_@123";
        System.out.println(url1.split("_@")[0]);
        url1 = "/a/b/c";
        System.out.println(url1.split("_@")[0]);
        url1 = "/";
        System.out.println(url1.split("_@")[0]);
        url1 = "/a/b/caaa_";
        System.out.println(url1.split("_@")[0]);

    }

}
