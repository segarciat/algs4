package com.segarciat.algs4.ch2.sec3.ex19;

import com.segarciat.algs4.ch2.SortCompare;
import com.segarciat.algs4.ch2.SortUtil;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * <code>2.2.19</code>
 * Implements quicksort based on the implementation given in
 * Algorithms by Sedgewick and Wayne (Algorithm 2.5), but
 * using a partitioning key that is a median of a sample of
 * 5 random items from the subarray.
 * @author Sergio E. Garcia Tapia
 */
public class QuickMedianOf5 {
    private QuickMedianOf5() {}

    public static <T extends Comparable<T>> void sort(T[] a) {
        if (a == null)
            throw new NullPointerException("array cannot be null");
        if (a.length < 2)
            return;
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static <T extends Comparable<T>> void sort(T[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    public static <T extends Comparable<T>> int partition(T[] a, int lo, int hi) {
        if (lo + 4 <= hi) {
            medianOf5(a, lo, hi);
        } else {
            setMedianOf3(a, lo, hi);
        }
        T v = a[lo];
        int i = lo;
        int j = hi + 1;
        while (true) {
            while (SortUtil.less(a[++i], v))
                continue;
            while (SortUtil.less(v, a[--j]))
                continue;
            if (i >= j)
                break;
            SortUtil.exchange(a, i, j);
        }
        SortUtil.exchange(a, j, lo);
        return j;
    }

    private static <T extends Comparable<T>> void medianOf5(T[] a, int lo, int hi) {
        int mid = lo + (hi - lo) / 2;
        int midLo = lo + (mid - lo) / 2;
        int midHi = mid + (hi - mid) / 2;

        if (SortUtil.less(a[midLo], a[lo]))
            SortUtil.exchange(a, midLo, lo);

        if (SortUtil.less(a[hi], a[midHi]))
            SortUtil.exchange(a, hi, midHi);
        if (SortUtil.less(a[hi], a[mid]))
            SortUtil.exchange(a, hi, mid);
        if (SortUtil.less(a[midHi], a[mid]))
            SortUtil.exchange(a, midHi, mid);

        if (SortUtil.less(a[hi], a[midLo])) {
            SortUtil.exchange(a, hi, midLo);
            if (SortUtil.less(a[midLo], a[lo])) {
                SortUtil.exchange(a, midLo, lo);
            } else {
                SortUtil.exchange(a, midHi, midLo);
                if (SortUtil.less(a[lo], a[midLo])) {
                    SortUtil.exchange(a, lo, midLo);
                }
            }
        } else {
            if (SortUtil.less(a[midHi], a[midLo])) {
                SortUtil.exchange(a, midHi, midLo);
                if (SortUtil.less(a[lo], a[midLo])) {
                    SortUtil.exchange(a, lo, midLo);
                }
            } else { // midHi was next biggest
                if (SortUtil.less(a[mid], a[midLo])) {
                    SortUtil.exchange(a, lo, midLo);
                } else {
                    SortUtil.exchange(a, lo, mid);
                }
            }
        }
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
        for (int n = 512; true; n *= 2) {
            double timeMed5 = SortCompare.timeRandomInput(SortCompare.QUICK_MEDIAN_OF_5, n, trials) / trials;
            double timeMed3 = SortCompare.timeRandomInput(SortCompare.QUICK_MEDIAN_OF_3, n, trials) / trials;
            double timeRegular = SortCompare.timeRandomInput(SortCompare.QUICK_SORT, n, trials) / trials;
            StdOut.printf("n=%d,%n\t%.2f faster than median of 3%n\t%.2f faster than standard%n",
                    n, timeMed3 / timeMed5, timeRegular / timeMed5);
        }
    }
}
