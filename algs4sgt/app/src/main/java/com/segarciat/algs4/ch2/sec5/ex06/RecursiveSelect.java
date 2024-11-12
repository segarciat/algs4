package com.segarciat.algs4.ch2.sec5.ex06;

import com.segarciat.algs4.ch2.SortUtil;
import edu.princeton.cs.algs4.StdRandom;

/**
 * <strong>2.5.6)</strong>
 * Recursive implementation of the <code>select()</code> method
 * in the book.
 * @author Sergio E. Garcia Tapia
 */
public class RecursiveSelect {
    /**
     * Selects the <code>k</code>th smallest item in <code>a[]</code>.
     * @param a The array from which to choose items.
     * @param k The ordinal position of the item of interest (as if the
     *          array were sorted). Must be a value between <code>a</code>
     *          and <code>a.length - 1</code>
     * @return The <code>k</code>th smallest item in the array.
     * @param <T> The type of elements in the array.
     */
    public static <T extends Comparable<T>> T select(T[] a, int k) {
        if (a == null)
            throw new NullPointerException("cannot be null");
        if (k < 0 || k >= a.length)
            throw new IllegalArgumentException("invalid integer");

        StdRandom.shuffle(a);
        return select(a, 0, a.length - 1, k);
    }

    private static <T extends Comparable<T>> T select(T[] a, int lo, int hi, int k) {
        if (hi == lo)
            return a[k];
        int j = partition(a, lo, hi);
        if (j == k)
            return a[k];
        else if (j > k)
            return select(a, lo, j - 1, k);
        else
            return select(a, j + 1, hi, k);
    }

    private static <T extends Comparable<T>> int partition(T[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        T v = a[lo];
        while(true) {
            while(SortUtil.less(a[++i], v)) {
                if (i == hi)
                    break;
            }
            while (SortUtil.less(v, a[--j])) {
                if (j == lo)
                    break;
            }
            if (i >= j)
                break;
            SortUtil.exchange(a, i, j);
        }
        SortUtil.exchange(a, lo, j);
        return j;
    }

    public static void main(String[] args) {

    }
}
