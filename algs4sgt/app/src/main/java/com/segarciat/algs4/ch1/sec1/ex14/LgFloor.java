package com.segarciat.algs4.ch1.sec1.ex14;

/**
 * <strong>1.1.14)</strong>
 * Write a static method <code>lg()</code> that takes an input value <code>n</code>
 * as argument and returns the largest <code>int</code> not larger than the base-2 logarithm of
 * <code>n</code>. Do not use <code>Math</code>.
 *
 * @author Sergio E. Garcia Tapia
 */

public class LgFloor {
    /**
     * Returns the largest int no larger than the base-2 logarithm of n.
     * @param n Positive integer.
     * @return The floor of the base-2 logarithm of n.
     */
    public static int lg(int n) {
        // lg(n) is undefined when n <= 0
        if (n <= 0)
            throw new IllegalArgumentException("n must be positive");
        // k is the number of bits in n minus 1.
        int k = 0;
        while (n > 1) {
            n >>>= 1;   // Logical right shift by 1 is equivalent to dividing by 2.
            k++;
        }
        return k;
    }
}
