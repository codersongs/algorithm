package com.codersongs.algorithm.solution;


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
        System.out.println(solution.preorderTraversal(treeNode));
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            res.add(cur.val);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
        return res;
    }

    private void dfs(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        dfs(root.left, res);
        dfs(root.right, res);
    }
}
