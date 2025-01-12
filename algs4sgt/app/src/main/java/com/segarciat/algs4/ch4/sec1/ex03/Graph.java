package com.segarciat.algs4.ch4.sec1.ex03;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/**
 * <strong>4.1.3)</strong>
 * Extends {@link edu.princeton.cs.algs4.Graph} by adding
 * a copy-constructor {@link #Graph(Graph)}. The remaining
 * method implementations are as given in the text.
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
        E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    public Graph(Graph G) {
        this(G.V());
        this.E = G.E;
        for (int v = 0; v < V; v++)
            for (int w: G.adj[v])
                this.adj[v].add(w);
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
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
}
