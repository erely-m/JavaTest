package com.erely.leetcode;

import java.util.Arrays;

/**
 * 给定一个单词列表，我们将这个列表编码成一个索引字符串 S 与一个索引列表 A。
 * <p>
 * 例如，如果这个列表是 ["time", "me", "bell"]，我们就可以将其表示为 S = "time#bell#" 和 indexes = [0, 2, 5]。
 * <p>
 * 对于每一个索引，我们可以通过从字符串 S 中索引的位置开始读取字符串，直到 "#" 结束，来恢复我们之前的单词列表。
 * <p>
 * 那么成功对给定单词列表进行编码的最小字符串长度是多少呢？
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入: words = ["time", "me", "bell"]
 * 输出: 10
 * 说明: S = "time#bell#" ， indexes = [0, 2, 5] 。
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= words.length <= 2000
 * 1 <= words[i].length <= 7
 * 每个单词都是小写字母 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/short-encoding-of-words
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class minimumLengthEncoding {
    private TrieNode root = new TrieNode();
    public int minimumLengthEncoding(String[] words) {
        int len = 0;
        TrieNode trie = new TrieNode();
        Arrays.sort(words, (s1, s2) -> s2.length() - s1.length());
        for (String word : words) {
            len += f(word);
        }
        return len;
    }

    public int f(String s) {
        TrieNode node = root;
        boolean isNew = false;
        for (int i = s.length()-1; i >= 0; i--) {
            int ch = s.charAt(i) - 'a';
            if (node.childrens[ch] == null) {
                isNew = true;
                node.childrens[ch] = new TrieNode();
            }
            node = node.childrens[ch];
        }
        return isNew ? s.length() + 1 : 0;
    }

    class TrieNode {
        TrieNode[] childrens = new TrieNode[26];
    }

    public static void main(String[] args) {

        new minimumLengthEncoding().minimumLengthEncoding(new String[]{"time", "me", "bell"});
    }
}
