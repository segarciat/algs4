package com.segarciat.algs4.ch2.sec2.ex23;

import com.segarciat.algs4.ch2.SortUtil;
import edu.princeton.cs.algs4.StdOut;

import static com.segarciat.algs4.ch2.SortCompare.MERGE_ORDER_CHECK;
import static com.segarciat.algs4.ch2.SortCompare.timeRandomInput;

/**
 * <strong>2.2.23)</strong>
 * Implements mergesort as in Section 2.2 of Algorithms by Sedgewick and Wayne,
 * and in particular, implements the suggested improvement that skips calls to
 * <code>merge()</code> when <code>a[mid] <= a[mid + 1]</code>, as
 * suggested in Section 2.2.
 *
 * @author Sergio E. Garcia Tapia
 */
public class MergeOrderCheck {
    private static final int DEFAULT_TRIALS = 5;
    private MergeOrderCheck() {}

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
        if (hi <= lo)
            return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        // a[lo..mid] and a[mid+1..hi] sorted with a[mid] <= a[mid +1]
        if (!SortUtil.less(a[mid + 1], a[mid]))
            return;
        merge(a, aux, lo, mid, hi);
    }

    /**
     * Given sorted subarrays a[lo..mid] and a[mid+1..hi], merges them into the sorted
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
        double prev = timeRandomInput(MERGE_ORDER_CHECK, 256, DEFAULT_TRIALS);
        for (int n = 512; true; n *= 2) {
            double time = timeRandomInput(MERGE_ORDER_CHECK, n, DEFAULT_TRIALS);
            StdOut.printf("n=%d, ratio=%.1f%n", n, time / prev);
            prev = time;
        }
    }
}
