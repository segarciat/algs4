package com.segarciat.algs4.ch1.sec5.ex22;

import com.segarciat.algs4.ch1.sec5.ex17.ErdosRenyi;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * @author Sergio E. Garcia Tapia
 */
public class DoublingTestErdosRenyi {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Provide command-line argument: T");
            System.err.println("T    The number of trials");
        }
        int trials = Integer.parseInt(args[0]);
        if (trials <= 0) {
            System.err.println("Number of trials must be positive");
            System.exit(1);
        }

        double t0 = 0.0;
        for (int n = 256; true; n *= 2) {
            double avgConnections = 0.0;
            double t1 = 0.0;
            for (int t = 0; t < trials; t++) {
                Stopwatch timer = new Stopwatch();
                long connections = ErdosRenyi.count(n);
                t1 += timer.elapsedTime() / trials;
                avgConnections += (double) connections / trials;
            }
            System.out.printf("n=%d, connections=%.1f, ratio=%.1f%n",
                    n, avgConnections, t0 == 0.0? Double.POSITIVE_INFINITY: t1 / t0);
            t0 = t1;
        }
    }
}
