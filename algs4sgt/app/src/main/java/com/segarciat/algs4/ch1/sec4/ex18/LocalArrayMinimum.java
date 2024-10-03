package com.segarciat.algs4.ch1.sec4.ex18;

public class LocalArrayMinimum {
    /**
     * Finds the index of a local minimum.
     * @param a Array of distinct values, with at least two values.
     * @return Index of a local minimum, or -1.
     * @throws NullPointerException if the array is <code>null</code>.
     * @throws IllegalArgumentException if there are less than 2 values in the array.
     */
    public static int localMinimum(int[] a) {
        if (a == null)
            throw new NullPointerException("cannot be null");
        if (a.length < 2)
            throw new IllegalArgumentException("must have at least 2 elements");

        // Outer local min
        if (a[0] < a[1])
            return 0;
        if (a[a.length - 1] < a[a.length - 2])
            return a.length - 1;

        // Inner local min
        int lo = 1;
        int hi = a.length - 2;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid] > a[mid + 1])
                lo = mid + 1;
            else if (a[mid] > a[mid - 1])
                hi = mid - 1;
            else
                return mid;
        }

        // No local min
        return -1;
    }
}
