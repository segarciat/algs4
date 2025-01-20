package com.segarciat.algs4.ch4.sec4.ex07;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * <strong>4.4.7)</strong>
 * Modifies {@link edu.princeton.cs.algs4.DijkstraSP} to implement
 * a method {@link #secondPathTo(int)} to return a <em>second</em>
 * shortest path, if one exists.
 * @author Sergio E. Garcia Tapia
 */
public final class DijkstraSP {
    private static final double EPSILON = 1e-15;
    private final DirectedEdge[] edgeTo;
    private final DirectedEdge[] secondEdgeTo;
    private final double[] distTo;
    private final IndexMinPQ<Double> pq;

    public DijkstraSP(EdgeWeightedDigraph G, int s) {
        if (G == null)
            throw new NullPointerException("graph cannot be null");
        if (s < 0 || s >= G.V())
            throw new IllegalArgumentException("invalid source vertex");

        edgeTo = new DirectedEdge[G.V()];
        secondEdgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        pq = new IndexMinPQ<>(G.V());
        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;
        pq.insert(s, 0.0);
        while (!pq.isEmpty())
            relax(G, pq.delMin());
    }

    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e: G.adj(v)) {
            int w = e.to();
            if (distTo[w] != Double.POSITIVE_INFINITY && Math.abs(distTo[w] - (distTo[v] + e.weight())) <= EPSILON) {
                secondEdgeTo[w] = e;
            }
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                secondEdgeTo[w] = e;
                if (pq.contains(w)) {
                    pq.changeKey(w, distTo[w]);
                } else {
                    pq.insert(w, distTo[w]);
                }
            }

        }
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= edgeTo.length)
            throw new IllegalArgumentException("invalid ");
    }

    public double distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        validateVertex(v);
        return distTo[v] != Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) {
            return null;
        }

        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
            path.push(e);
        return path;
    }

    public Iterable<DirectedEdge> secondPathTo(int v) {
        if (!hasPathTo(v)) return null;

        Stack<DirectedEdge> secondPath = new Stack<>();
        DirectedEdge f = edgeTo[v];
        boolean hasSecondPath = false;
        for (DirectedEdge e = secondEdgeTo[v]; e != null; e = secondEdgeTo[e.from()]) {
            hasSecondPath = hasSecondPath || (e != f);
            secondPath.push(e);
            if (f != null)
                f = edgeTo[f.from()];
        }

        return (hasSecondPath) ? secondPath : null;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
        int s = Integer.parseInt(args[1]);
        DijkstraSP sp = new DijkstraSP(G, s);
        for (int t = 0; t < G.V(); t++) {
            StdOut.print(s + " to " + t);
            if (!sp.hasPathTo(t)) {
                StdOut.println(" (INF) : No path exists.");
                continue;
            }
            StdOut.printf(" (%4.2f): ", sp.distTo(t));
            for (DirectedEdge e : sp.pathTo(t)) {
                StdOut.print(e + "   ");
            }
            StdOut.println();
            Iterable<DirectedEdge> secondPath = sp.secondPathTo(t);
            if (secondPath == null)
                continue;
            StdOut.print(s + " to " + t);
            StdOut.printf(" (%4.2f): ", sp.distTo(t));
            for (DirectedEdge e : secondPath) {
                StdOut.print(e + "   ");
            }
            StdOut.println();
        }
    }
}
