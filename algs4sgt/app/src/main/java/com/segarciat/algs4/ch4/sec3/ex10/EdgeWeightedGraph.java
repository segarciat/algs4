package com.segarciat.algs4.ch4.sec3.ex10;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * <strong>4.3.10)</strong>
 * Implements a version of {@link edu.princeton.cs.algs4.EdgeWeightedGraph}
 * for dense graphs, using an adjacency matrix representation instead of
 * adjacency lists.
 * @author Sergio E. Garcia Tapia
 */
public final class EdgeWeightedGraph {
    private final Edge[][] adj;
    private final int V;
    private int E;

    public EdgeWeightedGraph(int V) {
        if (V <= 0)
            throw new IllegalArgumentException("invalid number of vertices");
        this.V = V;
        adj = new Edge[V][V];
    }

    public EdgeWeightedGraph(In in) {
        this(in.readInt());
        int fileEdgeCount = in.readInt();
        for (int i = 0; i < fileEdgeCount; i++) {
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
        if (adj[v][w] != null)
            throw new IllegalArgumentException("parallel edges are not allowed");
        adj[v][w] = e;
        adj[w][v] = e;
        E++;
    }

    public Iterable<Edge> adj(int v) {
        validateVertex(v);
        Bag<Edge> adjEdges= new Bag<>();
        for (Edge e: adj[v])
            if (e != null)
                adjEdges.add(e);
        return adjEdges;
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
        sb.append(V).append(" vertices and ").append(E).append(" edges.").append(System.lineSeparator());
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
