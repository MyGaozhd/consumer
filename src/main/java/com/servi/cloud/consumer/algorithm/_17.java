package com.servi.cloud.consumer.algorithm;

/**
 * 反转字符串
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 * <p>
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * <p>
 * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：["h","e","l","l","o"]
 * 输出：["o","l","l","e","h"]
 * 示例 2：
 * <p>
 * 输入：["H","a","n","n","a","h"]
 * 输出：["h","a","n","n","a","H"]
 */
public class _17 {
    public static void main(String[] args) {
        new _17().reverseString("hhih".toCharArray());
        new _17().reverseString("hello".toCharArray());
        new _17().reverseString("hannah".toCharArray());

        System.out.println("=================================");

        new _17().reverseString2("hhih".toCharArray());
        new _17().reverseString2("hello".toCharArray());
        new _17().reverseString2("hannah".toCharArray());
    }

    public void reverseString(char[] s) {

        int length = s.length;
        char c;
        for (int i = 0; i < length / 2; i++) {
            c = s[i];
            s[i] = s[length - i - 1];
            s[length - i - 1] = c;
        }

        System.out.println(s);
    }

    public void reverseString2(char[] s) {

        int i = 0;
        int j = s.length - 1;
        char c;
        while (i < j) {
            c = s[i];
            s[i] = s[j];
            s[j] = c;
            i++;
            j--;
        }

        System.out.println(s);
    }
}
