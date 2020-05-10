package com.erely.leetcode;

import java.util.Stack;

public class reversePrint {
    public int[] reversePrint(ListNode head) {
        Stack s = new Stack();
        while (head != null) {
            s.push(head.val);
            head = head.next;
        }
        int[] result = new int[s.size()];
        int i = 0;
        while (!s.empty()) {
            result[i++] = (int) s.pop();
        }
        return result;
    }
    public int[] f(ListNode head) {
        ListNode node = head;
        int count = 0;
        while (node != null) {
            node = node.next;
            count++;
        }
        int[] result = new int[count];
        node = head;
        for (int i = count-1; i >=0; i++) {
            result[i] = node.val;
            node= node.next;
        }
        return result;
    }
}
