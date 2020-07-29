package com.longchi;
import com.mj.printer.BinaryTrees;

public class Main {

    public static void main(String[] args) {
        // write your code here
        test3();
    }

    public static void test3() {
        int[] arr = new int[]{7, 4, 2, 1, 3, 5, 9, 8, 11, 10, 12};
        BST<Integer> bst = new BST<>();
        for (int i = 0; i < arr.length; i++) {
            bst.add(arr[i]);
        }
        BinaryTrees.println(bst);
        bst.remove(2);
        BinaryTrees.println(bst);
    }
}
