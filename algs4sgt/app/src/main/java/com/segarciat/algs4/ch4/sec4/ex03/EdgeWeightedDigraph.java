package com.segarciat.algs4.ch4.sec4.ex03;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * <strong>4.4.3)</strong>
 * Extends {@link edu.princeton.cs.algs4.EdgeWeightedDigraph} by
 * using an adjacency-matrix representation for the edges suitable
 * for dense graphs and disallowing parallel edges.
 * @author Sergio E. Garcia Tapia
 */
public final class EdgeWeightedDigraph {
    private final DirectedEdge[][] adj;
    private final int V;
    private int E;

    public EdgeWeightedDigraph(int V) {
        if (V <= 0)
            throw new IllegalArgumentException("invalid number of vertices");
        this.V = V;
        adj = new DirectedEdge[V][V];
    }

    public EdgeWeightedDigraph(In in) {
        this(in.readInt());
        int fileEdgeCount = in.readInt();
        for (int i = 0; i < fileEdgeCount; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            addEdge(new DirectedEdge(v, w, weight));
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    private int validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("invalid vertex");
        return v;
    }

    public void addEdge(DirectedEdge e) {
        if (e == null)
            throw new NullPointerException("cannot add a null edge");

        int v = validateVertex(e.from());
        int w = validateVertex(e.to());
        if (adj[v][w] != null)
            throw new IllegalArgumentException("parallel edges are not allowed");

        adj[v][w] = e;
        E++;
    }

    public Iterable<DirectedEdge> adj(int v) {
        validateVertex(v);
        Bag<DirectedEdge> adjacentEdges = new Bag<>();
        for (DirectedEdge e: adj[v])
            adjacentEdges.add(e);
        return adjacentEdges;
    }

    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> allEdges = new Bag<>();
        for (int v = 0; v < V; v++)
            for (DirectedEdge e: adj[v])
                allEdges.add(e);
        return allEdges;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(V).append(" vertices and ").append(E).append( " edges.").append(System.lineSeparator());
        for (int v = 0; v < V; v++) {
            sb.append(v).append(":");
            for (DirectedEdge e : adj[v])
                if (e != null)
                    sb.append(" ").append(e);
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
        StdOut.println(G);
    }
}
