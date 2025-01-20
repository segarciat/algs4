package com.segarciat.algs4.ch4.sec4.ex08;

import edu.princeton.cs.algs4.DijkstraSP;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * <strong>4.4.8)</strong>
 * Computes the diameter of an edge-weighted digraph.
 * @author Sergio E. Garcia Tapia
 */
public final class DiameterEWD {
    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
        // Verify weights are non-negative
        for (DirectedEdge e: G.edges()) {
            if (e.weight() < 0) {
                StdOut.println("Digraph must have non-negative weights.");
                return;
            }
        }

        double diameter = Double.NEGATIVE_INFINITY;
        for (int v = 0; v < G.V(); v++) {
            DijkstraSP sp = new DijkstraSP(G, v);
            for (int w = 0; w < G.V(); w++)
                if (sp.hasPathTo(w) && sp.distTo(w) > diameter)
                    diameter = sp.distTo(w);
        }
        StdOut.printf("Diameter: %f%n", diameter);
    }
}
