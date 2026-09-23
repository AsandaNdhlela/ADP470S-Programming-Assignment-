package adp.searching_sorting_algo;

public class BinarySearch {
    /**
     * Iterative binary search.
     * Time complexity:  O(log n) — the search space is halved on every iteration.
     * Space complexity: O(1)    — only a few index variables are used.
     */
    public static int searchIterative(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2; // avoids potential overflow of (low+high)/2
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1; // not found
    }

    /**
     * Recursive binary search (public entry point).
     * Time complexity:  O(log n) — same halving argument as the iterative version.
     * Space complexity: O(log n) — each recursive call adds a stack frame; the recursion depth is log n, unlike
     * the O(1) space used by the iterative version.
     */
    public static int searchRecursive(int[] arr, int target) {
        return searchRecursive(arr, target, 0, arr.length - 1);
    }

    private static int searchRecursive(int[] arr, int target, int low, int high) {
        if (low > high) {
            return -1; // base case: search space exhausted, not found
        }
        int mid = low + (high - low) / 2;
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            return searchRecursive(arr, target, mid + 1, high);
        } else {
            return searchRecursive(arr, target, low, mid - 1);
        }
    }
}
