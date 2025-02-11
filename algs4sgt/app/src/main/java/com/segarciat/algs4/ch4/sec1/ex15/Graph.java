package com.segarciat.algs4.ch4.sec1.ex15;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/**
 * <strong>4.1.5)</strong>
 * Modifies {@link edu.princeton.cs.algs4.Graph}, specifically the
 * {@link #Graph(In)} constructor, to require adjacency lists to
 * be given in each line for each vertex.
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
        while (!in.isEmpty()) {
            int v = in.readInt();
            String[] vertices = in.readLine().strip().split("\\s+");
            for (String adjW: vertices) {
                int w = Integer.parseInt(adjW);
                addEdge(v, w);
            }
        }
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

    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        System.out.println(G);
    }
}
