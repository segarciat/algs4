package com.segarciat.algs4.ch1.sec1.ex22;

import edu.princeton.cs.algs4.StdOut;

/**
 * <strong>1.1.22)</strong>
 * Write a version of <code>BinarySearch</code> that uses the recursive <code>indexOf()</code>
 * given on page 25 and <em>traces</em> the method calls. Each time the recursive method
 * is called, print the argument values <code>lo</code> and <code>hi</code>, indented by the depth
 * of the recursion. <em>Hint</em>: Add an argument to the recursive method that keeps track
 * of the depth.
 *
 * @author Sergio E. Garcia Tapia
 */
public class BinarySearchTrace {
    public static int indexOf(int[] a, int key) {
        return indexOf(a, key, 0, a.length-1, new StringBuilder());
    }

    /**
     * Adapted from the recursive <code>indexOf()</code> implementation in page 25 of Algorithms
     * 4th Edition by Sedgewick and Wayne.
     * Given a sorted array <code>a</code> of <code>int</code> and an index range
     * <code>lo</code>..<code>hi</code> (inclusive), searches for <code>key</code>, using the
     * <code>sbDepth</code> argument to print call traces.
     *
     * @param a Sorted array of integers.
     * @param key Integer to search for.
     * @param lo Lowest array index (inclusive)
     * @param hi Highest array index (inclusive)
     * @param sbDepth Call depth string for tracing
     *
     * @return The index of <code>key</code> in array <code>a</code> if found, or <code>-1</code>
     * if <code>key</code> is not in <code>a</code>.
     */
    private static int indexOf(int[] a, int key, int lo, int hi, StringBuilder sbDepth) {
        if (lo > hi)
            return -1;
        StdOut.printf("%slo=%d, hi=%d%n", sbDepth.toString(), lo, hi);
        int mid = lo + (hi - lo) / 2;
        if (key < a[mid])
            return indexOf(a, key, lo, mid - 1, sbDepth.append(" "));
        else if (key > a[mid])
            return indexOf(a, key, mid + 1, hi, sbDepth.append(" "));
        return mid;
    }
}
