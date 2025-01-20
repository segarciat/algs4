package com.segarciat.algs4.ch4.sec4.ex09;

import com.segarciat.algs4.ch4.sec4.ex07.DijkstraSP;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * <strong>4.4.9)</strong>
 * Finds an error in a map of shortest distances by using
 * {@link DijkstraSP} to compute a shortest path tree.
 * @author Sergio E. Garcia Tapia
 */
public final class Routes {
    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in.readInt());
        String[] towns = new String[G.V()];
        for (int v = 0; v < G.V(); v++) {
            towns[v] = in.readString();
            for (int w = 0; w < G.V(); w++) {
                double distance = in.readDouble();
                if (v != w) {
                    G.addEdge(new DirectedEdge(v, w, distance));
                    G.addEdge(new DirectedEdge(w, v, distance));
                }
            }
        }

        for (int v = 0; v < G.V(); v++) {
            DijkstraSP sp = new DijkstraSP(G, v);
            StdOut.printf("%15s: ", towns[v]);
            for (int w = 0; w < G.V(); w++) {
                if (v == w) {
                    StdOut.printf(" %6s ", "-");
                } else {
                    StdOut.printf(" %6.0f ", sp.distTo(w));
                }
            }
            StdOut.println();
        }
    }
}
