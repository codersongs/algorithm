package com.codersongs.algorithm.solution;


import com.codersongs.algorithm.common.ListNode;
import com.codersongs.algorithm.common.TreeNode;
import com.google.common.collect.Lists;

import java.util.*;

/**
 * 解决方案
 */
public class AlgorithmSolution {
    public static void main(String[] args) {
        AlgorithmSolution solution = new AlgorithmSolution();
        TreeNode treeNode = TreeNode.arrayToTreeNode(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8});
        TreeNode.show(treeNode);
        System.out.println(solution.getIntersectionNode(null, null));
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }
}
