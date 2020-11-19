package com.imooc.demo.test;

import java.util.LinkedList;
import java.util.Queue;

public class TestMain {



    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode treeNode1 = new TreeNode(4);
        TreeNode treeNode2 = new TreeNode(8);
        TreeNode treeNode3 = new TreeNode(11);
        TreeNode treeNode4 = new TreeNode(13);
        TreeNode treeNode5 = new TreeNode(4);
        TreeNode treeNode6 = new TreeNode(7);
        TreeNode treeNode7 = new TreeNode(2);
        TreeNode treeNode8 = new TreeNode(1);

        root.left = treeNode1;
        root.right = treeNode2;
        treeNode1.left = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        treeNode3.left = treeNode6;
        treeNode3.right = treeNode7;
        treeNode5.right = treeNode8;
        System.out.println(hasPathSum2(root, 22));
    }

    private static boolean hasPathSum2(TreeNode root, int sum){
        if (root == null){
            return false;
        }
        if(root.right==null&&root.left==null){
            return root.val == sum;
        }
        return hasPathSum2(root.left, sum-root.val) || hasPathSum2(root.right, sum-root.val);
    }

    /**
     * 广度优先搜索
     */
    private static boolean hasPathSum1(TreeNode root, int sum) {
        if(root == null){
            return false;
        }
        Queue<TreeNode> que_node = new LinkedList<>();
        Queue<Integer> que_val = new LinkedList<>();
        que_node.offer(root);
        que_val.offer(root.val);
        while (!que_node.isEmpty()){
            TreeNode now = que_node.poll();
            int temp = que_val.poll();
            if(now.left==null&&now.right==null){
                if(temp == sum){
                    return true;
                }
                continue;
            }
            if(now.left!=null){
                que_node.offer(now.left);
                que_val.offer(now.left.val+temp);
            }
            if(now.right!=null){
                que_node.offer(now.right);
                que_val.offer(now.right.val+temp);
            }
        }
        return false;
    }

}
