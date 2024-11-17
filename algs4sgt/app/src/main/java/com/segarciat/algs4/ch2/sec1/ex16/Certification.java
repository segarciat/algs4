package com.segarciat.algs4.ch2.sec1.ex16;

import com.segarciat.algs4.ch2.SortUtil;

import java.util.Arrays;

/**
 * <strong>2.1.16)</strong>
 * @author Sergio E. Garcia Tapia
 */
public final class Certification {
    private Certification() {}
    /**
     * Verifies that <code>sort()</code> places the array in sorted order
     * and leaves the set of elements in the array unchanged.
     * @param a The array to check.
     * @return <code>true</code> if the array is in order after the sort
     * and contains the same objects, and <code>false</code> otherwise.
     * @param <T> The type of the elements in the array.
     * @throws NullPointerException if the given array is <code>null</code>.
     */
    public static <T extends Comparable<T>> boolean check(T[] a) {
        if (a == null)
            throw new NullPointerException("cannot be null");
        if (a.length <= 1)
            return true;

        int n = a.length;

        // Copy the array
        T[] original = (T[]) new Comparable[n];
        for (int i = 0; i < n; i++)
            original[i] = a[i];

        Arrays.sort(a);

        // Check first object in sorted array
        if (!contains(original, a[0]))
            return false;

        for (int i = 1; i < n; i++) {
            // Order check
            if (!SortUtil.less(a[i], a[i - 1]))
                return false;

            // Object check
            if (!contains(original, a[i]))
                return false;
        }

        return true;
    }

    /**
     * Determines whether the given object is in the array by using
     * <code>==</code> for comparison.
     * @param a The array to search for.
     * @param val The object to search for.
     * @return <code>true</code> if <code>val</code> is an element of the
     * array, and <code>false</code> otherwise.
     * @param <T> The type of the elements in the array.
     */
    private static <T> boolean contains(T[] a, T val) {
        for (T t : a)
            if (t == val)
                return true;
        return false;
    }
}
