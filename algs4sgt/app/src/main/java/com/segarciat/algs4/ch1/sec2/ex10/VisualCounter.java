package com.segarciat.algs4.ch1.sec2.ex10;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;

/**
 * <strong>1.1.10)</strong>
 * Develop a class <code>VisualCounter</code> that allows both increment and decrement
 * operations. Take two arguments <code>n</code> and <code>max</code> in the constructor,
 * where <code>n</code> specifies the maximum number of operations and <code>max</code>
 * specifies the maximum absolute value of the counter. As a side effect, create a plot
 * showing the value of the counter each time its tally changes.
 *
 * @author Sergio E. Garcia Tapia
 */
public class VisualCounter {
    private final int MAX_OPERATIONS;
    private final int ABSOLUTE_MAX_VALUE;

    private int opCount = 0;
    private int val = 0;

    public VisualCounter(int n, int max) {
        if (n <= 0 || max <= 0)
            throw new IllegalArgumentException("n and max must be positive");
        MAX_OPERATIONS = n;
        ABSOLUTE_MAX_VALUE = max;
    }

    /**
     * Increments the counter's value if the number of operations has not been reached,
     * and if its value would not exceed the maximum value. Plots the new tally
     * on StdDraw.
     */
    public void increment() {
        if (opCount < MAX_OPERATIONS) {
            opCount++;
            if (val < ABSOLUTE_MAX_VALUE)
                val++;
            plot();
        }
    }

    /**
     * Decrements the counter's value if the number of operations has not been reached,
     * and if its value would not go below the minimum value. Plots the new tally
     * on StdDraw.
     */
    public void decrement() {
        if (opCount < MAX_OPERATIONS) {
            opCount++;
            if (-ABSOLUTE_MAX_VALUE < val)
                val--;
            plot();
        }
    }

    private void plot() {
        StdDraw.setXscale(0, MAX_OPERATIONS + 1.0);
        StdDraw.setYscale(-ABSOLUTE_MAX_VALUE + 0.0, ABSOLUTE_MAX_VALUE + 0.0);

        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.point(opCount, val);
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Provide exactly two arguments: n, max");
            System.err.println();
            System.err.println("n    A positive integer. The maximum number of increment/decrement operations.");
            System.err.println("max  A positive integer. The maximum absolute value of the counter.");
            System.exit(1);
        }

        int n = Integer.parseInt(args[0]);
        int max = Integer.parseInt(args[1]);

        if (n <= 0 || max <= 0) {
            System.err.println("n and max must be positive integers");
            System.exit(1);
        }

        VisualCounter counter = new VisualCounter(n, max);
        StdRandom.setSeed(System.nanoTime());
        for (int i = 0; i < n; i++) {
            if (StdRandom.uniformDouble() >= 0.5)
                counter.increment();
            else
                counter.decrement();
        }
    }
}
