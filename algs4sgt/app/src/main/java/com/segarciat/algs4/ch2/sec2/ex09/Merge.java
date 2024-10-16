package com.segarciat.algs4.ch2.sec2.ex09;

/**
 * @author Sergio E. Garcia Tapia
 * Based on implementation in Secton 2.2 of Algorithms by Sedgewick and Wayne.
 */
public class Merge {
    private Merge() {}

    public static <T extends Comparable<T>> void sort(T[] a) {
        if (a == null)
            throw new NullPointerException("array cannot be null");
        if (a.length  <= 1)
            return;
        T[] aux = (T[]) new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    private static <T extends Comparable<T>> void sort(T[] a, T[] aux, int lo, int hi) {
        assert a != null && aux != null;
        assert a.length <= aux.length;
        assert a.length >= 1;
        assert 0 <= lo;
        assert lo <= hi;
        assert hi < a.length;

        if (hi <= lo)
            return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
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
            else if (less(aux[j], aux[i]))
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }
    }

    private static <T extends Comparable<T>> boolean less(T v, T w) {
        assert v != null;
        assert w != null;
        return v.compareTo(w) < 0;
    }
}
