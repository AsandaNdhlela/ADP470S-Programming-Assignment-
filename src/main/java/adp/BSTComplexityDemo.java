package adp;

import adp.trees.BinarySearchTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * BSTComplexityDemo
 * -----------------------------------------------------------
 * Part D demonstration: makes the average-case O(log n) vs.
 * worst-case O(n) distinction concrete by building two BSTs of
 * the same size from the same values, inserted in different
 * orders:
 *
 *   1. RANDOM order  -> tree stays roughly balanced -> height ~ log2(n)
 *   2. SORTED order  -> tree degenerates into a linked-list shape
 *                       -> height = n - 1 (the worst case)
 *
 * It then times a batch of searches on each tree to show the
 * practical effect that height has on lookup speed.
 * -----------------------------------------------------------
 */
public class BSTComplexityDemo {

    public static void main(String[] args) {
        int n = 10_000;

        // Build one list of n unique values, then create two orderings from it.
        List<Integer> sortedValues = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            sortedValues.add(i);
        }

        List<Integer> randomOrder = new ArrayList<>(sortedValues);
        Collections.shuffle(randomOrder, new Random(42));

        // Tree 1: values inserted in random order -> expected to stay balanced.
        BinarySearchTree<Integer> balancedTree = new BinarySearchTree<>();
        for (int v : randomOrder) {
            balancedTree.insert(v);
        }

        // Tree 2: values inserted in already-sorted order -> worst-case shape.
        BinarySearchTree<Integer> degenerateTree = new BinarySearchTree<>();
        for (int v : sortedValues) {
            degenerateTree.insert(v);
        }

        double log2n = Math.log(n) / Math.log(2);

        System.out.println("--- BST Shape: Random Insertion vs Sorted Insertion (n = " + n + ") ---\n");
        System.out.printf("Theoretical log2(n) for reference: %.1f%n%n", log2n);

        System.out.println("Random-order insertion (average case):");
        System.out.println("  Height = " + balancedTree.height() + "  (close to log2(n), confirming O(log n) behaviour)");

        System.out.println("\nSorted-order insertion (worst case):");
        System.out.println("  Height = " + degenerateTree.height() + "  (equal to n-1, confirming the tree degenerated into a linked list)");

        // Time 1,000 searches on each tree for values guaranteed to exist.
        int searchTrials = 1000;
        Random searchRandom = new Random(7);

        long balancedNanos = timeSearches(balancedTree, n, searchTrials, searchRandom);
        long degenerateNanos = timeSearches(degenerateTree, n, searchTrials, searchRandom);

        System.out.println("\n--- Search timing over " + searchTrials + " lookups ---");
        System.out.printf("Balanced (random-insert) tree   : %.3f ms total (%.5f ms/search)%n",
                balancedNanos / 1_000_000.0, balancedNanos / 1_000_000.0 / searchTrials);
        System.out.printf("Degenerate (sorted-insert) tree  : %.3f ms total (%.5f ms/search)%n",
                degenerateNanos / 1_000_000.0, degenerateNanos / 1_000_000.0 / searchTrials);
    }

    private static long timeSearches(BinarySearchTree<Integer> tree, int n, int trials, Random random) {
        long start = System.nanoTime();
        for (int i = 0; i < trials; i++) {
            tree.search(random.nextInt(n));
        }
        return System.nanoTime() - start;
    }
}
