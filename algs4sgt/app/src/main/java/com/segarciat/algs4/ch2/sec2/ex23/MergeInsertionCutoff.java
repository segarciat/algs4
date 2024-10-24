package com.segarciat.algs4.ch2.sec2.ex23;

import com.segarciat.algs4.ch2.SortUtil;
import edu.princeton.cs.algs4.StdOut;

import static com.segarciat.algs4.ch2.SortCompare.MERGE_INSERTION_CUTOFF;
import static com.segarciat.algs4.ch2.SortCompare.timeRandomInput;

/**
 * <strong>Exercise 2.2.23)</strong>
 * Implements mergesort as in Section 2.2 of Algorithms by Sedgewick and Wayne.
 * In particular, implements the suggested improvement, where for sub-arrays
 * whose length is beyond a certain cutoff, the algorithm switches to
 * insertion sort instead of further recursing. The cutoff value is hardcoded,
 * but I chose a value that I determined empirically by doing sample runs
 * on my system.
 *
 * @author Sergio E. Garcia Tapia
 */
public class MergeInsertionCutoff {
    private static final int CUTOFF = 63;
    private static final int DEFAULT_TRIALS = 5;
    private MergeInsertionCutoff() {}

    public static <T extends Comparable<T>> void sort(T[] a) {
        if (a == null)
            throw new NullPointerException("array cannot be null");
        if (a.length < 2)
            return;

        int n = a.length;
        T[] aux = (T[]) new Comparable[n];
        sort(a, aux, 0, n - 1);
    }

    private static <T extends Comparable<T>> void sort(T[] a, T[] aux, int lo, int hi) {
        if (hi <= lo + CUTOFF) {
            insertionSort(a, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    private static <T extends Comparable<T>> void insertionSort(T[] a, int lo, int hi) {
        for (int i = lo; i < hi; i++) {
            // Do half-exchanges
            T temp = a[i];
            int j;
            for (j = i + 1; j > lo && SortUtil.less(temp, a[j - 1]); j--) {
                a[j] = a[j - 1];
            }
            a[j] = temp;
        }
    }

    /**
     * Given sorted sub-arrays a[lo..mid] and a[mid+1..hi], merges them into the sorted
     * array a[lo..hi] by using aux for an auxiliary copy.
     */
    private static <T extends Comparable<T>> void merge(T[] a, T[] aux, int lo, int mid, int hi) {
        // Auxiliary copy
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (j > hi)
                a[k] = aux[i++];
            else if (i > mid)
                a[k] = aux[j++];
            else if (SortUtil.less(aux[j], aux[i]))
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }
    }

    public static void main(String[] args) {
        double prev = timeRandomInput(MERGE_INSERTION_CUTOFF, 256, DEFAULT_TRIALS);
        for (int n = 512; true; n *= 2) {
            double time = timeRandomInput(MERGE_INSERTION_CUTOFF, n, DEFAULT_TRIALS);
            StdOut.printf("n=%d, ratio=%.1f%n", n, time / prev);
            prev = time;
        }
    }
}
