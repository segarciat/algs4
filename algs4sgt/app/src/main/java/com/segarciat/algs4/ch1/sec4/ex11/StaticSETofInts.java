package com.segarciat.algs4.ch1.sec4.ex11;

import java.util.Arrays;

/**
 * <strong>1.4.11)</strong>
 * Add ann instance method <code>howMany()</code> to <code>StaticSETofInts</code>
 * (page 99) that finds the number of occurrences of a given key in time proportional
 * to log(n) in the worst case.
 */
public class StaticSETofInts {
    private final int[] a;

    public StaticSETofInts(int[] keys) {
        if (keys == null || keys.length == 0)
            throw new IllegalArgumentException("keys must be non-null with at least 1 item");
        a = new int[keys.length];
        for (int i = 0; i < keys.length; i++)
            a[i] = keys[i]; // defensive copy
        Arrays.sort(a);
    }

    /**
     * Determines whether <code>key</code> is in the set.
     *
     * @param key The value to search for
     * @return <code>true</code> if <code>key</code> is in the set, and <code>false</code>
     * otherwise.
     */
    public boolean contains(int key) {
        return smallestIndexOf(key) != -1;
    }

    /**
     * Determines how many times <code>key</code> appears in the set
     * @param key The value whose occurrences we want to count.
     * @return How many times <code>key</code> appears in the set.
     */
    public int howMany(int key) {
        int smallest = smallestIndexOf(key);
        if (smallest == -1)
            return 0;
        return largestIndexOf(key) - smallest + 1;
    }

    /**
     * Determines the smallest index of the private array <code>a</code>
     * containing <code>key</code>.
     *
     * @param key Value to test for.
     *
     * @return An integer between 0 and <code>a.length - 1 </code>, or -1 if
     * key is not in the array.
     */
    private int smallestIndexOf(int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key > a[mid])
                lo = mid + 1;
            else
                hi = mid - 1;
        }
        if (lo >= a.length || a[lo] != key)
            return -1;
        return lo;
    }

    /**
     * Determines the highest index of the private array <code>a</code>
     * containing <code>key</code>.
     *
     * @param key Value to test for.
     *
     * @return An integer between 0 and <code>a.length - 1 </code>, or -1 if
     * key is not in the array.
     */
    private int largestIndexOf(int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (key >= a[mid])
                lo = mid + 1;
            else
                hi = mid - 1;
        }
        if (lo >= a.length || a[lo] != key)
            return -1;
        return lo;
    }
}
