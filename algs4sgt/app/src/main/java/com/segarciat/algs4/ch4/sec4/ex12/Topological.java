package com.segarciat.algs4.ch4.sec4.ex12;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.Stack;

/**
 * <strong>4.4.12)</strong>
 * Adapts the implementation of {@link edu.princeton.cs.algs4.Topological} to
 * work with edge-weighted digraphs.
 * @author Sergio E. Garcia Tapia
 */
public final class Topological {
    private Stack<Integer> reversePostOrder;
    public Topological(EdgeWeightedDigraph G) {
        if (G == null)
            throw new NullPointerException("G cannot be null");

        EdgeWeightedDirectedCycle cycleFinder = new EdgeWeightedDirectedCycle(G);
        if (!cycleFinder.hasCycle()) {
            reversePostOrder = new Stack<>();
            boolean[] marked = new boolean[G.V()];
            for (int s = 0; s < G.V(); s++)
                if (!marked[s])
                    dfs(G, s, marked);
        }
    }

    private void dfs(EdgeWeightedDigraph G, int v, boolean[] marked) {
        marked[v] = true;
        for (DirectedEdge e: G.adj(v)) {
            int w = e.to();
            if (!marked[w]) {
                dfs(G, w, marked);
            }
        }
        reversePostOrder.push(v);
    }

    public boolean hashOrder() {
        return reversePostOrder != null;
    }

    public Iterable<Integer> order() {
        return reversePostOrder;
    }
}
