package com.segarciat.algs4.ch2.sec3.ex18;

import com.segarciat.algs4.ch2.SortCompare;
import com.segarciat.algs4.ch2.SortUtil;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * <strong>2.2.18</strong>
 * Implements quicksort as in Algorithm 2.5 of Algorithms by Sedgewick and
 * Wayne (4th edition). Modified to use median-of-3 partitioning.
 *
 * @author Sergio E. Garcia Tapia
 */
public class QuickMedianOf3 {
    private QuickMedianOf3() {}

    public static <T extends Comparable<T>> void sort(T[] a) {
        if (a == null)
            throw new NullPointerException("array cannot be null");
        if (a.length < 2)
            return;
        StdRandom.shuffle(a);
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
        setMedianOf3(a, lo, hi);
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

    private static <T extends Comparable<T>> void setMedianOf3(T[] a, int lo, int hi) {
        int mid = lo + (hi - lo) / 2;
        if (SortUtil.less(a[hi], a[lo]))
            SortUtil.exchange(a, lo, hi);
        // now lo <= hi

        if (SortUtil.less(a[hi], a[mid]))
            SortUtil.exchange(a, hi, mid);
        // now mid <= hi

        // place median at lo
        if (SortUtil.less(a[lo], a[mid]))
            SortUtil.exchange(a, lo, mid);
    }

    public static void main(String[] args) {
        final int trials = 5;
        double prev = SortCompare.timeRandomInput(SortCompare.QUICK_MEDIAN_OF_3, 256, trials) / trials;
        for (int n = 512; true; n *= 2) {
            double time = SortCompare.timeRandomInput(SortCompare.QUICK_MEDIAN_OF_3, n, trials) / trials;
            StdOut.printf("n=%d, ratio=%.1f%n", n, time / prev);
            prev = time;
        }
    }
}
