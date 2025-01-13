package com.segarciat.algs4.ch4.sec1.ex29;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.SET;

/**
 * <strong>4.1.29)</strong>
 * Extends {@link edu.princeton.cs.algs4.Cycle} so that it works with
 * graphs that have self-loops and paralell edges.
 * @author Sergio E. Garcia Tapia
 */
public final class Cycle {
    private final boolean[] marked;
    private boolean hasCycle;

    public Cycle(Graph G) {
        marked = new boolean[G.V()];
        for (int s = 0; s < G.V(); s++)
            if (!marked[s])
                dfs(G, -1, s);
    }

    private void dfs(Graph G, int u, int v) {
        marked[v] = true;
        // used to ensure it works for self-loops and parallel edges.
        SET<Integer> adjacentVertices = new SET<>();
        for (int w: G.adj(v)) {
            if (w != v)
                adjacentVertices.add(w);
        }
        for (int w: adjacentVertices) {
            if (!marked[w]) {
                dfs(G, v, w);
            } else if (w != u) {
                hasCycle = true;
            }
        }
    }


    public boolean hasCycle() {
        return hasCycle;
    }
}
