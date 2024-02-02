package com.codersongs.algorithm.solution;


import com.codersongs.algorithm.common.ListNode;

import java.util.Arrays;

/**
 * 解决方案
 */
public class AlgorithmSolution {
    public static void main(String[] args) {
        AlgorithmSolution solution = new AlgorithmSolution();
        System.out.println(ListNode.toString(solution.reverseList(ListNode.generateByArray(1))));
    }

    //1->2->3<-4
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
