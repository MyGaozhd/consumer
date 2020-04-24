package com.servi.cloud.consumer.algorithm;

import java.util.*;

/**
 * 字符串中的第一个唯一字符
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 * <p>
 * 案例:
 * <p>
 * s = "leetcode"
 * 返回 0.
 * <p>
 * s = "loveleetcode",
 * 返回 2.
 * <p>
 * <p>
 * 注意事项：您可以假定该字符串只包含小写字母。
 */
public class _14 {

    public static void main(String[] args) {

        System.out.println(new _14().firstUniqChar_1(""));

        System.out.println(new _14().firstUniqChar_1("aadadaad"));

        System.out.println(new _14().firstUniqChar_1("leetcode"));

        System.out.println(new _14().firstUniqChar_1("loveleetcode"));

        System.out.println("====================================================");

        System.out.println(new _14().firstUniqChar_2(""));

        System.out.println(new _14().firstUniqChar_2("aadadaad"));

        System.out.println(new _14().firstUniqChar_2("leetcode"));

        System.out.println(new _14().firstUniqChar_2("loveleetcode"));

        System.out.println("====================================================");

        System.out.println(new _14().firstUniqChar_3(""));

        System.out.println(new _14().firstUniqChar_3("aadadaad"));

        System.out.println(new _14().firstUniqChar_3("leetcode"));

        System.out.println(new _14().firstUniqChar_3("loveleetcode"));
    }

    public int firstUniqChar_1(String s) {
        Map<String, Integer> charAtMap = new HashMap<>();
        Set<Integer> set = new TreeSet<>();

        char[] c = s.toCharArray();

        for (int i = 0; i < c.length; i++) {
            String at = String.valueOf(c[i]);
            if (charAtMap.containsKey(at)) {
                set.remove(charAtMap.get(at));
            } else {
                charAtMap.put(at, i);
                set.add(i);
            }
        }

        int count = -1;
        for (Integer i : set) {
            if (count == -1) {
                count = i;
            }
            if (count != -1 && i < count) {
                count = i;
            }
        }

        return count;
    }

    public int firstUniqChar_2(String s) {

        Map<Character, Integer> charAtMap = new HashMap<>();
        int n = s.length();
        char[] c = s.toCharArray();
        for (int i = 0; i < n; i++) {
            charAtMap.put(c[i], charAtMap.getOrDefault(c[i], 0) + 1);
        }

        for (int i = 0; i < n; i++) {
            if (charAtMap.get(c[i]) == 1) {
                return i;
            }
        }

        return -1;
    }

    public int firstUniqChar_3(String s) {
        //fast
        int n = s.length();

        for (int i = 'a'; i <= 'z'; i++) {
            int start = s.indexOf(i);
            int end = s.lastIndexOf(i);

            if (start == end && start != -1) {
                n = Math.min(start, n);
            }
        }

        if (n == s.length()) {
            return -1;
        } else {
            return n;
        }
    }
}
