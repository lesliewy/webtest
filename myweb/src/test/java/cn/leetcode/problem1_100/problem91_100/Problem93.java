package cn.leetcode.problem1_100.problem91_100;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 *     复原IP地址
 *      给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 *
 *      输入: "25525511135"
 输出: ["255.255.11.135", "255.255.111.35"]
 * </pre>
 * 
 * Created by leslie on 2019/12/13.
 */
public class Problem93 {

    @Test
    public void test1() {
        Assert.assertArrayEquals(new String[] { "255.255.11.135", "255.255.111.35" },
                                 restoreIpAddresses3("25525511135").toArray());
    }

    /**
     * <pre>
     *     方法一: 穷举.
     *     按4个长度穷举所有可能的分类.
     * </pre>
     * 
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> ret = new ArrayList<>();
        StringBuilder ip = new StringBuilder();

        for (int a = 1; a < 4; ++a)
            for (int b = 1; b < 4; ++b)
                for (int c = 1; c < 4; ++c)
                    for (int d = 1; d < 4; ++d) {
                        if (a + b + c + d == s.length()) {
                            int n1 = Integer.parseInt(s.substring(0, a));
                            int n2 = Integer.parseInt(s.substring(a, a + b));
                            int n3 = Integer.parseInt(s.substring(a + b, a + b + c));
                            int n4 = Integer.parseInt(s.substring(a + b + c));
                            if (n1 <= 255 && n2 <= 255 && n3 <= 255 && n4 <= 255) {
                                ip.append(n1).append('.').append(n2).append('.').append(n3).append('.').append(n4);
                                if (ip.length() == s.length() + 3) {
                                    ret.add(ip.toString());
                                }
                                // 清空ip
                                ip.delete(0, ip.length());
                            }
                        }
                    }
        return ret;
    }

    /**
     * <pre>
     *     方法二: 回溯.
     * </pre>
     * 
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses2(String s) {
        return restoreAddresses(s, 4);
    }

    public List<String> restoreAddresses(String s, int num) {
        List<String> res = new ArrayList<>();
        if (num == 1) {
            if (s.length() == 0 || s.length() > 3 || Integer.valueOf(s) > 255 || s.charAt(0) == '0' && s.length() > 1)
                return res;
            res.add(s);
            return res;
        }

        if (s.length() > 3 && s.charAt(0) != '0' && Integer.parseInt(s.substring(0, 3)) <= 255) {
            List<String> temp = restoreAddresses(s.substring(3), num - 1);
            String str = new String(s.substring(0, 3));
            for (String cache : temp) {
                String newstr = str + "." + cache;
                res.add(newstr);
            }
        }
        if (s.length() > 2 && s.charAt(0) != '0') {
            List<String> temp = restoreAddresses(s.substring(2), num - 1);
            String str = new String(s.substring(0, 2));
            for (String cache : temp) {
                String newstr = str + "." + cache;
                res.add(newstr);
            }
        }
        if (s.length() > 1) {
            List<String> temp = restoreAddresses(s.substring(1), num - 1);
            String str = new String(s.substring(0, 1));
            for (String cache : temp) {
                String newstr = str + "." + cache;
                res.add(newstr);
            }
        }

        return res;
    }

    /**
     * <pre>
     *     方法三: 回溯.
     * </pre>
     * 
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses3(String s) {
        List<String> res = new ArrayList<>();
        int n = s.length();
        backtrack(0, "", 4, s, res, n);
        return res;
    }

    private void backtrack(int i, String tmp, int flag, String s, List<String> res, int n) {
        if (i == n && flag == 0) {
            res.add(tmp.substring(0, tmp.length() - 1));
            return;
        }
        if (flag < 0) {
            return;
        }
        for (int j = i; j < i + 3; j++) {
            if (j < n) {
                if (i == j && s.charAt(j) == '0') {
                    backtrack(j + 1, tmp + s.charAt(j) + ".", flag - 1, s, res, n);
                    break;
                }
                if (Integer.parseInt(s.substring(i, j + 1)) <= 255)
                    backtrack(j + 1, tmp + s.substring(i, j + 1) + ".", flag - 1, s, res, n);
            }
        }
    }
}
