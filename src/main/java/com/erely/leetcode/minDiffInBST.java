package com.erely.leetcode;

/**
 *
 * 给定一个二叉搜索树的根结点 root，返回树中任意两节点的差的最小值。
 *
 *
 *
 * 示例：
 *
 * 输入: root = [4,2,6,1,3,null,null]
 * 输出: 1
 * 解释:
 * 注意，root是树结点对象(TreeNode object)，而不是数组。
 *
 * 给定的树 [4,2,6,1,3,null,null] 可表示为下图:
 *
 *           4
 *         /   \
 *       2      6
 *      / \
 *     1   3
 *
 * 最小的差值是 1, 它是节点1和节点2的差值, 也是节点3和节点2的差值。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class minDiffInBST {

    private static int count = Integer.MAX_VALUE;
    private static Integer prev, result;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.right = new TreeNode(6);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        System.out.println(f1(root));

    }

    private static int f1(TreeNode root) {
        prev = null;
        result = Integer.MAX_VALUE;
        dfs(root);
        return result;

    }

    private static void dfs(TreeNode root) {

        if (root == null) return;
        dfs(root.left);
        if (prev != null) {
            result = Math.min(result, root.val - prev);
        }
        prev = root.val;
        dfs(root.right);
    }


    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}


