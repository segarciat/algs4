package com.segarciat.algs4.ch1.sec4.ex20;

public class BitonicSearch {
    /**
     * Determines whether the given key exists in the given bitonic
     * array.
     * @param a A bitonic array, meaning it has an increasing sequence
     *          immediately followed by a decreasing sequence.
     * @param key The value that we will search for.
     * @return The index of the key in the array, or -1 if the key does not exist.
     * @throws NullPointerException if the array is <code>null</code>.
     * @throws IllegalArgumentException if the array is empty.
     */
    public static int indexOf(int[] a, int key) {
        if (a == null)
            throw new NullPointerException("array cannot be null");
        if (a.length == 0)
            throw new IllegalArgumentException("array has no elements");

        // See if it's an edge entry.
        if (a[0] == key)
            return 0;
        if (a[a.length - 1] == key)
            return a.length - 1;

        if (a.length < 2)
            return -1;

        // Find the maximum value
        int lo = 1;
        int hi = a.length - 2;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid] < a[mid + 1])
                lo = mid + 1;
            else if (a[mid] < a[mid - 1])
                hi = mid - 1;
            else
                break;
        }
        final int maxIndex = lo + (hi - lo) / 2;
        if (a[maxIndex] == key)
            return maxIndex;

        // Search inner left (increasing sequence)
        lo = 1;
        hi = maxIndex - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key > a[mid])
                lo = mid + 1;
            else if (key < a[mid])
                hi = mid - 1;
            else
                return mid;
        }

        // Search inner right (decreasing sequence)
        lo = maxIndex + 1;
        hi = a.length - 2;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key > a[mid])
                hi = mid - 1;
            else if (key < a[mid])
                lo = mid + 1;
            else
                return mid;
        }

        // Not in the array
        return -1;
    }
}
