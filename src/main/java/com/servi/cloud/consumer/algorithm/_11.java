package com.servi.cloud.consumer.algorithm;

/**
 * 实现 Trie (前缀树)
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 * <p>
 * 示例:
 * <p>
 * Trie trie = new Trie();
 * <p>
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 * 说明:
 * <p>
 * 你可以假设所有的输入都是由小写字母 a-z 构成的。
 * 保证所有输入均为非空字符串。
 */

public class _11 {

    public static void main(String[] args) {
        Trie trie = new _11.Trie();

        trie.insert("apple");
        System.out.println(trie.search("apple"));   // 返回 true
        System.out.println(trie.search("app"));     // 返回 false
        System.out.println(trie.startsWith("app")); // 返回 true
        trie.insert("app");
        System.out.println(trie.search("app"));     // 返回 true
    }

    static class Trie {

        TrieNode root = new TrieNode();

        /**
         * Initialize your data structure here.
         */
        public Trie() {
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            char[] keys = word.toCharArray();
            TrieNode node = root;
            for (int i = 0; i < keys.length; i++) {
                char k = keys[i];
                if (node.contains(k)) {
                    node = node.get(k);
                } else {
                    TrieNode addNode = new TrieNode(k);
                    node.put(k, addNode);
                    node = addNode;
                }
            }
            node.setEnd(true);
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            char[] keys = word.toCharArray();
            TrieNode node = root;
            for (int i = 0; i < keys.length; i++) {
                char k = keys[i];
                if (node.contains(k)) {
                    node = node.get(k);
                } else {
                    return false;
                }
            }
            return node.isEnd();
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            char[] keys = prefix.toCharArray();
            TrieNode node = root;
            for (int i = 0; i < keys.length; i++) {
                char k = keys[i];
                if (node.contains(k)) {
                    node = node.get(k);
                } else {
                    return false;
                }
            }
            return true;
        }
    }

    static class TrieNode {
        private TrieNode[] links = null;
        private boolean end = false;
        private boolean root;
        private static final int R = 26;
        private static final char A = 'a';
        private char k;

        public TrieNode() {
            this.links = new TrieNode[R];
            this.k = k;
            this.root = true;
        }

        public TrieNode(char k) {
            this.links = new TrieNode[R];
            this.k = k;
            this.end = false;
        }

        public boolean contains(char k) {
            TrieNode node = links[k - A];
            return node != null;
        }

        public TrieNode get(char k) {
            TrieNode node = links[k - A];
            return node;
        }

        public void put(char k, TrieNode node) {
            links[k - A] = node;
        }

        @Override
        public String toString() {
            return k + " ";
        }

        public boolean isEnd() {
            return end;
        }

        public void setEnd(boolean end) {
            this.end = end;
        }
    }
}
