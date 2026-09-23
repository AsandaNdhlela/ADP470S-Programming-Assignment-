package adp.searching_sorting_algo;

public class SelectionSort {
    /**
 * Selection Sort (iterative).
 *
 * Repeatedly finds the minimum element in the unsorted portion
 * of the array and swaps it into place at the front.
 *
 * Time complexity:
 *   Best / Average / Worst: O(n^2) — the algorithm always scans
 *   the remaining unsorted portion in full, regardless of the
 *   input's initial order, so there is no "best case" shortcut.
 * Space complexity: O(1) — sorts in place, using only a few
 *   index/temp variables.
 * Stability: Not stable (a swap can move an equal element past
 *   another equal element).
 */
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swap(arr, i, minIndex);
            }
        }
    }
    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    /** Utility: checks an array is sorted ascending (used for correctness verification). */
    public static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) return false;
        }
        return true;
    }
}
