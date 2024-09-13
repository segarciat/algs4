package com.segarciat.algs4.ch1.sec1.ex14;

/**
 * 1.1.14
 * Write a static method lg() that takes an input value n as argument and returns
 * the largest int not larger than the base-2 logarithm of n. Do not use Math
 * (Java's Math package).
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
