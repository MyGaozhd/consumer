package com.servi.cloud.consumer.algorithm;

import java.util.Arrays;

/**
 * 有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 说明:
 * 你可以假设字符串只包含小写字母。
 * <p>
 * 进阶:
 * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 */
public class _13 {

    public static void main(String[] args) {
        System.out.println(one("anagram", "nagaram"));
        System.out.println(two("anagram", "nagaram"));
        System.out.println(three("anagram", "nagaram"));
    }

    private static boolean one(String s, String t) {

        if (s.length() != t.length()) return false;
        char[] cs = s.toCharArray();
        char[] ct = t.toCharArray();
        Arrays.sort(cs);
        Arrays.sort(ct);
        return Arrays.equals(cs, ct);
    }


    private static boolean two(String s, String t) {

        if (s.length() != t.length()) return false;

        int[] c = new int[26];

        for (int i = 0; i < s.length(); i++) {
            c[s.charAt(i) - 'a']++;
            c[t.charAt(i) - 'a']--;
        }

        for (int i = 0; i < c.length; i++) {
            if (c[i] != 0) return false;
        }

        return true;
    }

    /**
     * s 的长度等于t的长度 所以任何时候 ++ -- 之后的最终结果都等于0
     * 如果出现一个小于零的情况，因为已经不能在进行++操作 所以t中此字母数量肯定大于s中此字母数量，必定不相等
     *
     * @param s
     * @param t
     * @return
     */
    private static boolean three(String s, String t) {

        if (s.length() != t.length()) return false;

        int[] c = new int[26];

        for (int i = 0; i < s.length(); i++) {
            c[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < t.length(); i++) {
            c[t.charAt(i) - 'a']--;
            if (c[t.charAt(i) - 'a'] < 0) return false;
        }

        return true;
    }
}
