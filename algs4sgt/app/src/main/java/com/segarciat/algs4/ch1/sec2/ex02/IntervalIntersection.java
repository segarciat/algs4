package com.segarciat.algs4.ch1.sec2.ex02;

import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.StdIn;

/**
 * <strong>1.2.2)</strong>
 * Write an <code>Interval1D</code> client that takes an <code>int</code> value
 * <em>n</em> as command-line argument, reads <em>n</em> intervals (each defined by a pair
 * of <code>double</code> values) from standard input, and prints all pairs that intersect.
 *
 * @author Sergio E. Garcia Tapia
 */
public class IntervalIntersection {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.print("Provide a positive integer with value at least 2.");
            System.exit(1);
        }

        int n;
        try {
            n = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            throw new RuntimeException(String.format("Failed to parse as integer: %s", args[0]));
        }

        if (n < 2) {
            System.err.printf("Expected a positive integer with value at least 2, but got: %d%n", n);
            System.exit(1);
        }

        Interval1D[] intervals = new Interval1D[n];
        for (int k = 0; k < n; k++) {
            double min = StdIn.readDouble();
            double max = StdIn.readDouble();
            intervals[k] = new Interval1D(min, max);
        }

        // Print intersecting pairs
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++)
                if (intervals[i].intersects(intervals[j]))
                    System.out.printf("%s %s%n", intervals[i], intervals[j]);
        }
    }
}
