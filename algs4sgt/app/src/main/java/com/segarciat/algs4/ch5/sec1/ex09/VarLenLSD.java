package com.segarciat.algs4.ch5.sec1.ex09;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/**
 * <strong>5.1.9)</strong>
 * Implements a version of LSD that works for variable-length strings.
 * Assumes that the given string has only extended ASCII characters.
 * The implementation is a hybrid of {@link edu.princeton.cs.algs4.LSD}
 * and {@link edu.princeton.cs.algs4.MSD}.
 * @author Sergio E. Garcia Tapia
 */
public final class VarLenLSD {
    public static final int R = 256;
    private static int charAt(String s, int i) {
        return i >= s.length() ? -1 : s.charAt(i);
    }

    /**
     * Sorts an array whose strings is expected to contain only
     * characters whose value is no more than {@value R}.
     * @param a An array of strings.
     */
    public static void sort(String[] a) {
        if (a == null)
            throw new NullPointerException("cannot sort null array");
        if (a.length <= 1)
            return;

        int n = a.length;
        // Find length of the longest string.
        int w = 0;
        for (String s: a)
            if (s.length() > w)
                w = s.length();

        String[] aux = new String[n];
        for (int d = w - 1; d >= 0; d--) {
            int[] count = new int[R + 2];

            // Compute frequency counts
            for (int i = 0; i < n; i++)
                count[charAt(a[i], d) + 2]++;

            // Transform counts to indices
            for (int r = 0; r < R + 1; r++)
                count[r + 1] += count[r];

            // Distribute keys
            for (int i = 0; i < n; i++)
                aux[count[charAt(a[i], d) + 1]++] = a[i];

            // Copy-back
            for (int i = 0; i < n; i++)
                a[i] = aux[i];
        }
    }

    public static void main(String[] args) {
        String[] a = {
                "acorn", "amy", "bird", "cake", "continuous", "cat", "derivative", "day", "denmark", "dire",
                "eloquent", "euler", "exact", "function", "first", "go"
        };
        StdRandom.shuffle(a);
        sort(a);
        StdOut.println(Arrays.toString(a));
    }
}
