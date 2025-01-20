package com.segarciat.algs4.ch4.sec4.ex12;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.Stack;

/**
 * <strong>4.4.12)</strong>
 * Adapts the implementation of {@link edu.princeton.cs.algs4.DirectedCycle}
 * to work with edge-weighted digraphs.
 * @author Sergio E. Garcia Tapia
 */
public final class EdgeWeightedDirectedCycle {
    private Stack<DirectedEdge> cycle;
    private final boolean[] onStack;
    private final boolean[] marked;
    private final DirectedEdge[] edgeTo;

    public EdgeWeightedDirectedCycle(EdgeWeightedDigraph G) {
        onStack = new boolean[G.V()];
        marked = new boolean[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        for (int s = 0; s < G.V(); s++)
            if (!marked[s] && !hasCycle())
                dfs(G, s);
    }

    private void dfs(EdgeWeightedDigraph G, int v) {
        marked[v] = true;
        onStack[v] = true;
        for (DirectedEdge e: G.adj(v)) {
            if (hasCycle())
                return;
            int w = e.from();
            if (!marked[w]) {
                edgeTo[w] = e;
                dfs(G, v);
            } else if (onStack[w]) {
                cycle = new Stack<>();
                while (e.from() != w) {
                    cycle.push(e);
                    e = edgeTo[e.from()];
                }
                cycle.push(e);
                return;
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<DirectedEdge> cycle() {
        return cycle;
    }
}
