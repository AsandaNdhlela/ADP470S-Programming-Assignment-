package adp.searching_sorting_algo;

public class QuickSort {
    /**
     * Quick Sort (recursive), using the last element as pivot
     * (Lomuto partition scheme).
     *
     * Time complexity:
     *   Best / Average: O(n log n) — balanced partitions halve the
     *     problem size at each of the O(log n) recursion levels,
     *     with O(n) partitioning work per level.
     *   Worst: O(n^2) — occurs when the pivot is always the smallest
     *     or largest element (e.g. an already-sorted array with this
     *     pivot choice), producing maximally unbalanced partitions.
     * Space complexity: O(log n) average (recursion stack depth for
     *   balanced partitions); O(n) worst case for unbalanced ones.
     * Stability: Not stable.
     */
    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    /** Lomuto partition: places the pivot in its final sorted position. */
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    /** Utility: checks an array is sorted ascending (used for correctness verification). */
    public static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) return false;
        }
        return true;
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
