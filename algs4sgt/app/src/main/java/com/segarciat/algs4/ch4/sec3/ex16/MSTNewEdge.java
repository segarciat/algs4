package com.segarciat.algs4.ch4.sec3.ex16;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.Stack;

/**
 * <strong>4.3.16)</strong>
 * Given the MST of an {@link EdgeWeightedGraph}, and an edge, determines
 * the maximum weight of the edge for which it may be in the MST.
 * @author Sergio E. Garcia Tapia
 */
public final class MSTNewEdge {
    public static double maxWeightForMST(Iterable<Edge> mst, Edge e) {
        if (mst == null || e == null)
            throw new NullPointerException();

        // Create graph from MST
        int V = 0;
        for (Edge edge: mst) // assumes edges are valid and indeed make up an MST.
            V++;
        if (e.either() < 0 || e.either() >= V || e.other(e.either()) < 0 || e.other(e.either()) >= V)
            throw new IllegalArgumentException("invalid edge");

        EdgeWeightedGraph G = new EdgeWeightedGraph(V);
        for (Edge edge: mst)
            G.addEdge(edge);

        // Find cycle after adding e to the MST
        G.addEdge(e);
        boolean[] marked = new boolean[V];
        Edge[] edgeTo = new Edge[V];
        Stack<Edge> cycle = new Stack<>();
        for (int s = 0; s < V && cycle.isEmpty(); s++)
            if (!marked[s])
                dfs(G, -1, s, marked, edgeTo, cycle);

        // Determine the largest weight of the cycle
        double maxCycleWeight = Double.NEGATIVE_INFINITY;
        for (Edge edge: cycle) {
            if (edge != e && edge.weight() > maxCycleWeight)
                maxCycleWeight = edge.weight();
        }
        return maxCycleWeight;
    }

    private static void dfs(EdgeWeightedGraph G, int u, int v, boolean[] marked, Edge[] edgeTo, Stack<Edge> cycle) {
        marked[v] = true;
        for (Edge e: G.adj(v)) {
            int w = e.other(v);
            if (!cycle.isEmpty())
                return;
            else if (!marked[w]) {
                edgeTo[w] = e;
                dfs(G, v, w, marked, edgeTo, cycle);
            } else if (u != w) {
                // v -> w
                cycle.push(e);
                int x = v;
                while (x != w) {
                    cycle.push(edgeTo[x]);
                    x = edgeTo[x].other(x);
                }
                return;
            }
        }
    }
}
