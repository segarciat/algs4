package com.segarciat.algs4.ch1.sec4.ex16;

import edu.princeton.cs.algs4.Point2D;

import java.util.Arrays;

public class ClosestPair1D {
    /**
     * Determines the two values whose difference is no greater than the
     * difference of any other pair.
     * @param a Arrays of at least 2 values.
     * @return The closest pair.
     * @throws NullPointerException if the array is <code>null</code>.
     * @throws IllegalArgumentException if the array has less than 2 values.
     */
    public static Point2D closestPair(double[] a) {
        if (a == null)
            throw new NullPointerException("array cannot be null");
        if (a.length < 2)
            throw new IllegalArgumentException("must have at least 2 values");

        Arrays.sort(a);
        double minimum = Double.POSITIVE_INFINITY;
        int minIndex = 1;
        for (int i = 1; i < a.length; i++) {
            double distance = a[i] - a[i - 1];
            if (distance < minimum) {
                minimum = distance;
                minIndex = i;
            }
        }

        return new Point2D(a[minIndex - 1], a[minIndex]);
    }
}
