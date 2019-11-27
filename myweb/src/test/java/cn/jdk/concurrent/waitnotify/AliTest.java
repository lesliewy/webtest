package cn.jdk.concurrent.waitnotify;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by leslie on 2019/11/18.
 */
public class AliTest {

    @Test
    public void test1() {
        Assert.assertFalse(matches("aa", "a"));
        Assert.assertTrue(matches("aa", "aa"));
        Assert.assertTrue(matches("aa", "a*"));
        Assert.assertTrue(matches("aa", ".*"));
    }

    public boolean matches(String s, String p) {
        // pattern 为空时, 需要判断s是否为空;
        if (p == null || p.isEmpty()) {
            return s == null || s.isEmpty();
        }
        // 先看第一个字符是否匹配上, '.' 能匹配
        boolean matchFirstLetter = (!s.isEmpty() && (p.charAt(0) == '.' || p.charAt(0) == s.charAt(0)));

//        if(!matchFirstLetter)
//            return false;
        // 有*，
        if (p.length() >= 2 && p.charAt(1) == '*') {
//            return ;

        } else { // 后面的不是 *， 一个一个匹配
            return matchFirstLetter && matches(s.substring(1), p.substring(1));
        }
        return false;
    }

}
//            return (matches(s, p.substring(2)) || (matchFirstLetter && matches(s.substring(1), p)));
