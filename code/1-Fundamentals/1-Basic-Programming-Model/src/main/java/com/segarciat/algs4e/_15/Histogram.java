package com.segarciat.algs4e._15;

/**
 * 1.1.15
 * Write a static method histogram() that takes an array a[]
 * 	of int values and an integer m as arguments and returns
 * 	an array m whose ith entry is the number of times the integer
 * 	i appeared in the argument array. If the values in a[] are all
 * 	between 0 and m-1, the sum of the values in the returned array
 * 	should equal to a.length.
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
