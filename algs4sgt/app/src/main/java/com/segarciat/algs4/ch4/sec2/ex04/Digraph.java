package com.segarciat.algs4.ch4.sec2.ex04;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/**
 * <strong>4.2.3)</strong>
 * Extends {@link edu.princeton.cs.algs4.Digraph} by implementing a method
 * {@link #hasEdge(int, int)}.
 * @author Sergio E. Garcia Tapia
 */
public final class Digraph {
    private final Bag<Integer>[] adj;
    private final int V;
    private int E;

    public Digraph(int V) {
        if (V <= 0)
            throw new IllegalArgumentException("invalid number of vertices");
        this.V = V;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<>();
    }
    public Digraph(In in) {
        this(in.readInt());
        int expectedEdgeCount = in.readInt();
        while (in.hasNextLine()) {
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

    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("invalid vertex");
    }

    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        adj[v].add(w);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        Bag<Integer> bag = new Bag<>();
        for (int w: adj[v])
            bag.add(w);
        return bag;
    }

    public Digraph reverse() {
        Digraph R = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w: adj[v]) {
                R.addEdge(w, v);
            }
        }
        return R;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(V).append(" vertices, and ")
                .append(E).append(" edges.")
                .append(System.lineSeparator());

        for (int v = 0; v < V; v++) {
            sb.append(v).append(":");
            for (int w: adj[v])
                sb.append(" ").append(w);
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        for (int u: adj[v])
            if (u == w)
                return true;
        return false;
    }
}
