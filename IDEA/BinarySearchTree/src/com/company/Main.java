package com.company;
import com.mj.printer.BinaryTrees;

import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
	// write your code here
//        System.out.print("Hello world");
        test3();
    }

    static void test3() {
        int[] arr = new int[]{7, 4, 2, 1, 3 , 5 , 9, 8, 11 , 10 , 12};
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < arr.length; i++) {
            bst.add(arr[i]);
        }

        BinaryTrees.println(bst);

        bst.remove(2);


        BinaryTrees.println(bst);
//        System.out.println(bst.height2());
//        System.out.println(bst.isComplete());
//
//        bst.printPredecessor();


//        System.out.print("前序遍历: ");
//        bst.preOrder(new BinarySearchTree.Visitor<Integer>() {
//            @Override
//            public boolean visit(Integer element) {
//                System.out.print("_" + element + "_");
//                return element == 1 ? true : false;
//            }
//        });
//
//        System.out.println("");
//
//        System.out.print("中序遍历: ");
//        bst.inOrder(new BinarySearchTree.Visitor<Integer>() {
//            @Override
//            public boolean visit(Integer element) {
//                System.out.print("_" + element + "_");
//                return element == 4 ? true : false;
//            }
//        });
//
//        System.out.println("");
//
//        System.out.print("后序遍历: ");
//        bst.postOrder(new BinarySearchTree.Visitor<Integer>() {
//            @Override
//            public boolean visit(Integer element) {
//                System.out.print("_" + element + "_");
//                return element == 5 ? true : false;
//            }
//        });
//
//        System.out.println("");
//
//        System.out.print("层序遍历: ");
//        bst.levelOrder(new BinarySearchTree.Visitor<Integer>() {
//            @Override
//            public boolean visit(Integer element) {
//                System.out.print("_" + element + "_");
//                return element == 2 ? true : false;
//            }
//        });
    }
}
