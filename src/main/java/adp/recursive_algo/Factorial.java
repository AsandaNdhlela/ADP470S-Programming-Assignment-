package adp.recursive_algo;
/**
 * RecursiveAlgorithms
 * -----------------------------------------------------------
 * Part A of ADP470S Programming Assignment.
 *
 * Demonstrates two classic recursive algorithms:
 *   1. Factorial of a non-negative integer.
 *   2. The nth Fibonacci number.
 *
 * Each recursive method is paired with an iterative equivalent
 * so that their behaviour and performance can be compared,
 * as required by the assignment brief.
 * -----------------------------------------------------------
 */
public class Factorial {
    /** * Recursively computes n! (n factorial).
     *
     * Recurrence relation:
     *   factorial(0) = 1                (base case)
     *   factorial(n) = n * factorial(n-1)   for n > 0
     *
     * Time complexity:  O(n)  — one multiplication per recursive call,
     *                            n calls made in total.
     * Space complexity: O(n)  — the call stack grows by one frame per
     *                            call until the base case is reached.
     *
     * @param n a non-negative integer
     * @return n! as a long (long used to delay overflow slightly)
     */
    public static long factorialRecursive(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is undefined for negative numbers.");
        }
        // Base case: 0! = 1
        if (n == 0) {
            return 1;
        }
        // Recursive case: n! = n * (n-1)!
        return n * factorialRecursive(n - 1);
    }

    /**
     * Iterative equivalent of factorial, used for comparison.
     * Time complexity:  O(n)  — a single loop of n iterations.
     * Space complexity: O(1)  — only a fixed number of variables are used,
     *                            regardless of the size of n.
     */
    public static long factorialIterative(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is undefined for negative numbers.");
        }
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

}
