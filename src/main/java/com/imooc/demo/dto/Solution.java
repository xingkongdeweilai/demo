package com.imooc.demo.dto;

public class Solution {
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        return null;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next=listNode2;
        listNode2.next=listNode3;
        listNode3.next=listNode4;
        listNode4.next=listNode5;
        System.out.println(listNode1);
        System.out.println(reverseBetween(listNode1,2, 4));
    }
}
