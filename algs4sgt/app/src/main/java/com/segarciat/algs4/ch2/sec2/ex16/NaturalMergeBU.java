package com.segarciat.algs4.ch2.sec2.ex16;

import com.segarciat.algs4.ch2.SortUtil;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class NaturalMergeBU {
    private NaturalMergeBU() {}

    public static <T extends Comparable<T>> void sort(T[] a) {
        if (a == null)
            throw new NullPointerException("array cannot be null");
        if (a.length < 2)
            return;

        int n = a.length;
        T[] aux = (T[]) new Comparable[n];
        int lo = 0;
        while (true) {
            int mid = lo;
            while (mid + 1 < n && !SortUtil.less(a[mid + 1], a[mid]))
                mid++;
            if (mid + 1 >= n) {
                if (lo == 0) // sorted
                    break;
                else {
                    lo = 0;
                    continue;
                }
            }
            int hi = mid + 1;
            while (hi + 1 < n && !SortUtil.less(a[hi + 1], a[hi]))
                hi++;
            merge(a, aux, lo, mid, hi);
            lo = hi + 1;
        }
    }

    private static <T extends Comparable<T>> void merge(T[] a, T[] aux, int lo, int mid, int hi) {
        assert a != null;
        assert a.length >= 1;
        assert 0 <= lo;
        assert lo <= mid;
        assert mid <= hi;
        assert hi < a.length;

        // Temp copy
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];

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
     * Performs a timed trial of the sort implemented by this class on
     * an array of <code>n</code> values of type <code>Double</code>.
     * @param n The size of the array for the experiment.
     * @return The time it took to sort an array of <code>n</code> random <code>Double</code>
     * values.
     */
    private static double timeTrial(int n) {
        Double[] a = new Double[n];
        for (int i = 0; i < n; i++)
            a[i] = StdRandom.uniformDouble();
        Stopwatch timer = new Stopwatch();
        sort(a);
        double elapsed = timer.elapsedTime();
        assert SortUtil.isSorted(a, 0, n - 1);
        return elapsed;
    }

    public static void main(String[] args) {
        double prev = timeTrial(256);
        for (int n = 512; true; n *= 2) {
            double time = timeTrial(n);
            StdOut.printf("n=%d, ratio=%.1f%n", n, time / prev);
            prev = time;
        }
    }
}
