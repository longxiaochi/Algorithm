package com.company;

import com.mj.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

@SuppressWarnings("unused")
public class BinarySearchTree<E> implements BinaryTreeInfo{
    private int size;
    private Node<E> root;
    private Comparator<E> comparator;

    // 由外部传入比较器
    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    // 无参的构造器
    public BinarySearchTree() {
        this.comparator = null;
    }

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

    public void add(E element) {
        nodeNotNULLCheck(element);

        if (root == null) {
            root = new Node<>(element, null);
            size++;
            return ;
        }

        // 添加子节点，遍历找到合适的地方进行插入
        Node<E> node = root;
        Node<E> parent = root;
        int result = 0;

        while (node != null) {
            result = compare(element, node.element);
            parent = node;
            if (result > 0) {
                // 插入到右边
                node = node.right;
            } else if (result < 0){
                // 插入到左边
                node = node.left;
            } else {
                // 覆盖原来的值
                parent.element = element;
                return;
            }
        }

        Node<E> newNode = new Node<>(element, parent);
        if (result > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
    }

    private void remove(Node<E> node) {
        if (node == null) return;

        // 首先处理有2个子树的情况
        if (node.hasTwoChildren()) {
            // 使用后继来替代
            Node<E> s = successor(node);
            node.element = s.element;
            node = s;   // 当前节点指向后继节点，因为后继或者前驱节点的度要么为0 要么为1，那么后面逻辑与处理 度为1 或 度为0 的节点一样
        }

        // 处理度为0的节点
        if (node.isLeaf()) {
            if (node.parent == null) {   // 即只有一个root节点的情况
                root = null;
            } else if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
        } else {
            // 节点度为1 的情况
            Node<E> replacement = node.left != null ? node.left : node.right;
            replacement.parent = node.parent;

            // 重新调整指向
            if (node.parent == null) {
                root = replacement;
            } else if (node == node.parent.left){
                node.parent.left = replacement;
            } else {
                node.parent.right = replacement;
            }
        }
    }

    public void remove(E element) {
        remove(node(element));
    }

    private Node<E> node(E element) {
        if (root == null) return root;

        Node<E> node = root;
        while (node != null) {
            int cmp = compare(element, node.element);
            if (cmp == 0) return node;
            if (cmp > 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        // 到这里都找不到， 那就不找了，哈哈
        return null;
    }






//    public void preOrder() {
//        preOrder(root);
//    }
//
//    private void preOrder(Node<E> node) {
//        if (node == null) return;
//        System.out.print(node.element + " ");
//        preOrder(node.left);
//        preOrder(node.right);
//    }
//
//    public void inOrder() {
//        inOrder(root);
//    }
//
//    public void inOrder(Node<E> node) {
//        if (node == null) return;
//        inOrder(node.left);
//        System.out.print(node.element + " ");
//        inOrder(node.right);
//    }
//
//    public void postOrder() {
//        postOrder(root);
//    }
//    public void postOrder(Node<E> node) {
//        if (node == null) return;
//
//        postOrder(node.left);
//        postOrder(node.right);
//        System.out.print(node.element + " ");
//    }
//
//    public void levelOrder() {
//        levelOrder(root);
//    }
//
//    public void levelOrder(Node<E> node) {
//        if (node == null) return;
//
//        Queue<Node<E>> queue = new LinkedList<>();
//        queue.offer(node);   //根节点入队
//
//        while (!queue.isEmpty()) {
//            Node<E> root = queue.poll();
//            System.out.print(root.element + " ");
//            if (root.left != null) {
//                queue.offer(root.left);
//            }
//            if (root.right != null) {
//                queue.offer(root.right);
//            }
//        }
//    }

    // ==============================
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
        Queue<Node<E>>queue = new LinkedList<>();
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

    public void printPredecessor() {
       Node<E> node = predecessor(root);
       System.out.print(node.element);
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        //一些拼接操作
         toString(root, builder, "");
         return builder.toString();
    }

    private void toString(Node<E> node, StringBuilder sb, String prefix) {
        if (node == null) return;

        sb.append(prefix).append(node.element + "\n");
        toString(node.left, sb, prefix + "L---");
        toString(node.right, sb, prefix + "R---");
    }

    public static abstract class Visitor<E> {
        public boolean stop;
        public abstract boolean visit(E element);
    }

    // 比较方法： 0 代表相等， 1 代表e1 大于 e2, -1: 代表e1小于e2
    private int compare(E e1, E e2) {
        if (this.comparator != null) {
           return this.comparator.compare(e1, e2);
        }
        return ((Comparable<E>)e1).compareTo(e2);
    }

    private void nodeNotNULLCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("节点不能为null");
        }
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>)node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>)node).right;
    }

    @Override
    public Object string(Object node) {
        return ((Node<E>)node).element + " ";
    }

    @SuppressWarnings("unused")
    private static class Node<E> {
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
