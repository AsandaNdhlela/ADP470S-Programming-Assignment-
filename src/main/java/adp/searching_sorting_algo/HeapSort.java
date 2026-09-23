package adp.searching_sorting_algo;

public class HeapSort {
    /**
     * Heap Sort.
     *
     * Builds a max-heap from the array, then repeatedly swaps the
     * root (largest remaining element) to the end of the unsorted
     * region and re-heapifies.
     *
     * Time complexity:
     *   Best / Average / Worst: O(n log n) in every case — building
     *   the heap is O(n), and each of the n extractions costs
     *   O(log n) to restore the heap property, giving a guaranteed
     *   bound that does not degrade on adversarial input.
     * Space complexity: O(1) additional — sorts in place (the
     *   recursion used by heapify here is O(log n) stack space).
     * Stability: Not stable.
     */
    public static void heapSort(int[] arr) {
        int n = arr.length;

        // Build max heap: start from the last non-leaf node.
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Extract elements from the heap one by one.
        for (int end = n - 1; end > 0; end--) {
            swap(arr, 0, end);       // move current max to the end
            heapify(arr, end, 0);    // restore heap property on the reduced heap
        }
    }

    /** Sift-down: ensures the subtree rooted at index i satisfies the max-heap property. */
    private static void heapify(int[] arr, int heapSize, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < heapSize && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < heapSize && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, heapSize, largest);
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
