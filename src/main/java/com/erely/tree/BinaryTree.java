package com.erely.tree;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 二叉树
 */
public class BinaryTree {

    public static class Node { //二叉树节点
        private Node leftNode; //左节点
        private Node rightNode; //右节点
        private Node parent; //父节点
        private Integer value; //节点值

        public Node getLeftNode() {
            return leftNode;
        }

        public void setLeftNode(Node leftNode) {
            this.leftNode = leftNode;
        }

        public Node getRightNode() {
            return rightNode;
        }

        public void setRightNode(Node rightNode) {
            this.rightNode = rightNode;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public Node(Node leftNode, Node rightNode, Node parent, Integer value) {
            this.leftNode = leftNode;
            this.rightNode = rightNode;
            this.value = value;
            this.parent = parent;
        }
    }

    private Node root = null;

    public void createBinaryTree(int value) throws Exception { //创建二叉树
        insertNode(new Node(null, null, null, value));
    }

    public void insertValue(int value) throws Exception {
        createBinaryTree(value);
    }

    //插入节点
    private void insertNode(Node node) throws Exception {
        Node pre = null;
        Node x = this.root;
        while (x != null) { //根节点不为空则寻找需要插入的点
            pre = x;
            if (x.getValue() > node.getValue()) { //大于该值左子树
                x = x.getLeftNode();
            } else if (x.getValue() < node.getValue()) { //小于等于右子树
                x = x.getRightNode();
            } else {
                throw new Exception("node is exits");
            }
        }
        if (pre != null) {
            node.setParent(pre);
            if (pre.getValue() > node.getValue()) {
                pre.setLeftNode(node);
            } else {
                pre.setRightNode(node);
            }
        } else { //为根节点
            this.root = node;
        }
    }

    //删除节点 左节点作为根节点 如果存在子节点去最右节点替换该节点即可 右节点反之

    /**
     * @param value
     * @throws Exception
     */
    private void delete(int value) throws Exception {
        Node node = find(this.root, value);
        if (node != null) {
            Node pre = node.parent; //父节点
            Boolean isLeftNode = pre.getLeftNode().equals(node) ? true : false;//是否是左孩子
            if (node.getLeftNode() != null) { //存在左孩子
                Node child = node.getLeftNode();
                while (child.getRightNode() != null) {
                    child = child.getRightNode();
                } //取到最右节点
                if (isLeftNode) {
                    pre.setLeftNode(child);
                } else {
                    pre.setRightNode(child);
                }
                child.getParent().setRightNode(null);
                child.setLeftNode(node.getLeftNode());
                child.setRightNode(node.getRightNode());

            } else if (node.getRightNode() != null) { //存在右孩子
                Node child = node.getRightNode(); //获取右孩子 需要替换该值
                while (child.getLeftNode() != null) {
                    child = child.getLeftNode();
                }
                if (isLeftNode) {
                    pre.setLeftNode(child);
                } else {
                    pre.setRightNode(child);
                }
                child.getParent().setLeftNode(null);
                child.setLeftNode(node.getLeftNode());
                child.setRightNode(node.getRightNode());

            } else { //该节点是叶子节点直接删除
                if (isLeftNode) {
                    pre.setLeftNode(null);
                } else {
                    pre.setRightNode(null);
                }
            }
        } else {
            throw new Exception("node not find ");
        }
    }

    //查找节点
    private Node find(Node root, int value) {
        if (root.getValue() == value) {
            return root;
        } else if (root.getValue() > value) {
            return find(root.leftNode, value);
        } else if (root.getValue() < value) {
            return find(root.rightNode, value);
        } else {
            return null;
        }
    }


    public static void main(String[] args) throws Exception {
        BinaryTree tree = new BinaryTree();
        tree.insertValue(20);
        tree.insertValue(39);
        tree.insertValue(30);
        tree.insertValue(65);
        tree.insertValue(56);
        tree.insertValue(25);
        tree.insertValue(87);
        tree.insertValue(31);
        tree.insertValue(10);
        tree.insertValue(15);
        tree.insertValue(8);
        tree.insertValue(9);
        tree.insertValue(14);
        tree.insertValue(17);

        tree.delete(39);
        System.out.println(tree.root.getValue());
        System.out.println("++++++++++++");
        firstSort(tree.root);
        System.out.println();
        sort(tree.root);
    }


    public static void firstSort(Node node) {//先序遍历


        if (node.getLeftNode() != null) {
            firstSort(node.getLeftNode());
        }
        if (node.getRightNode() != null) {
            firstSort(node.getRightNode());
        }
        System.out.print(node.value + "  ");
    }

    public static void sort(Node node) {
        Stack<Node> stack = new Stack<Node>();
        Node temp = node;
        while (temp != null || !stack.empty()) {
            while (temp != null) {
                stack.push(temp);
                temp = temp.getLeftNode();
            }

            if (!stack.empty()) {
                Node n = stack.pop();
                System.out.print(n.value + " ");
                temp = n.rightNode;
            }
        }
    }

}
