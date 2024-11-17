package com.segarciat.algs4.ch2.sec5.ex19;

public final class KendallTau {
    private KendallTau() {}

    /**
     * Computes the Kendall tau distance between the two given arrays,
     * both of which are the same length <code>n</code>, which are permutations
     * of the non-negative integers <code>0</code> through <code>n-1</code>,
     * with no duplicates. The method does not verify that the arrays are
     * indeed permutations.
     * @param a A permutation of the non-negative integers.
     * @param b A permutation of the non-negative integers.
     * @return The number of pairs that are in different order in the two permutations.
     * @throws NullPointerException if either array is <code>null</code>.
     * @throws IllegalArgumentException if the arrays are distinct length.
     */
    public static int distance(int[] a, int[] b) {
        if (a == null || b == null)
            throw new NullPointerException("arrays may not be null");
        if (a.length != b.length)
            throw new IllegalArgumentException("arrays must be of same length");

        int n = a.length;

        if (n <= 1)
            return 0;

        /**
         * Ideas:
         * - Compute the number of inversions (K-tau distance to identity) for both.
         * Then do some operation on that.
         * -
         */


        return 0;
    }
}
