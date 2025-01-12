package com.segarciat.algs4.ch4.sec1.ex10;

import edu.princeton.cs.algs4.Graph;

/**
 * <strong>4.1.10)</strong>
 * Given a connected graph, uses depth-first search (DFS) to
 * find a vertex that, if removed (along with any edges
 * incident to or from it), would leave the graph connected.
 * @author Sergio E. Garcia Tapia
 */
public final class RemoveVertex {
    private final boolean[] marked;
    private int count;
    private int removedVertex;
    public RemoveVertex(Graph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        count++;
        boolean allMarked = true;
        for (int w: G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
                allMarked = false;
            }
        }
        if (allMarked) {
            removedVertex = v;
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }

    public int count() {
        return count;
    }

    /**
     *
     * @return A vertex in G that can be removed from G while keeping the
     * graph connected, or if the graph is not connected,
     * from the connected component of G containing the source vertex <code>s</code>.
     */
    public int removedVertex() {
        return removedVertex;
    }
}
