package com.segarciat.algs4.ch1.sec1.ex32;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;

/**
 * <strong>1.1.32)</strong>
 * <em>Histogram</em>. Suppose that the standard input stream is a sequence
 * of <code>double</code> values. Write a program that takes an integer <em>n</em>
 * and two <code>double</code> values <em>lo</em> and <em>hi</em> from the command
 * line and uses <code>StdDraw</code> to plot a histogram of the count of the numbers
 * in the standard input stream that fall in each of the <em>n</em> intervals defined
 * by dividing (<code>lo</code>, <code>hi</code>) into <code>n</code> equal-sized intervals.
 */
public class Histogram {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Provide three arguments: n, lo, and hi");
            System.err.println();
            System.err.println("n    A positive integer. The interval (lo, hi) is divided by n.");
            System.err.println("lo   A floating-point number. The left endpoint of the histogram interval.");
            System.err.println("hi   A floating-point number. The right endpoint of the histogram interval.");
            System.err.println("     The value must be greater than lo.");
            System.err.println();
            System.err.println("Plots a histogram of the count of floating-point numbers in the");
            System.err.println("standard input stream that fall in the n equal-sized intervals defined");
            System.err.println("by (lo, hi).");
        }

        int n;
        double lo, hi;

        try {
            n = Integer.parseInt(args[0]);
            lo = Double.parseDouble(args[1]);
            hi = Double.parseDouble(args[2]);
        } catch (Exception e) {
            throw new RuntimeException(String.format("%s: n must be an integer, while lo and hi are doubles",
                    e.getLocalizedMessage()));
        }

        if (n <= 0) {
            System.err.println("n must be positive");
            System.exit(1);
        }

        if (lo >= hi) {
            System.err.println("lo must be strictly less than hi");
            System.exit(1);
        }
        double intervalSize = (hi - lo) / n;
        int[] frequencies = new int[n];
        int max = 0;
        while (!StdIn.isEmpty()) {
            double value = StdIn.readDouble();
            if (value <= lo || value >= hi)
                continue;
            int k = (int) Math.floor((value - lo) / intervalSize);
            frequencies[k]++;
            if (max < frequencies[k])
                max = frequencies[k];
        }
        StdDraw.setXscale(-1, n);
        StdDraw.setYscale(-1, max + 1);
        for (int k = 0; k < frequencies.length; k++) {
            double x = k;
            double y = frequencies[k] / 2.0;
            double rw = 0.45;
            double rh = y;
            StdDraw.filledRectangle(x, y, rw, rh);
        }
    }
}
