package com.segarciat.algs4.ch2.sec2.ex23;

import com.segarciat.algs4.ch2.SortUtil;
import edu.princeton.cs.algs4.StdOut;

import static com.segarciat.algs4.ch2.SortCompare.MERGE_NO_COPY;
import static com.segarciat.algs4.ch2.SortCompare.timeRandomInput;

/**
 * <strong>Exercise 2.2.23)</strong>
 * Implements mergesort as in Section 2.2 of Algorithms by Sedgewick and Wayne.
 * In particular, it implements suggested the improvement described, where the roles
 * of the result array and the auxiliary are reversed in order to eliminate
 * the copy into the auxiliary array.
 *
 * @author Sergio E. Garcia Tapia
 */
public class MergeNoCopy {
    private static final int DEFAULT_TRIALS = 5;

    private MergeNoCopy() {}

    public static <T extends Comparable<T>> void sort(T[] a) {
        if (a == null)
            throw new NullPointerException("array cannot be null");
        if (a.length < 2)
            return;

        int n = a.length;
        T[] aux = (T[]) new Comparable[n];
        for (int i = 0; i < n; i++)
            aux[i] = a[i];
        sort(a, aux, 0, n - 1);
    }

    private static <T extends Comparable<T>> void sort(T[] a, T[] aux, int lo, int hi) {
        if (hi <= lo)
            return;
        int mid = lo + (hi - lo) / 2;
        sort(aux, a, lo, mid);
        sort(aux, a, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    /**
     * Given sorted sub-arrays source[lo..mid] and source[mid+1..hi], merges them into
     * the result array such that result[lo..hi] is sorted, without making copies.
     */
    private static <T extends Comparable<T>> void merge(T[] result, T[] source, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (j > hi)
                result[k] = source[i++];
            else if (i > mid)
                result[k] = source[j++];
            else if (SortUtil.less(source[j], source[i]))
                result[k] = source[j++];
            else
                result[k] = source[i++];
        }
    }

    public static void main(String[] args) {
        double prev = timeRandomInput(MERGE_NO_COPY, 256, DEFAULT_TRIALS);
        for (int n = 512; true; n *= 2) {
            double time = timeRandomInput(MERGE_NO_COPY, n, DEFAULT_TRIALS);
            StdOut.printf("n=%d, ratio=%.1f%n", n, time / prev);
            prev = time;
        }
    }
}
