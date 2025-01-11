package com.segarciat.algs4.ch4.sec1.ex04;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/**
 * <strong>4.1.4)</strong>
 * Extends {@link edu.princeton.cs.algs4.Graph} by adding a method
 * {@link #hasEdge(int, int)} that checks whether an <code>v-w</code>
 * exists.
 * @author Sergio E. Garcia Tapia
 */
public final class Graph {
    private final Bag<Integer>[] adj;
    private final int V;
    private int E;

    public int E() {
        return E;
    }

    public Graph(int V) {
        this.V = V;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<>();
    }

    public Graph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    public int V() {
        return V;
    }

    public void addEdge(int v, int w) {
        if (v < 0 || w < 0 || v >= V || w >= V)
            throw new IllegalArgumentException("invalid vertex");
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

    public boolean hasEdge(int v, int w) {
        if (v < 0 || w < 0 || v >= V || w >= V)
            throw new IllegalArgumentException("invalid vertex");
        for (int u: adj[v])
            if (u == w)
                return true;
        return false;
    }
}
