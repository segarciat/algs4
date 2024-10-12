package com.segarciat.algs4.ch1.sec5.ex16;

import com.segarciat.algs4.ch1.sec5.UF;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.awt.Color;

/**
 * @author Sergio E. Garcia Tapia
 */
public class AmortizedCostPlot {
    public static <T extends UF & Costable> void plot(T uf) {
        final double scalePercent = 0.8;
        Queue<Integer> costPerOperation = new Queue<>();
        int maxCost = 0;
        int connections = 0;
        while (!StdIn.isEmpty()) {
            connections++;
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            uf.resetCost();
            if (!uf.connected(p, q)) {
                uf.union(p, q);
                StdOut.println(p + " " + q);
            }
            costPerOperation.enqueue(uf.getCost());
            if (uf.getCost() > maxCost)
                maxCost = uf.getCost();
        }
        StdOut.println(uf.count() + " components");

        double maxScaleX = connections / scalePercent;
        double maxScaleY = maxCost / scalePercent;
        double gapX = maxScaleX * (1.0 - scalePercent) / 2.0;
        double gapY = maxScaleY * (1.0 - scalePercent) / 2.0;

        StdDraw.setXscale(0, maxScaleX);
        StdDraw.setYscale(0, maxScaleY);
        // Draw axes
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setPenRadius(0.005);
        // x-axis
        StdDraw.line(gapX, gapY, maxScaleX - gapX, gapY);
        // y-axis
        StdDraw.line(gapX, gapY, gapX, maxScaleY - gapY);

        StdDraw.setPenRadius(0.01);
        // x-axis max label
        StdDraw.text(gapX + connections, gapY / 2, String.format("%d", connections));
        // y-axis max label
        StdDraw.text(gapX / 2, gapY + maxCost, String.format("%d", maxCost));

        int i = 0;
        double total = 0;
        while (!costPerOperation.isEmpty()) {
            i++;

            int cost = costPerOperation.dequeue();
            StdDraw.setPenColor(Color.GRAY);
            StdDraw.point(gapX + i, gapY + cost);

            total += cost;
            StdDraw.setPenColor(Color.RED);
            StdDraw.point(gapX + i, gapY + total / i);
        }
    }
}
