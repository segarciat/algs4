package com.segarciat.algs4.ch5.sec1.ex12;

import edu.princeton.cs.algs4.Alphabet;

/**
 * <strong>5.1.12)</strong>
 * Adapts {@link edu.princeton.cs.algs4.LSD} to work with a specific
 * alphabet.
 * @author Sergio E. Garcia Tapia
 */
public final class LSD {
    /**
     * Sorts an array of strings whose characters come from the specified
     * alphabet.
     * @param a Array of strings of length <code>w</code> with characters in alphabet <code>alpha</code>.
     * @param w Exact length of strings in array.
     * @param alpha The alphabet of interest.
     */
    public static void sort(String[] a, int w, Alphabet alpha) {
        if (a == null || alpha == null)
            throw new NullPointerException();
        if (a.length == 0 || w <= 0)
            throw new IllegalArgumentException();

        int n = a.length;

        String[] aux = new String[n];
        int R = alpha.radix();
        for (int d = w - 1; d >= 0; d--) {
            // Compute frequency counts
            int[] count = new int[R + 1];
            for (int i = 0; i < n; i++)
                count[alpha.toIndex(a[i].charAt(d)) + 1]++;

            // Transform counts into indices
            for (int r = 0; r < R; r++)
                count[r + 1] += count[r];

            // Distribute keys
            for (int i = 0; i < n; i++)
                aux[count[alpha.toIndex(a[i].charAt(d))]++] = a[i];

            // Copy-back
            for (int i = 0; i < n; i++)
                a[i] = aux[i];
        }
    }
}
