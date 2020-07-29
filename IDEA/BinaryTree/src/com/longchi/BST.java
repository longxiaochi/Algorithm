package com.longchi;

import java.util.Comparator;
import com.mj.printer.BinaryTreeInfo;

@SuppressWarnings("unused")
public class BST<E> extends BinaryTree<E> implements BinaryTreeInfo{
    private Comparator<E> comparator;

    // 由外部传入比较器
    public BST(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    // 无参的构造器
    public BST() {
        this.comparator = null;
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
}
