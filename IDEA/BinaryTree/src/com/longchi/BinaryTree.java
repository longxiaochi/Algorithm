package com.longchi;

import java.util.LinkedList;
import java.util.Queue;

@SuppressWarnings("unused")
public class BinaryTree<E> {
    protected int size;
    protected Node<E> root;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public void preOrder(Visitor<E> visitor) {
        if (visitor == null) return;
        preOrder(root, visitor);
    }

    private void preOrder(Node<E> node, Visitor<E> visitor) {
        if (node == null  || visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
        preOrder(node.left, visitor);
        preOrder(node.right, visitor);
    }

    public void inOrder(Visitor<E> visitor) {
        if (visitor == null) return;
        inOrder(root, visitor);
    }

    private void inOrder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;
        inOrder(node.left, visitor);
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
        inOrder(node.right, visitor);
    }

    public void postOrder(Visitor<E> visitor) {
        if (visitor == null) return;
        postOrder(root, visitor);
    }

    private void postOrder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;
        postOrder(node.left, visitor);
        postOrder(node.right, visitor);
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
    }

    public void levelOrder(Visitor<E> visitor) {
        if (root == null  || visitor == null) return;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (visitor.visit(node.element)) return;

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    // 二叉树的高度(递归)
    public int height() {
        return height(root);
    }

    private int height(Node<E> node) {
        if (node == null) return 0;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    // 二叉树的高度(非递归)
    public int height2() {
        if (root == null) return 0;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        int queueSize = 1;
        int height = 0;
        while (!queue.isEmpty()) {
            // 把头部消掉
            Node<E> node = queue.poll();
            queueSize --;

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }

            if (queueSize == 0) {  // 将一层的节点遍历完后再改变其数量
                height++;
                queueSize = queue.size();
            }
        }

        return height;
    }

    // 判断一颗树是否是完全二叉树
    public boolean isComplete() {
        return isComplete(root);
    }

    private boolean isComplete(Node<E> node) {
        if (node == null)  return false;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(node);

        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node<E> curNode = queue.poll();
            if (leaf && !curNode.isLeaf()) {
                return false;
            }

            if (curNode.left != null) {
                queue.offer(curNode.left);
            } else {
                if (curNode.right != null) {
                    return false;
                }
            }

            if (curNode.right != null) {
                queue.offer(curNode.right);
            } else {
                // 右子树为空，那么接下来遍历的都是叶子节点
                leaf = true;
            }
        }

        return true;
    }

    public Node<E> predecessor(Node<E> node) {
        if (node == null) return node;

        // 场景一
        if (node.left != null) {
            Node<E> leftNode = node.left;
            while(leftNode.right != null) {
                leftNode = leftNode.right;
            }
            // 这时leftNode 就是左子树上最右的节点了
            return leftNode;
        }

        // 场景二， parent不为空，则一直往上找，当处于右子树时， 即满足条件
        while (node.parent != null && node.parent.right != node) {
            node = node.parent;
        }
        return node.parent;
    }

    public Node<E> successor(Node<E> node) {
        if (node == null) return node;

        // 场景一
        if (node.right != null) {
            Node<E> rightNode = node.right;
            while(rightNode.left != null) {
                rightNode = rightNode.left;
            }
            // 这时rightNode 就是右子树上最左的节点了
            return rightNode;
        }

        // 场景二， parent不为空，则一直往上找，当处于右子树时， 即满足条件
        while (node.parent != null && node.parent.left != node) {
            node = node.parent;
        }
        return node.parent;
    }

    public static abstract class Visitor<E> {
        public boolean stop;
        public abstract boolean visit(E element);
    }

    @SuppressWarnings("unused")
    protected static class Node<E> {
        E element;
        Node<E> parent;
        Node<E> left;
        Node<E> right;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }
    }
}
