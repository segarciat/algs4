package com.segarciat.algs4.ch2.sec1.ex17;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Based on the <code>Example</code> class in section 2.1, page 245
 * of Algorithms by Sedgewick and Wayne.
 */
public class SortUtil {
    public static <T extends Comparable<T>> boolean less(T v, T w) {
        if (v == null || w == null)
            throw new NullPointerException("values cannot be null");
        return v.compareTo(w) < 0;
    }

    public static <T> void exchange(T[] a, int i, int j) {
        if (a == null)
            throw new NullPointerException("array cannot be null");
        if (i < 0 || j < 0 || i >= a.length || j >= a.length)
            throw new IllegalArgumentException("invalid indices for array");
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * Shows vertical bars corresponding to each value of the given
     * array.
     * @param a An array with values between 0 and 1.
     */
    public static void show(Double[] a) {
        if (a == null)
            throw new NullPointerException("array cannot be null");
        StdDraw.setXscale(-0.05, 1.05);
        StdDraw.setYscale(-0.05, 1.05);
        StdDraw.setPenRadius(0.05);
        StdDraw.clear();
        int n = a.length;
        for (int i = 0; i < n; i++) {
            double x = (i + 0.5) / n;
            double y = a[i] / 2;
            double rw = 0.4 / (double) n;
            double rh = a[i] / 2;
            StdDraw.filledRectangle(x, y, rw, rh);
        }
    }

    public static Double[] randomValues(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("number of values must be positive");
        Double[] a = new Double[n];
        for (int i = 0; i < n; i++)
            a[i] = StdRandom.uniformDouble();
        return a;
    }
}
