package com.segarciat.algs4.ch1.sec2.ex01;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * <strong>1.2.1)</strong>
 * Write a <code>Point2D</code> client that takes an integer value <em>n</em> from the command line,
 * generates <em>n</em> random points in the unit square, and computes the distance separating the
 * <em>closest pair</em> of points.
 *
 * @author Sergio E. Garcia Tapia
 */
public class ClosestPointPair {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("You must provide a single positive integer argument with value at least 2.");
            System.exit(1);
        }

        int n;
        try {
            n = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            throw new RuntimeException(String.format("Expected an integer, but got: %s%n", args[0]));
        }
        if (n < 2) {
            System.err.printf("Expected integer that was at least 2, but got: %d%n", n);
            System.exit(1);
        }

        // Create n points in unit square.
        Point2D[] points = new Point2D[n];
        StdRandom.setSeed(System.nanoTime());
        for (int i = 0; i < points.length; i++) {
            double x = StdRandom.uniformDouble();
            double y = StdRandom.uniformDouble();
            points[i] = new Point2D(x, y);
        }

        // Determine the shortest point separation.
        double closest = Double.POSITIVE_INFINITY;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                double distance = points[j].distanceTo(points[i]);
                if (distance < closest)
                    closest = distance;
            }
        }

        StdOut.println(closest);
    }
}
