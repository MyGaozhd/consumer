package com.servi.cloud.consumer.algorithm;

/**
 * 合并两个有序数组
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 * <p>
 * 说明:
 * <p>
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * 示例:
 * <p>
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * <p>
 * 输出: [1,2,2,3,5,6]
 */
public class _4 {

    private static int[] nums_1 = new int[]{1, 2, 3, 0, 0, 0};
    private static int[] nums_2 = new int[]{2, 5, 6};
//    private static int[] nums_1 = new int[]{ 0};
//    private static int[] nums_2 = new int[]{1};
    private static int start1 = 0;
    private static int start2 = 0;

    public static void main(String[] args) {
        int[] num = startmerge(nums_1, 3, nums_2, 3);

        if (start2 <= nums_2.length - 1 ) {
            int n = nums_2.length - 1;
            int m = nums_1.length - 1;
            while (n >= start2) {
                num[m] = nums_2[n];
                m--;
                n--;
            }
        }
        String a = "[";
        for (int i = 0; i < num.length; i++) {
            a = a + num[i];
            if (i !=num.length-1){
                a=a+",";
            }
        }
        a=a+"]";
        System.out.println(a);
    }

    public static int[] startmerge(int[] nums1, int m, int[] nums2, int n) {

        for (int i = start1; i < nums1.length && start2 <= nums2.length - 1; i++) {
            if (nums1[start1] >= nums2[start2]) {
                nums1 = move(nums1, start1);
                nums1[start1] = nums2[start2];
                start1++;
                start2++;
                nums1 = startmerge(nums1, m, nums2, n);
                break;
            }
            start1++;
        }


        return nums1;
    }

    public static int[] move(int[] nums1, int start) {
        for (int i = nums1.length - 1; i > start; i--) {
            nums1[i] = nums1[i - 1];
        }
        return nums1;
    }
}
