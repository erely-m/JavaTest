package com.erely.leetcode;

public class longestCommonPrefix {
    public static void main(String[] args) {
        System.out.println(new longestCommonPrefix().t5(new String[]{"aa", "a", "aaa"}));
    }

    public String t1(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        return t2(strs, 0, strs.length - 1);
    }

    public String t2(String[] ss, int left, int right) {
        if (left == right) {
            return ss[left];
        } else {
            int mid = (right + left) / 2;
            String lcpLeft = t2(ss, left, mid);
            String lcpRight = t2(ss, mid + 1, right);
            return t3(lcpLeft, lcpRight);
        }
    }

    public String t3(String x, String y) {
        int min = Math.min(x.length(), y.length());
        for (int i = 0; i < min; i++) {
            if (x.charAt(i) != y.charAt(i))
                return x.substring(0, i);
        }
        return x.substring(0, min);
    }

    /**
     * 字典树实现
     */
    private class TrieNode {
        private TrieNode child; //只有一个孩子如果不等于这个孩子直接不符合
        private char value;
        private boolean end = false;

        public void setValue(char value) {
            this.value = value;
        }

        public char getValue() {
            return value;
        }

        public void setEnd(boolean end) {
            this.end = end;
        }

        public boolean isEnd() {
            return end;
        }
    }

    private class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void dealTrie(String s) {
            if (root == null) return;
            TrieNode node = root;
            TrieNode parent = null;
            for (int i = 0; i < s.length(); i++) {
                if (parent != null && parent.isEnd()) return;
                if (node.getValue() == 0) {
                    node.setValue(s.charAt(i));
                    parent = node;
                    node = node.child;
                    if (node == null && !parent.isEnd()) {
                        node = new TrieNode();
                        parent.child = node;
                    }
                } else if (node.getValue() == s.charAt(i)) {
                    parent = node;
                    node = node.child;
                    if (node == null && !parent.isEnd()) {
                        node = new TrieNode();
                        parent.child = node;
                    }
                } else {
                    if (parent == null) {
                        root = null;
                    } else {
                        parent.child = null;
                        parent.setEnd(true);
                    }
                    return;
                }
            }
            if (parent == null) {
                root = null;
            } else {
                parent.child = null;
                parent.setEnd(true);
            }
            return;
        }

        public String getResult() {
            TrieNode node = root;
            StringBuilder sb = new StringBuilder();
            while (node.child != null) {
                sb.append(node.getValue());
                node = node.child;
            }
            sb.append(node.getValue());
            return sb.toString().trim();
        }
    }

    public String t4(String[] ss) {
        Trie trie = new Trie();
        for (int i = 0; i < ss.length; i++) {
            if ("".equals(ss[i])) return "";
            trie.dealTrie(ss[i]);
            if (trie.root == null) return "";
        }
        return trie.getResult();
    }

    public String t5(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        return t4(strs);
    }

}
