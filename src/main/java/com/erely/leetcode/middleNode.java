package com.erely.leetcode;

public class middleNode {


    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);
        node.next.next.next.next.next = new ListNode(6);
        System.out.println(f1(node).val);

    }

    public static ListNode f1(ListNode head) {
        int count = 0;
        ListNode node = head;
        do {
            count++;
            if (count == 2) {
                node = node.next;
                count = 0;
            }
            head = head.next;
        } while (head != null);
        return node;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}