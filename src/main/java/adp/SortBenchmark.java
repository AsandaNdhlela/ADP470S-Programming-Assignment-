package adp;

import adp.searching_sorting_algo.HeapSort;
import adp.searching_sorting_algo.QuickSort;
import adp.searching_sorting_algo.SelectionSort;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

/**
 * SortBenchmark
 * -----------------------------------------------------------
 * Part B of ADP470S Programming Assignment.
 *
 * Generates random integer datasets and times Selection Sort,
 * Quick Sort, and Heap Sort on identical copies of each dataset,
 * so the comparison is fair (same numbers, same order, for all
 * three algorithms at a given size).
 *
 * The assignment specifically asks for a dataset of 1,000 random
 * integers; this benchmark also repeats the comparison at several
 * other sizes (100 up to 20,000) so the results form a clear growth
 * curve when graphed in Excel, making the O(n^2) vs O(n log n)
 * difference visible rather than a single data point.
 *
 * Each (size, algorithm) combination is timed over several trials
 * on freshly generated arrays and averaged, to smooth out JVM
 * warm-up/JIT-compilation noise that a single run would otherwise
 * show. Results are printed to the console as a table and written
 * to sorting_benchmark_results.csv for import into Excel.
 * -----------------------------------------------------------
 */
public class SortBenchmark {

    private static final int[] SIZES = {100, 500, 1000, 2000, 4000, 8000, 16000, 20000};
    private static final int TRIALS = 7; // runs per (size, algorithm), averaged
    private static final long SEED = 42L; // fixed base seed -> reproducible datasets

    public static void main(String[] args) throws IOException {
        // Warm up the JIT compiler on a throwaway dataset before taking real
        // measurements, so the first sizes measured aren't unfairly penalised.
        for (int w = 0; w < 3; w++) {
            int[] warm = generateRandomArray(5000, 999 + w);
            SelectionSort.selectionSort(warm.clone());
            QuickSort.quickSort(warm.clone());
            HeapSort.heapSort(warm.clone());
        }

        System.out.printf("%-8s %-22s %-17s %-15s%n", "Size", "Selection Sort (ms)", "Quick Sort (ms)", "Heap Sort (ms)");
        System.out.println("-----------------------------------------------------------------------");

        StringBuilder csv = new StringBuilder();
        csv.append("Size,SelectionSort_ms,QuickSort_ms,HeapSort_ms\n");

        for (int size : SIZES) {
            double selectionTotal = 0, quickTotal = 0, heapTotal = 0;

            for (int t = 0; t < TRIALS; t++) {
                int[] original = generateRandomArray(size, SEED + size * 1000L + t);

                int[] forSelection = original.clone();
                int[] forQuick = original.clone();
                int[] forHeap = original.clone();

                selectionTotal += timeSort(forSelection, SelectionSort::selectionSort);
                quickTotal += timeSort(forQuick, QuickSort::quickSort);
                heapTotal += timeSort(forHeap, HeapSort::heapSort);

                // Correctness check: every run must produce a fully sorted array.
                if (!SelectionSort.isSorted(forSelection)
                        || !QuickSort.isSorted(forQuick)
                        || !HeapSort.isSorted(forHeap)) {
                    throw new IllegalStateException("Sort verification failed at size " + size);
                }
            }

            double selectionMs = selectionTotal / TRIALS;
            double quickMs = quickTotal / TRIALS;
            double heapMs = heapTotal / TRIALS;

            System.out.printf("%-8d %-22.3f %-17.3f %-15.3f%n", size, selectionMs, quickMs, heapMs);
            csv.append(size).append(",")
                    .append(String.format("%.3f", selectionMs)).append(",")
                    .append(String.format("%.3f", quickMs)).append(",")
                    .append(String.format("%.3f", heapMs)).append("\n");
        }

        try (FileWriter writer = new FileWriter("sorting_benchmark_results.csv")) {
            writer.write(csv.toString());
        }
        System.out.println("\nResults written to sorting_benchmark_results.csv");
    }

    /** Functional interface so each sort algorithm can be passed and timed identically. */
    @FunctionalInterface
    interface SortFunction {
        void sort(int[] arr);
    }

    private static double timeSort(int[] arr, SortFunction sortFunction) {
        long start = System.nanoTime();
        sortFunction.sort(arr);
        long end = System.nanoTime();
        return (end - start) / 1_000_000.0; // convert nanoseconds to milliseconds
    }

    private static int[] generateRandomArray(int size, long seed) {
        Random random = new Random(seed);
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(1_000_000); // random ints in [0, 1,000,000)
        }
        return arr;
    }
}
