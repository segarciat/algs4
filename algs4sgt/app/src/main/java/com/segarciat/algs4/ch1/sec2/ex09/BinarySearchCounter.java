package com.segarciat.algs4.ch1.sec2.ex09;

import edu.princeton.cs.algs4.Counter;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * <strong>1.1.9)</strong>
 * Instrument <code>BinarySearch</code> (page 47) to use a <code>Counter</code> to count
 * the total number of keys examined during all searches and then print the total after all searches
 * are complete. <em>Hint</em>: Create a <code>Counter</code> in <code>main()</code> and pass it as
 * an argument to <code>indexOf()</code>.
 *
 * @author Sergio E. Garcia Tapia
 */
public class BinarySearchCounter {
    /**
     * Based on page 47 of "Algorithms" (4th edition) by Sedgewick and Wayne.
     * If <code>key</code> is found in the array <code>a</code>, returns its
     * index. Otherwise, returns -1. Uses <code>counter</code> to keep track
     * of the number of keys examined.
     *
     * @param a Sorted array of integers.
     * @param key Value to search for.
     * @param counter Counter incremented  each time a key is examined.
     *
     * @return -1 if the key is not found, or its index if it is found.
     */
    public static int indexOf(int[] a, int key, Counter counter) {
        if (a == null)
            throw new NullPointerException("array a should not be null");
        if (counter == null)
            throw new NullPointerException("counter should not be null");
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            counter.increment();
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid])
                hi = mid - 1;
            else if (key > a[mid])
                lo = mid + 1;
            else
                return mid;
        }
        return -1;
    }

    /**
     * Based on test client as in page 47 of Algorithms (4th edition) by Sedgewick and Wayne.
     * Modified to print the tally of keys examined.
     */
    public static void main(String[] args) {
        Counter examinedCount = new Counter("Total Keys Examined");
        In in = new In(args[0]);
        int[] whitelist = in.readAllInts();
        Arrays.sort(whitelist);

        while  (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if (indexOf(whitelist, key, examinedCount) == -1)
                StdOut.println(key);
        }
        StdOut.println(examinedCount);
    }
}
