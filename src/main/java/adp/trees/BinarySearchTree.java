package adp.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * BinarySearchTree
 * -----------------------------------------------------------
 * Part D of ADP470S Programming Assignment.
 *
 * A generic Binary Search Tree (BST) implemented from scratch.
 * For every node, all values in its left subtree are smaller and
 * all values in its right subtree are larger (no duplicates are
 * stored; a duplicate insert is ignored).
 *
 * Complexity summary (n = number of nodes, h = height of the tree):
 *   insert / search / delete : O(h)
 *     - Average case: O(log n)  — this holds when the tree stays
 *       roughly balanced, which is what happens on average when
 *       n values are inserted in RANDOM order. A balanced tree's
 *       height is proportional to log2(n), because each comparison
 *       eliminates roughly half of the remaining nodes, exactly
 *       like binary search on a sorted array.
 *     - Worst case: O(n)        — happens when the tree degenerates
 *       into a shape resembling a linked list, most notably when
 *       values are inserted in already-sorted (or reverse-sorted)
 *       order: every new value becomes the new rightmost (or
 *       leftmost) node, so the tree's height grows to n and every
 *       operation must walk the full chain.
 *   traversals (in-order/pre-order/post-order) : O(n) always,
 *     since every node must be visited exactly once regardless of
 *     the tree's shape.
 * -----------------------------------------------------------
 */
public class BinarySearchTree<T extends Comparable<T>> {

    /** A single node: a value plus references to its left and right children. */
    private static class Node<T> {
        T data;
        Node<T> left;
        Node<T> right;

        Node(T data) {
            this.data = data;
        }
    }

    private Node<T> root;
    private int size;

    /** Inserts a value into the tree. Duplicate values are ignored. O(h). */
    public void insert(T value) {
        root = insertRecursive(root, value);
    }

    private Node<T> insertRecursive(Node<T> node, T value) {
        if (node == null) {
            size++;
            return new Node<>(value);
        }
        int cmp = value.compareTo(node.data);
        if (cmp < 0) {
            node.left = insertRecursive(node.left, value);
        } else if (cmp > 0) {
            node.right = insertRecursive(node.right, value);
        }
        // cmp == 0: duplicate, do nothing.
        return node;
    }

    /** Searches for a value. @return true if present. O(h). */
    public boolean search(T value) {
        return searchRecursive(root, value);
    }

    private boolean searchRecursive(Node<T> node, T value) {
        if (node == null) {
            return false;
        }
        int cmp = value.compareTo(node.data);
        if (cmp == 0) {
            return true;
        }
        return cmp < 0 ? searchRecursive(node.left, value) : searchRecursive(node.right, value);
    }

    /**
     * Deletes a value from the tree, if present. O(h).
     * @return true if the value was found and removed, false otherwise.
     */
    public boolean delete(T value) {
        int sizeBefore = size;
        root = deleteRecursive(root, value);
        return size < sizeBefore;
    }

    private Node<T> deleteRecursive(Node<T> node, T value) {
        if (node == null) {
            return null; // value not found
        }

        int cmp = value.compareTo(node.data);
        if (cmp < 0) {
            node.left = deleteRecursive(node.left, value);
        } else if (cmp > 0) {
            node.right = deleteRecursive(node.right, value);
        } else {
            // Found the node to delete - three cases:

            // Case 1: leaf node (no children) - just remove it.
            // Case 2: one child - replace node with its only child.
            if (node.left == null) {
                size--;
                return node.right;
            } else if (node.right == null) {
                size--;
                return node.left;
            }

            // Case 3: two children - replace this node's value with its
            // in-order successor (the smallest value in the right subtree),
            // then delete that successor node from the right subtree.
            Node<T> successor = findMin(node.right);
            node.data = successor.data;
            node.right = deleteRecursive(node.right, successor.data);
            // Note: size is decremented inside the recursive call above,
            // when the successor (which has at most one child) is removed.
        }
        return node;
    }

    private Node<T> findMin(Node<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /** In-order traversal (left, node, right): visits values in ascending sorted order. */
    public List<T> inOrder() {
        List<T> result = new ArrayList<>();
        inOrderRecursive(root, result);
        return result;
    }

    private void inOrderRecursive(Node<T> node, List<T> result) {
        if (node == null) return;
        inOrderRecursive(node.left, result);
        result.add(node.data);
        inOrderRecursive(node.right, result);
    }

    /** Pre-order traversal (node, left, right): useful for copying/serializing a tree's shape. */
    public List<T> preOrder() {
        List<T> result = new ArrayList<>();
        preOrderRecursive(root, result);
        return result;
    }

    private void preOrderRecursive(Node<T> node, List<T> result) {
        if (node == null) return;
        result.add(node.data);
        preOrderRecursive(node.left, result);
        preOrderRecursive(node.right, result);
    }

    /** Post-order traversal (left, right, node): useful for safely deleting/freeing a tree bottom-up. */
    public List<T> postOrder() {
        List<T> result = new ArrayList<>();
        postOrderRecursive(root, result);
        return result;
    }

    private void postOrderRecursive(Node<T> node, List<T> result) {
        if (node == null) return;
        postOrderRecursive(node.left, result);
        postOrderRecursive(node.right, result);
        result.add(node.data);
    }

    /** Height of the tree (number of edges on the longest root-to-leaf path; -1 for an empty tree). */
    public int height() {
        return heightRecursive(root);
    }

    private int heightRecursive(Node<T> node) {
        if (node == null) return -1;
        return 1 + Math.max(heightRecursive(node.left), heightRecursive(node.right));
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
