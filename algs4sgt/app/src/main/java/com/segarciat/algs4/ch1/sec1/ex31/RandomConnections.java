package com.segarciat.algs4.ch1.sec1.ex31;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;

/**
 * <strong>1.1.31</strong>
 * <em>Random Connections</em>. Write a program that takes as command-line arguments
 * an integer <code>n</code> and a <code>double</code> value <code>p</code> (between 0 and 1),
 * plots <code>n</code> equally spaced dots of size 0.05 on the circumference of a circle,
 * and then, with probability <code>p</code> for each pair of points, draws a gray line
 * connecting them.
 */
public class RandomConnections {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Provide two arguments: n and p");
            System.err.println();
            System.err.println("n    The number of equally-spaced points on the circumference of a circle.");
            System.err.println("p    A decimal between 0 and 1 representing a probability with which");
            System.err.println("     a pair of points may be connected.");
            System.exit(1);
        }

        int n;
        double p;
        try {
            n = Integer.parseInt(args[0]);
            p = Double.parseDouble(args[1]);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Pass an integer as a first argument, and a decimal for the second");
        }
        if (n <= 1) {
            System.err.println("The number n of equally-spaced points must be at least 2");
            System.exit(1);
        }
        if (p < 0 || p > 1) {
            System.err.println("Invalid probability p: must be between 0 and 1");
            System.exit(1);
        }

        StdDraw.setXscale(-1.5, 1.5);
        StdDraw.setYscale(-1.5, 1.5);
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(Color.RED);
        StdDraw.circle(0, 0, 1);
        StdDraw.setPenRadius(0.05);
        StdDraw.setPenColor(Color.BLACK);
        double[][] points = new double[n][2];
        for (int k = 0; k < n; k++) {
            double angle = 2 * Math.PI * k / n;
            double x = Math.cos(angle);
            double y = Math.sin(angle);
            points[k][0] = x;
            points[k][1] = y;
            StdDraw.point(x, y);
        }

        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(Color.CYAN);
        StdRandom.setSeed(System.nanoTime());
        for (int k = 0; k < n; k++) {
            double x = points[k][0];
            double y = points[k][1];
            for (int m = k + 1; m < n; m++) {
                if (StdRandom.uniformDouble() < p) {
                    double u = points[m][0];
                    double v = points[m][1];
                    StdDraw.line(x, y, u, v);
                }
            }
        }
    }
}
