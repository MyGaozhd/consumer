package com.servi.cloud.consumer.algorithm;

/**
 * 乘积最大子数组
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字）。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 * <p>
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 */
public class _18 {

    public static void main(String[] args) {

        Object[] param = new Object[2];
        param[0] = "";
        param[1] = "";


        System.out.println(new _18().maxProduct(new int[]{-2, 3, -4}));

        System.out.println(new _18().maxProduct(new int[]{0, 1}));

        System.out.println(new _18().maxProduct(new int[]{-2}));

        System.out.println(new _18().maxProduct(new int[]{-2, 0, -1}));

        System.out.println(new _18().maxProduct(new int[]{2, 3, -1, 4}));

        System.out.println(new _18().maxProduct(new int[]{2, 3, 0, 0, 66}));
    }

    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE;
        int c = 1;
        for (int i = 0; i < nums.length; i++) {
            int num = c * nums[i];
            if (num > max) {
                max = num;
                if (max == 0) {
                    c = 1;
                } else {
                    c = max;
                }
            } else {
                c = 1;
            }
        }
        return max;
    }


    public int maxProduct1(int[] nums) {
        int max = Integer.MIN_VALUE;
        int imin = 1, imax = 1;
        for (int i = 0; i < nums.length; i++) {
            int c = nums[i];
            if (c > 0) {
                imax = imax * c;
            } else {

            }

        }
        return max;
    }
}
