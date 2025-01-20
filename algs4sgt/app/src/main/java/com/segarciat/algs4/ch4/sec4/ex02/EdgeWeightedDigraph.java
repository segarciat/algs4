package com.segarciat.algs4.ch4.sec4.ex02;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * <strong>4.4.2)</strong>
 * Extends {@link edu.princeton.cs.algs4.EdgeWeightedDigraph} by
 * implementing an alternate constructor {@link #EdgeWeightedDigraph(In)}
 * and {@link #toString()}.
 * @author Sergio E. Garcia Tapia
 */
public final class EdgeWeightedDigraph {
    private final Bag<DirectedEdge>[] adj;
    private final int V;
    private int E;

    public EdgeWeightedDigraph(int V) {
        if (V <= 0)
            throw new IllegalArgumentException("invalid number of vertices");
        this.V = V;
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<>();
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
        validateVertex(e.to());
        adj[v].add(e);
        E++;
    }

    public Iterable<DirectedEdge> adj(int v) {
        return adj[validateVertex(v)];
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
