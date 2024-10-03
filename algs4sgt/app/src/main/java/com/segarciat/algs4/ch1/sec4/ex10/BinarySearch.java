package com.segarciat.algs4.ch1.sec4.ex10;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * Modify binary search so that it always returns the element with
 * the smallest index that matches the search element (and still
 * guarantees logarithmic running time).
 */
public class BinarySearch {
    /**
     * Given a sorted array <code>a</code>, determines the smallest index in <code>a</code>
     * corresponding to a value equal to <code>key</code>.
     *
     * @param a Sorted array.
     * @param key Value to search for.
     *
     * @return An integer between 0 and <code>a.length - 1</code>, or -1 if not found.
     */
    public static long indexOf(int[] a, int key) {
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

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Provide one argument: a file with integers");
        }
        In in = new In(args[0]);
        int[] a = in.readAllInts();
        Arrays.sort(a);

        StdOut.println("Provide integers to search for in file");
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            long index = indexOf(a, key);
            if (index == -1)
                StdOut.printf("Cannot find: %d%n", key);
            else
                StdOut.printf("Smallest sorted index: %d%n", index);
        }
    }
}
