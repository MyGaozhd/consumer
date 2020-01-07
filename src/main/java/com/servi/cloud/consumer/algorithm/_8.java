package com.servi.cloud.consumer.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分割回文串
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 * <p>
 * 返回 s 所有可能的分割方案。
 * <p>
 * 示例:
 * <p>
 * 输入: "aab"
 * 输出:
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 */
public class _8 {

    public static void main(String[] args) {

        List<List<String>> ll = new _8().partition("ccd");
        System.out.println(ll.toString());

        List<List<String>> lll = new _8().partition("abb");
        System.out.println(lll.toString());
//
        List<List<String>> llll = new _8().partition("aabb");
        System.out.println(llll.toString());
    }

    public List<List<String>> partition(String s) {

        List<List<String>> ll = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ll;
        }
        partition1(s, ll, null);

        return ll;
    }

    public void partition1(String s, List<List<String>> ll, List<String> l) {
        for (int i = 1; i <= s.length(); i++) {
            String sub = s.substring(0, i);
            if (isPalindrome(sub)) {
                List<String> nl = new ArrayList<>();
                if (l != null) {
                    nl.addAll(l);
                }
                nl.add(sub);
                if (i < s.length()) {
                    partition1(s.substring(i), ll, nl);
                } else {
                    ll.add(nl);
                }
            }
        }
    }


    private String a = "0123456789qwertyuioplkjhgfdsazxcvbnm";

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
