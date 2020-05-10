package com.erely.leetcode;

import java.util.Stack;

public class addTwoNumbers {


    public static void main(String[] args) {

        ListNode l1 = new ListNode(7);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        l1.next.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(4);
        l2.next.next = new ListNode(6);

        System.out.println(new addTwoNumbers().addTwoNumbers(l1, l2));
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        Stack s1 = new Stack();
        Stack s2 = new Stack();
        Stack s3 = new Stack();
        ListNode node1 = l1, node2 = l2, result = null;
        int num1 = 0, num2 = 0;
        int sum = 0;
        boolean addFlag = false;
        while (l1 != null || l2 != null) {
            if (l1 != null) {
                s1.push(l1.val);
                l1 = l1.next;
                result = node1;
            }
            if (l2 != null) {
                s2.push(l2.val);
                l2 = l2.next;
                result = node2;
            }
        }

        while (!s1.empty() || !s2.empty()) {
            if (!s1.empty()) {
                num1 = (int) s1.pop();
            }
            if (!s2.empty()) {
                num2 = (int) s2.pop();
            }
            sum = num1 + num2;
            if (addFlag == true) {
                sum++;
            }
            if (sum > 9) {
                addFlag = true;
                s3.push(sum % 10);
            } else {
                s3.push(sum);
                addFlag = false;
            }
            num1 = 0;
            num2 = 0;
            sum = 0;
        }
        if (addFlag == true) {
            s3.push(1);
        }
        node1 = result;
        int count = s3.size();
        int i = 0;
        while (!s3.isEmpty()) {
            result.val = (int) s3.pop();
            if (result.next == null && i < count - 1) {
                result.next = new ListNode(0);
            }
            result = result.next;
            i++;
        }
        return node1;
    }
}
