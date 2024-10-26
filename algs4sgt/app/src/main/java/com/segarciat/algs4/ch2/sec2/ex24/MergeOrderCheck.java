package com.segarciat.algs4.ch2.sec2.ex24;

import com.segarciat.algs4.ch2.SortUtil;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * <strong>2.2.24)</strong>
 * Same as {@link com.segarciat.algs4.ch2.sec2.ex23.MergeOrderCheck},
 * but additional  keeps track of the number of times the check
 * whether <code>a[mid] <= a[mid]</code> succeeds.
 *
 * @author Sergio E. Garcia Tapia
 */
public class MergeOrderCheck {
    private static int inOrderCheckSucceeded = 0;

    private MergeOrderCheck() {}

    public static <T extends Comparable<T>> void sort(T[] a) {
        if (a == null)
            throw new NullPointerException("array cannot be null");
        if (a.length < 2)
            return;

        int n = a.length;
        T[] aux = (T[]) new Comparable[n];
        inOrderCheckSucceeded = 0;
        sort(a, aux, 0, n - 1);
    }

    private static <T extends Comparable<T>> void sort(T[] a, T[] aux, int lo, int hi) {
        if (hi <= lo)
            return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        // a[lo..mid] and a[mid+1..hi] sorted with a[mid] <= a[mid +1]
        if (!SortUtil.less(a[mid + 1], a[mid])) {
            inOrderCheckSucceeded++;
            return;
        }
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
        for (int n = 512; true; n *= 2) {
            Double[] a = new Double[n];
            for (int i = 0; i < n; i++)
                a[i] = StdRandom.uniformDouble();
            sort(a);
            StdOut.printf("n=%d, inOrderSuccessCount=%d, successToSizeRatio=%.2f%n",
                    n, inOrderCheckSucceeded, (double) inOrderCheckSucceeded / n);
        }
    }
}
