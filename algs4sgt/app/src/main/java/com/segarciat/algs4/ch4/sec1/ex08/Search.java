package com.segarciat.algs4.ch4.sec1.ex08;

import edu.princeton.cs.algs4.Graph;

/**
 * <strong>4.1.8</strong>
 * Implements the <code>Search</code> API on page 528, but
 * using a union-find structure instead of Depth First Search.
 * @author Sergio E. Garcia Tapia
 */
public final class Search {
    private final WeightedQuickUnionUF uf;
    private final int sComponent;
    public Search(Graph G, int s) {
        uf = new WeightedQuickUnionUF(G.V());
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                uf.union(v, w);
            }
        }
        sComponent = uf.find(s);
    }

    /**
     * Determines whether <code>v</code> is reachable from the source vertex<code>s</code>
     * used to build this object.
     * @param v A valid vertex in the <code>Graph</code> used to build this object.
     * @return <code>true</code> if <code>v</code> and <code>s</code> are
     * in the same connected component, <code>false</code> otherwise.
     */
    public boolean marked(int v) {
        return sComponent == uf.find(v);
    }

    /**
     * @return The number of vertices reachable from the source vertex <code>s</code>.
     */
    public int count() {
        return uf.count(sComponent);
    }
}
