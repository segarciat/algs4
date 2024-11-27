package com.segarciat.algs4.ch3.sec1.ex07;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * <strong>3.1.7)</strong>
 * Computes the average number of distinct values in
 * a sample of N elements, chosen from a set of fixed size
 * (of 1000 integers). The results obtained are not the final
 * word, and simply serve to provide further evidence of the
 * mathematical analysis.
 * @author Sergio E. Garcia Tapia
 */
public final class AverageDistinct {
    private AverageDistinct() {}

    private static final int MIN = 0;
    private static final int MAX = 1000;
    private static final int TRIALS = 50;

    /**
     * Samples <code>n</code> values from a set of 1000
     * integers at random, and returns the number of distinct integers
     * in the sample.
     * @param n The number of times to sample.
     * @return The number of distinct integers in the sample.
     */
    private static int randomKeys(int n) {
        ST<Integer, Integer> integers = new ST<>();
        for (int i = 0; i < n; i++) {
            int r = StdRandom.uniformInt(MIN, MAX);
            ///  value does not matter
            integers.put(r, 1);
        }
        return integers.size();
    }

    public static void main(String[] args) {
        for (int n = 10; n <= 1_000_000; n *= 10) {
            double count = 0;
            for (int t = 0; t < TRIALS; t++)
                count += randomKeys(n);
            StdOut.printf("n=%d, average=%.2f%n", n, count / TRIALS);
        }
    }
}
