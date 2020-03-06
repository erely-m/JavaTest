package com.erely.leetcode;

public class numSum {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {

        ListNode l1 = new ListNode(5);
//        l1.next = new ListNode(4);

        ListNode l2 = new ListNode(5);
//        l2.next = new ListNode(6);
//        l2.next.next = new ListNode(4);
        new numSum().addTwoNumbers(l1, l2);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode result = new ListNode(0);
        ListNode head = result;
        boolean flag = false;
        boolean finish = false;
        int x = 0, y = 0, z;
        while (!finish) {
            if (l1 != null) {
                x = l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                y = l2.val;
                l2 = l2.next;
            }
            z = x + y;
            if (flag) { //进位
                z += 1;
            }
            if (z / 10 > 0) {
                flag = true;
            } else {
                flag = false;
            }

            result.next = new ListNode(z % 10);
            result = result.next;
            x = 0;
            y = 0;
            z = 0;
            if (l1 == null && l2 == null) {
                finish = true;
            }
        }
        if(flag){
            result.next = new ListNode(1);
        }

        return head.next;
    }
}
