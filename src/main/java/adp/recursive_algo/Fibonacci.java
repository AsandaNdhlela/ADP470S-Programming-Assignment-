package adp.recursive_algo;

public class Fibonacci {
    /**
     * Recursively computes the nth Fibonacci number using the
     * naive (non-memoised) definition, as requested in Part A.
     *
     * Recurrence relation:
     *   fib(0) = 0                          (base case)
     *   fib(1) = 1                          (base case)
     *   fib(n) = fib(n-1) + fib(n-2)        for n > 1
     *
     * Time complexity:  O(2^n)  — each call spawns two further calls,
     *                              forming a binary recursion tree whose
     *                              size grows exponentially with n.
     *                              (Many subproblems are recomputed
     *                              repeatedly, e.g. fib(n-2) is solved
     *                              once directly and once inside the
     *                              fib(n-1) branch.)
     * Space complexity: O(n)    — the *depth* of the recursion tree
     *                              (i.e. the maximum call-stack size)
     *                              is only n, even though the total
     *                              number of calls is exponential.
     *
     * @param n index of the Fibonacci number to compute (n >= 0)
     * @return the nth Fibonacci number
     */
    public static long fibonacciRecursive(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Fibonacci is undefined for negative numbers.");
        }
        // Base cases
        if (n == 0) return 0;
        if (n == 1) return 1;
        // Recursive case
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    /**
     * Iterative equivalent of Fibonacci, used for comparison.
     *
     * Time complexity:  O(n)  — a single loop, one addition per iteration.
     * Space complexity: O(1)  — only two running values are stored,
     *                            regardless of the size of n.
     */
    public static long fibonacciIterative(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Fibonacci is undefined for negative numbers.");
        }
        if (n == 0) return 0;
        long previous = 0;   // fib(0)
        long current = 1;    // fib(1)
        for (int i = 2; i <= n; i++) {
            long next = previous + current;
            previous = current;
            current = next;
        }
        return current;
    }

}
