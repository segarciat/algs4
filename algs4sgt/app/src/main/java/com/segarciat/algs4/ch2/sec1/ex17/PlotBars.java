package com.segarciat.algs4.ch2.sec1.ex17;

import com.segarciat.algs4.ch2.SortUtil;
import edu.princeton.cs.algs4.StdDraw;

/**
 * Helper class for plotting vertical bars for sorting with Exercise 2.2.17
 * in Algorithms by Sedgewick and Wayne
 *
 * @author Sergio E. Garcia Tapia
 */
public class PlotBars {
    private PlotBars() {}
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

    /**
     * Creates an array of random doubles from a uniform distribution in [0, 1].
     * Shows the array as a collection of vertical bars, and returns it.
     *
     * @param args The array of command-line arguments.
     *
     * @return An array of random doubles in the interval [0, 1].
     *
     * @throws RuntimeException if the array does not contain exactly one argument.
     * @throws NumberFormatException if the argument cannot be parsed as an integer.
     * @throws IllegalArgumentException if the parsed integer is non-positive.
     */
    public static Double[] createAndShowRandomArray(String[] args) {
        if (args.length != 1) {
            System.err.println("Sorts n values and shows the process by drawing vertical bars");
            System.err.println("Provide a command-line argument: n");
            System.err.println("n    The number of vertical bars");
            System.exit(1);
        }
        int n = Integer.parseInt(args[0]);
        Double[] a = SortUtil.createRandomDoubleArray(n);
        show(a);
        return a;
    }
}
