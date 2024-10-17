package com.segarciat.algs4.ch2.sec2.ex06;

import com.segarciat.algs4.ch2.SortUtil;

/**
 * @author Sergio E. Garcia Tapia
 * Based on implementation in Secton 2.2 of Algorithms by Sedgewick and Wayne.
 */
public class MergeBU {
    private MergeBU() {}

    private static int arrayAccesses;
    private static Comparable[] aux;

    public static int sort(Comparable[] a) {
        if (a == null)
            throw new NullPointerException("array cannot be null");
        if (a.length <= 1)
            return 0;
        int n = a.length;
        arrayAccesses = 0;
        aux = new Comparable[n];
        for (int len = 1; len < n; len *= 2)
            for (int lo = 0; lo < n - len; lo += len + len)
                merge(a, lo, lo + len - 1, Math.min(lo + len + len - 1, n - 1));
        return arrayAccesses;
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        assert a != null;
        assert a.length >= 1;
        assert 0 <= lo;
        assert lo <= mid;
        assert mid <= hi;
        assert hi < a.length;

        arrayAccesses += 4 * (hi - lo + 1);

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
            else if (less(aux[j], aux[i]))
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        arrayAccesses += 2;
        return SortUtil.less(v, w);
    }
}
