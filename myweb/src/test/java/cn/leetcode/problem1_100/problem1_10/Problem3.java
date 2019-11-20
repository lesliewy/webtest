package cn.leetcode.problem1_100.problem1_10;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by leslie on 2019/11/15.
 */
public class Problem3 {

    @Test
    public void test1() {
        Assert.assertEquals(3, lengthOfLongestSubstring("abcabcbb"));
        Assert.assertEquals(1, lengthOfLongestSubstring("bbbbb"));
        Assert.assertEquals(3, lengthOfLongestSubstring("pwwkew"));
        Assert.assertEquals(1, lengthOfLongestSubstring(" "));
        Assert.assertEquals(4, lengthOfLongestSubstring("jbpnbwwd"));
    }

    public int lengthOfLongestSubstring(String s) {
        char[] charArray = s.toCharArray();
        int charArrayLength = charArray.length;
        Set<Character> passedChars = new HashSet<>();
        int maxSubSequence = 0;
        for (int i = 0; i < charArrayLength; i++) {
            for (int j = i; j < charArrayLength; j++) {
                char c = charArray[j];
                if (passedChars.contains(c)) {
                    if (passedChars.size() > maxSubSequence) {
                        maxSubSequence = passedChars.size();
                    }
                    passedChars.clear();
                    break;
                } else {
                    passedChars.add(c);
                }
            }
        }
        return Math.max(passedChars.size(), maxSubSequence);
    }
}
