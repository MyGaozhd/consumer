package com.servi.cloud.consumer.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 验证回文串
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * <p>
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "race a car"
 * 输出: false
 */
public class _06 {

    private String a = "0123456789qwertyuioplkjhgfdsazxcvbnm";

    public static void main(String[] args) {
        System.out.println(new _06().isPalindrome("A man, a plan, a canal: Panama"));
    }

    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        Map<String, String> allow = new HashMap<>();
        char[] ar = a.toCharArray();
        for (char c : ar) {
            allow.put(c + "", c + "");
        }

        char[] sr = s.toCharArray();
        int start = 0;
        int end = sr.length - 1;

        for (; start < end; start++) {
            boolean match = false;
            char a = sr[start];
            if (!allow.containsKey(a + "")) {
                continue;
            }
            for (; end >= start && match == false; end--) {
                char b = sr[end];
                if (!allow.containsKey(b + "")) {
                    continue;
                }
                if (a == b) {
                    match = true;
                } else {
                    return false;
                }
            }
            if (match == false) {
                return false;
            }
        }
        return true;
    }
}
