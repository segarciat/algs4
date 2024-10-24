package com.segarciat.algs4.ch2.sec2.ex11;

import com.segarciat.algs4.ch2.SortUtil;
import edu.princeton.cs.algs4.StdOut;

import static com.segarciat.algs4.ch2.SortCompare.MERGE_IMPROVED;
import static com.segarciat.algs4.ch2.SortCompare.timeRandomInput;

/**
 * Implements mergesort as in Section 2.2 of Algorithms by Sedgewick and Wayne,
 * and in particular, implements the suggested improvement that skips calls to
 * <code>merge()</code> when <code>a[mid] <= a[mid + 1]</code>, as
 * suggested in Section 2.2.
 * @author Sergio E. Garcia Tapia
 */
public class MergeImproved {
    private static final int SMALL_SUBARRAY_SIZE_CUTOFF = 63;
    private static final int DEFAULT_TRIALS = 5;

    private MergeImproved() {}

    /**
     * Sorts the given array.
     * @param a The array to be sorted.
     * @throws NullPointerException if the array is <code>null</code>.
     */
    public static <T extends Comparable<T>> void sort(T[] a) {
        if (a == null)
            throw new NullPointerException("array cannot be null");
        if (a.length  < 2)
            return;
        // Auxiliary array used during sorting.
        T[] aux = (T[]) new Comparable[a.length];
        for (int i = 0; i < a.length; i++)
            aux[i] = a[i];
        sort(a, aux, 0, a.length - 1);
    }

    private static <T extends Comparable<T>> void sort(T[] a, T[] aux, int lo, int hi) {
        // Subarrays of size SMALL_SUBARRAY_SIZE_CUTOFF + 1 or less use insertion sort.
        if (hi <= lo + SMALL_SUBARRAY_SIZE_CUTOFF) {
            insertionSort(a, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(aux, a, lo, mid);
        sort(aux, a, mid + 1, hi);

        // Skip merge() if subarrays are ordered
        if (!SortUtil.less(aux[mid+1], aux[mid])) {
            for (int k = lo; k <= hi; k++)
                a[k] = aux[k];
            return;
        }
        merge(a, aux, lo, mid, hi);
    }

    private static <T extends Comparable<T>> void merge(T[] a, T[] aux, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                a[k] = aux[j++];
            else if (j > hi)
                a[k] = aux[i++];
            else if (SortUtil.less(aux[j], aux[i]))
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }
    }

    /**
     * Sorts a[lo..hi] with insertion sort.
     */
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

    public static void main(String[] args) {
        double prev = timeRandomInput(MERGE_IMPROVED, 256, DEFAULT_TRIALS);
        for (int n = 512; true; n *= 2) {
            double time = timeRandomInput(MERGE_IMPROVED, n, DEFAULT_TRIALS);
            StdOut.printf("n=%d, ratio=%.1f%n", n, time / prev);
            prev = time;
        }
    }
}
