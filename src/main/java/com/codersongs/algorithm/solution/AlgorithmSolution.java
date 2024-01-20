package com.codersongs.algorithm.solution;


import com.codersongs.algorithm.common.TreeNode;

/**
 * 解决方案
 */
public class AlgorithmSolution {
    public static void main(String[] args) {
        AlgorithmSolution solution = new AlgorithmSolution();
        System.out.println(solution.isBalanced(TreeNode.arrayToTreeNode(new Integer[]{1,2,2,3,3,null,null,4,4})));
    }

    public boolean res = true;
    public boolean isBalanced(TreeNode root) {
        level(root);
        return res;
    }

    public int level(TreeNode root) {
        if (root == null || !res) {
            return 0;
        }
        int left = 1 + level(root.left);
        int right =  1 + level(root.right);
        if (Math.abs(left - right) >= 2) {
            res = false;
            return 0;
        }
        return Math.max(left, right);
    }
}
