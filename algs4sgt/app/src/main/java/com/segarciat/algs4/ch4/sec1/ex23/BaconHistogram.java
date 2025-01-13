package com.segarciat.algs4.ch4.sec1.ex23;

import com.segarciat.algs4.ch4.sec1.ex13.BreadthFirstPaths;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.SymbolGraph;

import java.awt.Color;

/**
 * <strong>4.1.23)</strong>
 * Computes a histogram of Kevin Bacon numbers from the graph encoded
 * by movies.txt by computing the degrees of separation with the help
 * of BreadthFirstSearch.
 * @author Sergio E. Garcia Tapia
 */
public class BaconHistogram {
    public static void main(String[] args) {
        SymbolGraph sg = new SymbolGraph(args[0], args[1]);
        Graph G = sg.graph();
        String source = args[2];
        if (!sg.contains(source)) {
            StdOut.println(source + " not in database.");
            return;
        }

        int s = sg.indexOf(source);
        BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);
        ST<Double, Integer> frequencies = new ST<>();

        double maxBaconNumber = 0;
        double maxFrequency = 0;
        for (int v = 0; v < G.V(); v++) {
            int distance = bfs.distTo(v);

            // odd distance (degree) vertices are movies
            if ((distance % 2) == 1) {
                continue;
            }

            // even distance (degree) vertices are actors
            double baconNumber = (distance == -1) ? Double.POSITIVE_INFINITY : distance / 2.0;
            Integer f = frequencies.get(baconNumber);
            if (f == null)
                f = 0;
            f += 1;
            frequencies.put(baconNumber, f);

            if (baconNumber != Double.POSITIVE_INFINITY) {
                maxBaconNumber = Math.max(maxBaconNumber, baconNumber);
            }
            maxFrequency = Math.max(maxFrequency, f);
        }

        double scalePct = 0.8;
        double maxScaleX = (maxBaconNumber + 2) / scalePct; // account for 0 and infinity
        double maxScaleY = maxFrequency / scalePct;
        StdDraw.setXscale(0, maxScaleX);
        StdDraw.setYscale(0, maxScaleY);
        double gapX = maxScaleX * (1.0 - scalePct) / 2.0;
        double gapY = maxScaleY * (1.0 - scalePct) / 2.0;

        // Draw x-axis
        StdDraw.line(gapX, gapY, maxScaleX - gapX, gapY);

        // Draw y-axis
        StdDraw.line(gapX, gapY, gapX, maxScaleY - gapY);

        StdDraw.setPenRadius(0.0075);
        StdDraw.text(maxScaleX / 2.0, gapY / 2.5, "Kevin Bacon Number");
        StdDraw.text(gapX / 2.0, maxScaleY / 2.0, "Freq.");
        for (Double baconNumber: frequencies.keys()) {
            int frequency = frequencies.get(baconNumber);


            double halfW = 0.25;
            double halfH = frequency / 2.0;
            double centerX = baconNumber == Double.POSITIVE_INFINITY ?
                    gapX + 0.5 + maxBaconNumber + 1 :
                    gapX + 0.5 + baconNumber;
            double centerY = gapY + (frequency / 2.0);
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.filledRectangle(centerX, centerY, halfW, halfH);
            StdDraw.text(centerX, 0.7 * gapY, "%.0f".formatted(baconNumber));
            StdDraw.setPenColor(Color.RED);
            StdDraw.text(centerX, 1.2 * gapY + frequency, "%d".formatted(frequency));

            StdOut.printf("Kevin Bacon Number: %10.0f\t\tFrequency: %d%n", baconNumber, frequencies.get(baconNumber));
        }
    }
}
