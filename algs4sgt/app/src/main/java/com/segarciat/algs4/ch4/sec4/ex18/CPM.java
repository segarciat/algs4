package com.segarciat.algs4.ch4.sec4.ex18;

import edu.princeton.cs.algs4.AcyclicLP;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * <strong>4.4.18)</strong>
 * Using the {@link edu.princeton.cs.algs4.CPM} class given in the text,
 * extends it to actually display the critical paths.
 * @author Sergio E. Garcia Tapia
 */
public final class CPM {
    public static void main(String[] args) {
        int n = StdIn.readInt();
        StdIn.readLine();

        // n jobs, each has start an end; plus two for source and sink
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(2 * n + 2);

        // source and sink
        int s = 2 * n;
        int t = 2 * n + 1;
        for (int i = 0; i < n; i++) {
            String[] a = StdIn.readLine().strip().split("\\s+");
            double duration = Double.parseDouble(a[0]);
            // job start to job end
            G.addEdge(new DirectedEdge(i, i + n, duration));
            // source to job start
            G.addEdge(new DirectedEdge(s, i, 0.0));
            // job end to sink
            G.addEdge(new DirectedEdge(i + n, t, 0.0));
            for (int j = 2; j < a.length; j++) {
                int successor = Integer.parseInt(a[j]);
                G.addEdge(new DirectedEdge(i + n, successor, 0.0));
            }
        }

        AcyclicLP lp = new AcyclicLP(G, s);

        for (int i = 0; i < n; i++)
            StdOut.printf("%4d: %5.1f\n", i, lp.distTo(i));
        StdOut.printf("Finish time: %5.1f\n", lp.distTo(t));

        StdOut.println("Critical paths: ");
        double criticalPathLength = lp.distTo(t);
        for (int i = 0; i < n; i++) {
            double length = lp.distTo(i + n);
            if (length >= criticalPathLength) {
                for (DirectedEdge e: lp.pathTo(i + n)) {
                    if (e.from() < n)
                        StdOut.printf("%d (%.1f) -> ", e.from(), e.weight());
                }
                StdOut.println();
            }

        }
    }
}
