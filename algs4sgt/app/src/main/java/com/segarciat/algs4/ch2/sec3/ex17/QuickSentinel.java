package com.segarciat.algs4.ch2.sec3.ex17;

import com.segarciat.algs4.ch2.SortUtil;
import edu.princeton.cs.algs4.StdRandom;

/**
 * <strong>2.2.17</strong>
 * Implements quicksort as in Algorithm 2.5 of Algorithms by Sedgewick and
 * Wayne (4th edition). Modified to place the maximum value at the end of
 * the array to use as a sentinel.
 *
 * @author Sergio E. Garcia Tapia
 */
public class QuickSentinel {
    private QuickSentinel() {}

    /**
     * Sorts the given array using quicksort. Based on Algorithm 2.5
     * of Algorithms by Sedgewick and Wayne (4th edition). Modified
     * to place the maximum value at the end of the array to use
     * as a sentinel.
     * @param a The array to sort.
     * @param <T> The type of the elements in the array.
     */
    public static <T extends Comparable<T>> void sort(T[] a) {
        if (a == null)
            throw new NullPointerException("array cannot be null");
        if (a.length < 2)
            return;
        StdRandom.shuffle(a);
        // find max and use as right sentinel
        int maxIndex = 0;
        for (int i = 1; i < a.length; i++) {
            if (SortUtil.less(a[maxIndex], a[i]))
                maxIndex = i;
        }
        SortUtil.exchange(a, 0, maxIndex);
        sort(a, 0, a.length - 1);
    }

    private static <T extends Comparable<T>> void sort(T[] a, int lo, int hi) {
        if (hi <= lo)
            return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static <T extends Comparable<T>> int partition(T[] a, int lo, int hi) {
        T v = a[lo];
        int i = lo;
        int j = hi + 1;

        while (true) {
            while (SortUtil.less(a[++i], v));
            while (SortUtil.less(v, a[--j]));
            if (i >= j)
                break;
            SortUtil.exchange(a, i, j);
        }
        SortUtil.exchange(a, j, lo);
        return j;
    }
}
