package com.segarciat.algs4.ch2.sec2.ex06;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.Color;
import java.util.Arrays;

/**
 * @author Sergio E. Garcia tapia
 * Plots the number of compares used by top-down mergesort and bottom-up mergesort,
 * against the upper bound of 6 * n * log(n) for n from 1 to 512.
 */
public class MergesortPlot {
    private MergesortPlot() {}

    /**
     * @return An array of <code>size</code> random <code>Double</code> values
     * between 0 and 1.
     */
    private static Double[] randomArray(int size) {
        assert size >= 1;
        Double[] a = new Double[size];
        for (int i = 0; i < size; i++)
            a[i] = StdRandom.uniformDouble();
        return a;
    }

    public static void main(String[] args) {
        final int N = 512;
        double maxY = Double.NEGATIVE_INFINITY;

        Queue<Integer> tdPoints = new Queue<>();
        Queue<Integer> buPoints = new Queue<>();
        Queue<Double> logPredictionPoints = new Queue<>();

        // Compute costs for sorts and the predicted values
        for(int n = 1; n <= N; n++) {
            var a = randomArray(n);
            int mergeTDCost = MergeTD.sort(Arrays.copyOf(a, n));
            int mergeBUCost = MergeBU.sort(Arrays.copyOf(a, n));
            double prediction = 6 * n * Math.log(n) / Math.log(2);

            tdPoints.enqueue(mergeTDCost);
            buPoints.enqueue(mergeBUCost);
            logPredictionPoints.enqueue(prediction);

            maxY = Math.max(maxY, Math.max(prediction, Math.max(mergeTDCost, mergeBUCost)));
        }
        // Compute scaling and gap
        final double scalePct = 0.70;
        double maxScaleX = N / scalePct;
        double maxScaleY = maxY / scalePct;
        double gapX = maxScaleX * (1 - scalePct) / 2;
        double gapY = maxScaleY * (1 - scalePct) / 2;
        StdDraw.setXscale(0, maxScaleX);
        StdDraw.setYscale(0, maxScaleY);

        // x-axis
        StdDraw.line(gapX, gapY, maxScaleX - gapX, gapY);
        StdDraw.text(maxScaleX - gapX, gapY / 2, "%d".formatted(N));

        // y-axis
        StdDraw.line(gapX, gapY, gapX, maxScaleY - gapY);
        StdDraw.text(gapX / 2, maxScaleY - gapY, "%.1f".formatted(maxY));

        // Plot the data: top-down in red, bottom-up green, and predicted log in blue.
        StdDraw.setPenRadius(0.0075);
        for (int n = 1; n <= N; n++) {
            StdDraw.setPenColor(Color.RED);
            StdDraw.point(gapX + n, gapY + tdPoints.dequeue());

            StdDraw.setPenColor(Color.GREEN);
            StdDraw.point(gapX + n, gapY + buPoints.dequeue());

            StdDraw.setPenColor(Color.BLUE);
            StdDraw.point(gapX + n, gapY + logPredictionPoints.dequeue());
        }
    }
}
