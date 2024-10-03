package com.segarciat.algs4.ch1.sec4.ex17;

import edu.princeton.cs.algs4.Point2D;

public class FarthestPair1D {
    /**
     * Finds the two values whose difference is no smaller than the
     * difference of any other pair (in absolute value)
     * @param a Array of at least two values.
     * @return The farthest pair in the array.
     * @throws NullPointerException if the array is <code>null</code>.
     * @throws IllegalArgumentException if the array has less than 2 values.
     */
    public static Point2D farthestPair(int[] a) {
        if (a == null)
            throw new NullPointerException("array cannot be null");
        if (a.length < 2)
            throw new IllegalArgumentException("must have at least 2 values");

        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;

        for (int i = 0; i < a.length; i++) {
            if (a[i] < min)
                min = a[i];
            if (a[i] > max)
                max = a[i];
        }

        return new Point2D(min, max);
    }
}
