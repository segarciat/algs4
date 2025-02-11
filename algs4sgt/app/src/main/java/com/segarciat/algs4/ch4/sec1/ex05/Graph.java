package com.segarciat.algs4.ch4.sec1.ex05;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/**
 * <strong>4.1.5)</strong>
 * Extends the implementation in {@link edu.princeton.cs.algs4.Graph}
 * to disallow parallel edges and self-loops.
 * @author Sergio E. Garcia Tapia
 */
public final class Graph {
    private final Bag<Integer>[] adj;
    private final int V;
    private int E;

    public Graph(int V) {
        this.V = V;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<>();
    }

    public Graph(In in) {
        this(in.readInt());
        int expectedEdgeCount = in.readInt();
        for (int i = 0; i < expectedEdgeCount; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    public int V() {
        return V;
    }
    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        if (v == w)
            throw new IllegalArgumentException("self loops are not allowed");
        // could try selecting the smaller of the two adjacency lists
        for (int u: adj[v])
            if (u == w)
                throw new IllegalArgumentException("parallel edges are not allowed");
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        if (v < 0 | v >= V)
            throw new IllegalArgumentException("invalid vertex");
        return adj[v];
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(V).append(" vertices, ").append(E).append(" edges").append(System.lineSeparator());
        for (int v = 0; v < V; v++) {
            sb.append(v).append(": ");
            for (int w: this.adj(v))
                sb.append(w).append(" ");
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
