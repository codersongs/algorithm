package com.codersongs.algorithm.common;



import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * 树节点
 */
public class TreeNode {
    /**
     * 值
     */
    public Integer val;
    /**
     * 左子树
     */
    public TreeNode left;
    /**
     * 右子树
     */
    public TreeNode right;
    public TreeNode() {}
    public TreeNode(Integer val) { this.val = val; }
    public TreeNode(Integer val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }

    public static void main(String[] args) {
        show(arrayToTreeNode(new Integer[]{1,2,2,3,3,3,3,4,4,5,5,null,6,7,7,4,4,5,5,6,6,7,7,4,4,5,5,6,6,7,7}));
        show(arrayToTreeNode(new Integer[]{1}));
    }

    public static TreeNode arrayToTreeNode(Integer[] array){
        if(array.length == 0){
            return null;
        }
        TreeNode root = new TreeNode(array[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while (i < array.length) {
            TreeNode cur = queue.poll();
            Integer left = array[i++];
            if (left != null) {
                cur.left = new TreeNode(left);
                queue.offer(cur.left);
            }
            if (i >= array.length) {
                break;
            }
            Integer right = array[i++];
            if (right != null) {
                cur.right = new TreeNode(right);
                queue.offer(cur.right);
            }
        }
        return root;
    }

    /**
     *
     * @param root
     * @return
     */
    public static void show(TreeNode root) {
        if (root == null){
            System.out.println("EMPTY!");
            return;
        }
        // 得到树的深度
        int treeDepth = getTreeDepth(root);


        // 最后一行的宽度为2的（n - 1）次方乘3，再加1
        // 作为整个二维数组的宽度
        int arrayHeight = treeDepth * 2 - 1;
        int arrayWidth = Math.max(((2 << (treeDepth - 2)) - 1) * 4 + 1, 1);

        // 用一个字符串数组来存储每个位置应显示的元素
        String[][] res = new String[arrayHeight][arrayWidth];
        // 对数组进行初始化，默认为一个空格
        for (int i = 0; i < arrayHeight; i++) {
            for (int j = 0; j < arrayWidth; j++) {
                res[i][j] = " ";
            }
        }
        int[] gapArray = new int[treeDepth];
        gapArray[0] = 3;
        for (int i = 1; i < treeDepth; i++) {
            gapArray[i] = gapArray[i-1] * 2 + 1;
        }
        // 从根节点开始，递归处理整个树
        writeArray(root, 0, arrayWidth / 2, res, treeDepth, gapArray);

        // 此时，已经将所有需要显示的元素储存到了二维数组中，将其拼接并打印即可
        for (String[] line : res) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < line.length; i++) {
                builder.append(line[i]);
                if (line[i].length() > 1 && i <= line.length - 1) {
                    i += line[i].length() > 4 ? 2 : line[i].length() - 1;
                }
            }
            System.out.println(builder);
        }
    }

    /**
     * f(n) = f(n-1) * 2 + 1
     * @param currNode
     * @param rowIndex
     * @param columnIndex
     * @param res
     * @param treeDepth
     */
    private static void writeArray(TreeNode currNode, int rowIndex, int columnIndex, String[][] res, int treeDepth, int[] gapArray) {
        if (currNode == null) {
            return;
        }
        res[rowIndex][columnIndex] = String.valueOf(currNode.val);

        int currLevel = (rowIndex + 1) >> 1;
        if (currLevel == treeDepth) {
            return;
        }
        // 计算当前行到下一行，每个元素之间的间隔（下一行的列索引与当前元素的列索引之间的间隔）
        int index = treeDepth - currLevel - 1 - 1;
        if (index < 0){
            index = 0;
        }
        int gap = gapArray[index];

        if (currNode.left != null) {
            res[rowIndex + 1][columnIndex - (gap + 1 >> 2)] = "/";
            writeArray(currNode.left, rowIndex + 2, columnIndex - (gap >> 1) - 1, res, treeDepth, gapArray);
        }

        if (currNode.right != null) {
            res[rowIndex + 1][columnIndex + (gap + 1 >> 2)] = "\\";
            writeArray(currNode.right, rowIndex + 2, columnIndex + (gap >> 1)  + 1, res, treeDepth, gapArray);
        }
    }


    /**
     * @param currNode
     * @param rowIndex
     * @param columnIndex
     * @param res
     * @param treeDepth
     */
    private static void writeArray1(TreeNode currNode, int rowIndex, int columnIndex, String[][] res, int treeDepth) {
        if (currNode == null) {
            return;
        };
        res[rowIndex][columnIndex] = String.valueOf(currNode.val);

        int currLevel = (rowIndex + 1) >> 1;
        if (currLevel == treeDepth) {
            return;
        }
        // 计算当前行到下一行，每个元素之间的间隔（下一行的列索引与当前元素的列索引之间的间隔）
        int gap = treeDepth - currLevel - 1;

        if (currNode.left != null) {
            res[rowIndex + 1][columnIndex - gap] = "/";
            writeArray1(currNode.left, rowIndex + 2, columnIndex - gap * 2, res, treeDepth);
        }

        if (currNode.right != null) {
            res[rowIndex + 1][columnIndex + gap] = "\\";
            writeArray1(currNode.right, rowIndex + 2, columnIndex + gap * 2, res, treeDepth);
        }
    }

    private static int getTreeDepth(TreeNode root) {
        return root == null ? 0 : (1 + Math.max(getTreeDepth(root.left), getTreeDepth(root.right)));
    }

    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }

    private static void dfs(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        dfs(root.left, res);
        dfs(root.right, res);
    }
}