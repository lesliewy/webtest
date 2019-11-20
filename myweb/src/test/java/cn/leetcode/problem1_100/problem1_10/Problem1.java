package cn.leetcode.problem1_100.problem1_10;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by leslie on 2019/11/14.
 */
public class Problem1 {

    @Test
    public void test1(){
        Assert.assertArrayEquals("failed", new int[]{0,1}, twoSum(new int[]{2,7,3,5}, 9));
        Assert.assertArrayEquals("failed", new int[]{1,2}, twoSum(new int[]{1,0,15,2,7,3,5}, 15));
        Assert.assertArrayEquals("failed", new int[]{2,3}, twoSum(new int[]{2,7,3,5}, 8));
    }

    public int[] twoSum(int[] nums, int target){
        Assert.assertNotNull(nums);
        int length = nums.length;
        for(int i=0; i < length; i++){
            for (int j = i + 1; j < length; j++){
                if(nums[i] + nums[j] == target){
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}
