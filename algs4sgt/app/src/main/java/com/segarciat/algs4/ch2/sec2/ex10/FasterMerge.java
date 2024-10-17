package com.segarciat.algs4.ch2.sec2.ex10;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/**
 * @author Sergio E. Garcia Tapia
 * Mergesort implementation based on Section 2.2 of Algorithms by Sedgewick and Wayne
 */
public class FasterMerge {
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
        for (int k = lo; k <= mid; k++)
            aux[k] = a[k];
        // Copy second half in decreasing order
        for (int k = hi; k > mid; k--)
            aux[mid + 1 + (hi - k)] = a[k];

        int i = lo;
        int j = hi;
        for (int k = lo; k <= hi; k++)
            if (less(aux[j], aux[i]))
                a[k] = aux[j--];
            else
                a[k] = aux[i++];
    }

    private static <T extends Comparable<T>> boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[10];
        for (int i = 0; i < a.length; i++)
            a[i] = StdRandom.uniformInt(0, 200);
        sort(a);
        StdOut.println(Arrays.toString(a));
    }
}
