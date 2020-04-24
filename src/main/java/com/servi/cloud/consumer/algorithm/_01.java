package com.servi.cloud.consumer.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 只出现一次的数字
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * <p>
 * 说明：
 * <p>
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,2,1]
 * 输出: 1
 * 示例 2:
 * <p>
 * 输入: [4,1,2,1,2]
 * 输出: 4
 */
public class _01 {

    public static void main(String[] args) {

        System.out.println(singleNumber1(new int[]{2, 2, 1}));
        System.out.println(singleNumber2(new int[]{2, 2, 1, 4, 4, 1, 3}));
    }

    public static int singleNumber1(int[] nums) {

        if (nums == null) return 0;
        if (nums.length == 1) return nums[0];

        Map<Integer, Integer> h = new HashMap<>();

        for (int a : nums) {
            if (h.containsKey(a)) {
                h.remove(a);
            } else {
                h.put(a, a);
            }
        }
        return (int) h.keySet().toArray()[0];
    }

    /**
     * 异或的方法……
     *
     * 0^0 = 0,0^1 = 1,1^1 = 0,1^0=1
     *
     * @param nums
     * @return
     */
    public static int singleNumber2(int[] nums) {

        if (nums == null) return 0;
        if (nums.length == 1) return nums[0];


        int num = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                num = nums[0];
            } else {
                num ^= nums[i];
            }
        }
        return num;
    }
}
