package com.segarciat.algs4.ch2.sec5.ex17;

import java.util.Arrays;

/**
 * <strong>2.5.17)</strong>
 * @author Sergio E. Garcia Tapia
 */
public final class Stability {
    private Stability() {}

    /**
     * Verifies that <code>sort()</code> places the array in sorted order,
     * leaves the set of elements in the array unchanged, and that it is
     * stable.
     * @param a The array to check.
     * @return <code>true</code> if the sort is stable, orders the array,
     * and leaves the original set of objects intact.
     * @param <T> The type of the elements in the array.
     * @throws NullPointerException if the given array is <code>null</code>.
     */
    public static <T extends Comparable<T>> boolean check(T[] a) {
        if (a == null)
            throw new NullPointerException("array cannot be null");
        if (a.length <= 1)
            return true;

        int n = a.length;
        // Copy the array
        T[] original = (T[]) new Comparable[n];
        for (int i = 0; i < n; i++)
            original[i] = a[i];

        Arrays.sort(a);
        int k = indexOf(original, a[0]);
        if (k == -1)
            return false;

        for (int i = 1; i < n; i++) {
            // Order check
            int cmp = a[i].compareTo(a[i - 1]);
            if (cmp < 0)
                return false;

            // Object check
            int location = indexOf(original, a[i]);
            if (location == -1)
                return false;

            // Stability check
            if (cmp == 0 || location < k)
                return false;
            k = location;
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
    private static <T> int indexOf(T[] a, T val) {
        for (int i = 0; i < a.length; i++)
            if (a[i] == val)
                return i;
        return -1;
    }
}
