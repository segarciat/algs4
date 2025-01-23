package com.segarciat.algs4.ch5.sec1.ex12;

import edu.princeton.cs.algs4.Alphabet;

/**
 * <strong>5.1.12)</strong>
 * Extends {@link edu.princeton.cs.algs4.MSD} to work with an arbitrary
 * alphabet provided by the user.
 * @author Sergio E. Garcia Tapia
 */
public final class MSD {

    /**
     * Sorts the given array of strings, whose characters should belong
     * to the specified alphabet.
     * @param a An array of strings whose characters belong to the specified <code>alphabet</code>.
     * @param alphabet The alphabet of interest.
     */
    public static void sort(String[] a, Alphabet alphabet) {
        if (a == null || alphabet == null)
            throw new NullPointerException();

        int cutoff = (int) Math.sqrt(alphabet.radix());
        String[] aux = new String[a.length];
        sort(a, aux, alphabet, cutoff, 0, a.length - 1, 0);
    }

    /**
     * Helper method for {@link #sort(String[], Alphabet)}.
     */
    private static void sort(String[] a, String[] aux, Alphabet alphabet, int cutoff, int lo, int hi, int d) {
        if (hi <= lo + cutoff) {
            sort(a, lo, hi, d, alphabet);
            return;
        }

        int R = alphabet.radix();

        // Compute frequency counts
        int[] count = new int[R + 2];
        for (int i = lo; i <= hi; i++)
            count[charAt(a[i], d, alphabet) + 2]++;

        // Transform counts to indices
        for (int r = 0; r < R + 1; r++)
            count[r + 1] += count[r];

        // Distribute keys
        for (int i = lo; i <= hi; i++)
            aux[count[charAt(a[i], d, alphabet) + 1]++] = a[i];

        // Copy-back
        for (int i = lo; i <= hi; i++)
            a[i] = aux[i - lo];

        // Apply recursively
        for (int r = 0; r < R; r++)
            sort(a, aux, alphabet, cutoff, lo + count[r], lo + count[r + 1] - 1, d + 1);
    }

    private static int charAt(String s, int d, Alphabet alphabet) {
        if (d >= s.length())
            return -1;
        return alphabet.toIndex(s.charAt(d));
    }

    /**
     * Helper to {@link #sort(String[], Alphabet)}. Uses insertion sort for small sub-arrays.
     * Assumes the first d characters of each string are equal.
     */
    private static void sort(String[] a, int lo, int hi, int d, Alphabet alphabet) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j - 1], d, alphabet); i--) {
                // swap
                String temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
    }

    /**
     * Helper to {@link #sort(String[], int, int, int, Alphabet)}. Compares
     * only characters past the <code>d</code>-th character.
     */
    private static boolean less(String v, String w, int d, Alphabet alphabet) {
        for (int i = d; i < Math.min(v.length(), w.length()); i++) {
            int vIndex = alphabet.toIndex(v.charAt(i));
            int wIndex = alphabet.toIndex(w.charAt(i));
            if (vIndex < wIndex)
                return true;
            else if (vIndex > wIndex)
                return false;
        }
        return v.length() < w.length();
    }
}
