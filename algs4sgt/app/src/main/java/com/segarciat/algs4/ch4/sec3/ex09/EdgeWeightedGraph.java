package com.segarciat.algs4.ch4.sec3.ex09;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * <strong>4.3.9)</strong>
 * Implements a constructor {@link #EdgeWeightedGraph(In)} for
 * {@link edu.princeton.cs.algs4.EdgeWeightedGraph} that reads an
 * edge-weighted graph from the input stream.
 * @author Sergio E. Garcia Tapia
 */
public final class EdgeWeightedGraph {
    private final Bag<Edge>[] adj;
    private final int V;
    private int E;

    public EdgeWeightedGraph(int V) {
        if (V <= 0)
            throw new IllegalArgumentException("invalid number of vertices");
        this.V = V;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<>();
    }

    public EdgeWeightedGraph(In in) {
        this(in.readInt());
        E = in.readInt();
        while (!in.isEmpty()) {
            int v = validateVertex(in.readInt());
            int w = validateVertex(in.readInt());
            double weight = in.readDouble();
            addEdge(new Edge(v, w, weight));
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

    public void addEdge(Edge e) {
        if (e == null)
            throw new NullPointerException("cannot use a null edge");

        int v = validateVertex(e.either());
        int w = validateVertex(e.other(v));
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public Iterable<Edge> adj(int v) {
        return adj[validateVertex(v)];
    }

    public Iterable<Edge> edges() {
        Bag<Edge> edges = new Bag<>();
        for (int v = 0; v < V; v++)
            for (Edge e: adj[v])
                if (e.other(v) > v)
                    edges.add(e);
        return edges;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(V).append(" vertices and ").append(E).append(" edges.");
        for (int v = 0; v < V; v++) {
            sb.append(v).append(":");
            for (Edge e: adj[v])
                sb.append(" ").append(e);
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }



    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        StdOut.println(G);
    }
}
