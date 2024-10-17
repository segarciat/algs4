package com.segarciat.algs4.ch2;

import edu.princeton.cs.algs4.StdRandom;

/**
 * Based on the <code>Example</code> class in section 2.1, page 245
 * of Algorithms by Sedgewick and Wayne.
 *
 * @author Sergio E. Garcia Tapia
 */
public class SortUtil {
    private SortUtil() {}

    /**
     * Determines whether <code>v</code> is less than <code>w</code>
     * @throws NullPointerException if <code>v</code> or <code>w</code> is <code>null</code>.
     */
    public static <T extends Comparable<T>> boolean less(T v, T w) {
        if (v == null || w == null)
            throw new NullPointerException("values cannot be null");
        return v.compareTo(w) < 0;
    }

    /**
     * Exchanges the items in the given indices in the array.
     * @param a An array of items.
     * @param i An array index, at least 0, at most <code>a.length-1</code>
     * @param j An array index, at least 0, at most <code>a.length-1</code>
     * @throws NullPointerException if the array is <code>null</code>
     * @throws IllegalArgumentException if the indices are out of bound.
     */
    public static <T> void exchange(T[] a, int i, int j) {
        if (a == null)
            throw new NullPointerException("array cannot be null");
        if (i < 0 || j < 0 || i >= a.length || j >= a.length)
            throw new IllegalArgumentException("invalid indices for array");
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * Determines whether the subarray a[lo..hi] of <code>a</code> is sorted.
     */
    public static <T extends Comparable<T>> boolean isSorted(T[] a, int lo, int hi) {
        if (a == null)
            throw new NullPointerException("array cannot be null");
        if (lo < 0 || hi < 0 || lo >= a.length || hi >= a.length)
            throw new IndexOutOfBoundsException("invalid sub-array indices");
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1]))
                return  false;
        return true;
    }

    /**
     * Creates an array of <code>n</code> random values.
     * @param n The number of random values for the array.
     * @return An array of size <code>n</code> with random values.
     * @throws IllegalArgumentException if <code>n</code> is non-positive.
     */
    public static Double[] createRandomDoubleArray(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("number of values must be positive");
        Double[] a = new Double[n];
        for (int i = 0; i < n; i++)
            a[i] = StdRandom.uniformDouble();
        return a;
    }

}
