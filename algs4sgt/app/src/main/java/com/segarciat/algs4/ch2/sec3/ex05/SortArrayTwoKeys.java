package com.segarciat.algs4.ch2.sec3.ex05;

import com.segarciat.algs4.ch2.SortUtil;
import edu.princeton.cs.algs4.StdRandom;

/**
 * <code>2.3.5)</code>
 * Implements an algorithm to sort an array that is assumed to have at most
 * 2 distinct keys.
 * @author Sergio E. Garcia Tapia
 */
public class SortArrayTwoKeys {
    /**
     * Creates an array of <code>n</code> elements that has at most
     * 2 distinct integers.
     * @param n The number of elements in the result array.
     * @return An n-element array with at most 2 distinct values.
     */
    private static Integer[] arrayWithTwoKeys(int n) {
        if (n < 2)
            throw new IllegalArgumentException("size must be at least 2");
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++)
            a[i] = (StdRandom.uniformDouble() < 0.5) ? 0 : 1;
        return a;
    }

    /**
     * Given an array with at most 2 distinct keys, sorts the array.
     * @param a An array with at most 2 distinct keys.
     * @param <T> The type of items in the array.
     * @throws NullPointerException if the array is <code>null</code>.
     */
    private static <T extends Comparable<T>> void sort(T[] a) {
        if (a == null)
            throw new NullPointerException("array cannot be null");
        if (a.length < 2)
            return;

        // Find first index of largest key.
        int i = 0;
        while (i < a.length - 1 && !SortUtil.less(a[i + 1], a[i]))
            i++;
        for (int j = i + 1; j < a.length; j++) {
            if (SortUtil.less(a[j], a[j - 1]))
                SortUtil.exchange(a, i++, j);
        }
    }

    public static void main(String[] args) {
        for (int n = 256; true; n *= 2) {
            Integer[] a = arrayWithTwoKeys(n);
            sort(a);
            assert SortUtil.isSorted(a, 0, n - 1);
            System.out.printf("n=%d%n", n);
        }
    }
}
