package com.segarciat.algs4.ch1.sec1.ex15;

/**
 * <strong>1.1.15</strong>
 * Write a static method <code>histogram()</code> that takes an array <code>a[]</code>
 * of <code>int</code> values and an integer <code>m</code> as arguments and returns
 * an array of length <code>m</code> whose <code>i</code>th entry is the number of times
 * the integer <code>i</code> appeared in the argument array. If the values in <code>a[]</code>
 * are all between <code>0</code> and <code>m-1</code>, the sum of the values in the returned
 * array should equal to <code>a.length</code>.
 *
 * @author Sergio E. Garcia Tapia
 */
public class Histogram {
    /**
     * Creates an array representing a histogram of the values in the array a between 0 and m-1 (inclusive).
     * @param a Non-null array of integers.
     * @param m Positive integer.
     * @return m-element array whose ith entry is the count of i in the array a.
     */
    public static int[] histogram(int[] a, int m) {
        if (a == null)
            throw new NullPointerException("a should not be null");
        if (m <= 0)
            throw new IllegalArgumentException("m should be non-negative");

        int[] hist = new int[m];
        for (int val: a)
            if (val >= 0 && val < m)
                hist[val]++;
        return hist;
    }
}
