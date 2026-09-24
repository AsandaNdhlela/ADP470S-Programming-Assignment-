package adp;

import adp.trees.BinarySearchTree;

/**
 * BSTDemo
 * -----------------------------------------------------------
 * Part D demonstration: basic BST usage with a small, easy-to-
 * follow set of values, showing insert, search, delete, and all
 * three traversal orders.
 * -----------------------------------------------------------
 */
public class BSTDemo {
    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        int[] values = {50, 30, 70, 20, 40, 60, 80, 10};
        System.out.println("--- Binary Search Tree Demo ---");
        System.out.print("Inserting: ");
        for (int v : values) {
            System.out.print(v + " ");
            tree.insert(v);
        }
        System.out.println("\nSize: " + tree.size() + ", Height: " + tree.height());

        System.out.println("\nIn-order   (sorted order):        " + tree.inOrder());
        System.out.println("Pre-order  (root, left, right):    " + tree.preOrder());
        System.out.println("Post-order (left, right, root):    " + tree.postOrder());

        System.out.println("\nsearch(60): " + tree.search(60));
        System.out.println("search(99): " + tree.search(99));

        System.out.println("\n--- Deleting 20 (leaf node) ---");
        tree.delete(20);
        System.out.println("In-order after delete: " + tree.inOrder());

        System.out.println("\n--- Deleting 30 (node with one child, 40) ---");
        tree.delete(30);
        System.out.println("In-order after delete: " + tree.inOrder());

        System.out.println("\n--- Deleting 50 (root, two children) ---");
        tree.delete(50);
        System.out.println("In-order after delete: " + tree.inOrder());
        System.out.println("New height: " + tree.height() + ", Size: " + tree.size());
    }
}
