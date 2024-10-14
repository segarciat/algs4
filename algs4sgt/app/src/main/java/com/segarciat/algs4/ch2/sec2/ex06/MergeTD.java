package com.segarciat.algs4.ch2.sec2.ex06;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/**
 * @author Sergio E. Garcia Tapia
 * Based on implementation in Secton 2.2 of Algorithms by Sedgewick and Wayne.
 */
public class MergeTD {
    private MergeTD() {}

    private static Comparable[] aux;
    private static int arrayAccesses;

    public static int sort(Comparable[] a) {
        if (a == null)
            throw new NullPointerException("array cannot be null");
        if (a.length  <= 1)
            return 0;
        arrayAccesses = 0;
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
        return arrayAccesses;
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo)
            return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
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
        assert v != null;
        assert w != null;
        arrayAccesses += 2;
        return v.compareTo(w) < 0;
    }
}
