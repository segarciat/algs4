package com.segarciat.algs4.ch1.sec4.ex03;

import edu.princeton.cs.algs4.*;

import java.awt.*;

/**
 * <strong>1.4.3)</strong>
 * Modify <code>DoublingTest</code> to use <code>StdDraw</code> to produce
 * plots like the standard and log-log plots in the text, rescaling as
 * necessary so that the plot always fills a substantial portion of the
 * window.
 */
public class DoublingTest {
    /**
     * Times ThreeSum.count() for n random 6-digit ints.
     * Implementation from page 177 of Algorithms (4th edition) by Sedgewick and Wayne.
     *
     * @param n The number of integers in the array.
     *
     * @return How long <code>ThreeSum.count()</code> took.
     */
    public static double timeTrial(int n) {
        //
        int MAX = 1_000_000;
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = StdRandom.uniformInt(-MAX, MAX);
        Stopwatch timer = new Stopwatch();
        int count = ThreeSum.count(a);
        return timer.elapsedTime();
    }

    public static void main(String[] args) {
        Queue<Point2D> points = new Queue<>();
        StdDraw.enableDoubleBuffering();
        double maxTime = 0.0;
        final double scalePercent = 0.8;
        for (int n = 250; true; n *= 2) {
            // Record trial result
            double time = timeTrial(n);
            points.enqueue(new Point2D(n, time));

            // Update scale parameters
            maxTime = Math.max(time, maxTime);
            double maxScaleX = n / scalePercent;
            double maxScaleY = maxTime / scalePercent;

            // Rescale
            StdDraw.clear();
            StdDraw.setXscale(0, maxScaleX);
            StdDraw.setYscale(0, maxScaleY);
            double gapX = maxScaleX * (1.0 - scalePercent) / 2.0;
            double gapY = maxScaleY * (1.0 - scalePercent) / 2.0;

            // Draw axes
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.setPenRadius(0.005);
            StdDraw.line(gapX, gapY, maxScaleX - gapX, gapY);
            StdDraw.line(gapX, gapY, gapX, maxScaleY - gapY);

            // Draw axes labels
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.setPenRadius(0.001);
            for (double ratio = 0.0; ratio <= 1.0; ratio += 0.2) {
                double xTick = n * ratio;
                StdDraw.text(gapX + xTick, gapY / 2, String.format("%.1fK", xTick / 1000.0));

                double yTick = maxTime * ratio;
                StdDraw.text(gapX / 2, gapY + yTick, String.format("%.2f", yTick));
            }
            StdDraw.text(maxScaleX / 2, maxScaleY - gapY / 2,
                    "ThreeSum: time as a function of input size");

            // Plot trial data
            StdDraw.setPenColor(Color.RED);
            StdDraw.setPenRadius(0.01);
            for (Point2D p: points)
                StdDraw.point(gapX + p.x(), gapY + p.y());

            StdDraw.show();
            StdOut.printf("%7d %7.1f%n", n, time);
        }
    }
}
