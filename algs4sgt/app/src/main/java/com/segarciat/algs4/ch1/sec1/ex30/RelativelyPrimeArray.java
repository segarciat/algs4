package com.segarciat.algs4.ch1.sec1.ex30;

import com.segarciat.algs4.ch1.sec1.ex24.Euclid;

/**
 * <strong>1.1.30)</strong>
 * <code>Array exercise</code>. Write a code fragment that creates an <em>n</em>-by-<em>n</em>
 * boolean <code>a[][]</code> array such that <code>a[i][j]</code> is <code>true</code> if
 * <code>i</code> and <code>j</code> are relatively prime (have no common factors), and
 * <code>false</code> otherwise.
 */
public class RelativelyPrimeArray {
    /**
     * Creates a square two-dimensional array whose [i][j] entry is <code>true</code>
     * if i and j are relatively prime, and <code>false</code> otherwise.
     * @param n The number of rows (and columns) in the array.
     * @return A boolean array with entry (i, j) true if and only if gcd(i, j) = 1.
     * @throws IllegalArgumentException if n is non-positive.
     */
    public static boolean[][] relativePrimeArr(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("size must be at least 1");
        boolean[][] a = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            a[i][i] = false;
            for (int j = i + 1; j < n; j++) {
                boolean isRelPrime = (Euclid.gcd(i, j) == 1);
                a[i][j] = isRelPrime;
                a[j][i] = isRelPrime;
            }
        }
        return a;
    }
}
