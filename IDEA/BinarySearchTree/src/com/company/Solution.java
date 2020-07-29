package com.company;

import javax.xml.soap.Node;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    // 层序遍历
    public TreeNode invertTree1(TreeNode root) {
        if (root == null) return root;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();

            // 交换的方法
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return root;
    }

    // 前序遍历
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) return root;

        // 交换的方法
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree2(root.left);
        invertTree2(root.right);

        return root;
    }

    // 中序遍历
    public TreeNode invertTree3(TreeNode root) {
        if (root == null) return root;

        invertTree3(root.left);

        // 交换的方法
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree3(root.left);

        return root;
    }

    // 后序遍历
    public TreeNode invertTree4(TreeNode root) {
        if (root == null) return root;

        invertTree4(root.left);
        invertTree4(root.right);
        // 交换的方法
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        return root;
    }
}
