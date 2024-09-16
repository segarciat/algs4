package com.segarciat.algs4.ch1.sec1.ex29;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * <strong>1.1.29)Equal keys</strong>
 * Add to <code>BinarySearch</code> a static method <code>rank()</code> that takes a sorted
 * array of <code>int</code> values (some of which may be equal) and a key as arguments and
 * returns the number of elements that are smaller than the key and a similar method
 * <code>count()</code> that returns the number of elements equal to the key. <em>Note</em>:
 * If <code>i</code> and <code>j</code> are the values returned by <code>rank(a, key)</code>
 * and <code>count(a, key)</code>, respectively, then <code>a[i..i+j-1]</code> are the values
 * in the array that are equal to key.
 */
public class BinarySearch {
    /**
     * Given a sorted array <code>a</code>, determines how many elements in <code>a</code>
     * are <em>less than</em> <code>key</code>.
     * <code>key</code>.
     * @param a Sorted array.
     * @param key Value to test for.
     *
     * @return An integer between 0 and <code>a.length</code>, representing the number of elements
     * in <code>a</code> <em>less than</em> <code>key</code>.
     */
    public static long rank(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (key > a[mid])
                lo = mid + 1;
            else
                hi = mid - 1;
        }
        return lo;
    }

    /**
     * Given a sorted array <code>a</code>, determines how many elements in <code>a</code>
     * are less than or equal to <code>key</code>.
     * <code>key</code>.
     * @param a Sorted array.
     * @param key Value to test for.
     *
     * @return An integer between 0 and <code>a.length</code>, representing the number of elements
     * in <code>a</code> <em>less than or equal to</em> <code>key</code>.
     */
    private static long rankGe(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (key >= a[mid])
                lo = mid + 1;
            else
                hi = mid - 1;
        }
        return lo;
    }

    /**
     * Given a sorted array <code>a</code>, returns the number of entries
     * in <code>a></code> that equal <code>key</code>.
     *
     * @param a A sorted array.
     * @param key A target value.
     * @return How many entries in <code>a</code> equal <code>key</code>.
     */
    public static long count(int[] a, int key) {
        return rankGe(a, key) - rank(a, key);
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int[] a = in.readAllInts();
        Arrays.sort(a);

        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            StdOut.printf("Rank: %d%nEqual Count: %d%n", rank(a, key), count(a, key));
        }
    }
}
