package com.segarciat.algs4.ch1.sec4.ex14;

import edu.princeton.cs.algs4.BinarySearch;

import java.util.Arrays;

/**
 * <strong>1.4.14) </strong>
 * <em>4-sum</em>. Develop an algorithm for the 4-<em>sum</em> problem.
 */
public class FourSum {
    /**
     * Finds the number of tuples in <code>a</code> that sum to 0.
     *
     * @param a An array with integer values.
     * @return The number of tuples in <code>a</code> that sum to 0.
     */
    public static int count(int[] a) {
        if (a == null)
            throw new NullPointerException("a cannot be null");

        Arrays.sort(a);
        int count = 0;
        for (int i = 0; i < a.length; i++)
            for (int j = i + 1; j < a.length; j++)
                for (int k = j + 1; k < a.length; k++)
                    if (BinarySearch.indexOf(a, -(a[i] + a[j] + a[k])) > k)
                        count++;
        return count;
    }
}
