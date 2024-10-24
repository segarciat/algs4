package com.segarciat.algs4.ch2.sec2.ex21;

import edu.princeton.cs.algs4.Merge;

import java.util.Arrays;

public class Triplicates {
    /**
     * Given three lists with the same number of names, determines if there is
     * any name in common and returns the lexicographically first such name.
     * The lists are not modified.
     * @param a An array of <code>n</code> names.
     * @param b An array of <code>n</code> names.
     * @param c An array of <code>n</code> names.
     * @return The (lexicographically) first name in common in all three lists,
     * or <code>null</code> if no such name exists.
     * @throws NullPointerException if any of the arrays is <code>null</code>.
     * @throws IllegalArgumentException if the arrays have distinct lengths.
     */
    public static String triplicates(String[] a, String[] b, String[] c) {
        if (a == null || b == null || c == null)
            throw new NullPointerException("arrays cannot be null");
        int n = a.length;
        if (n == 0 || n != b.length || n != c.length)
            throw new IllegalArgumentException("arrays must all be of the same positive size");

        String[] s = Arrays.copyOf(a, n);
        String[] r = Arrays.copyOf(b, n);
        String[] t = Arrays.copyOf(c, n);

        // Sort the arrays; linearithmic
        Merge.sort(s);
        Merge.sort(t);
        Merge.sort(r);

        // Search the arrays (3n array accesses)
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < n && j < n && k < n) {
            if (s[i] == null) {
                i++;
                continue;
            }
            if (r[j] == null) {
                j++;
                continue;
            }
            if (t[k] == null) {
                k++;
                continue;
            }

            int srCmp = s[i].compareTo(r[j]);
            int rtCmp = r[j].compareTo(t[k]);

            // By transitivity, all must be equal
            if (srCmp == 0 && rtCmp == 0)
                return s[i];

            int stCmp = s[i].compareTo(t[k]);

            if (srCmp <= 0 && stCmp <= 0)
                i++; // s[i] is smallest
            else if (rtCmp <= 0 && srCmp >= 0)
                j++; // r[j] is smallest
            else
                k++; // t[k] is smallest
        }
        return null;
    }

    public static void main(String[] args) {
        String[] a = { "abby", "ben","carly", "dean" };
        String[] b = { "carly", "ash",  "dean", "bob",};
        String[] c = { "dean", "christine", "carly", "billy" };
        String[] d = { "viv", "serg", "david", "dean" };
        String[] e = { "pinky", "dixie", "lala", "poh" };

        System.out.println(triplicates(a, b ,c));
        System.out.println(triplicates(b, c ,d));
        System.out.println(triplicates(c, d, e));
    }
}
