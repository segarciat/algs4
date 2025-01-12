package com.segarciat.algs4.ch4.sec1.ex08;

/**
 * <strong>4.1.8)</strong>
 * Implements the union-find structure discussed in Section 1.5,
 * based on {@link edu.princeton.cs.algs4.WeightedQuickUnionUF}.
 * The implementation is identical, but it adds a new method
 * {@link #count(int)} to determine the size of a given component.
 * @author Sergio E. Garcia Tapia
 */
public final class WeightedQuickUnionUF {
    private final int[] id;
    private final int[] sz;
    private int nComponents;

    public WeightedQuickUnionUF(int n) {
        if (n < 1)
            throw new IllegalArgumentException("must have 1 more or more sites");
        // all components start with the id of their only site
        nComponents = n;
        id = new int[n];
        for (int p = 0; p < n; p++)
            id[p] = p;

        // all components have one site
        sz = new int[n];
        for (int p = 0; p < n; p++)
            sz[p] = 1;
    }

    public int count() {
        return nComponents;
    }

    public int find(int p) {
        if (p < 0 || p >= id.length)
            throw new IllegalArgumentException("invalid site id");
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int idP = find(p);
        int idQ = find(q);
        if (idP == idQ) {
            return;
        }

        // merge smaller component (tree) into the larger one
        if (sz[idP] < sz[idQ]) {
            id[idP] = idQ;
            sz[idQ] += sz[idP];
        } else {
            id[idQ] = idP;
            sz[idP] += sz[idQ];
        }
        nComponents--;
    }

    public int count(int compId) {
        if (compId < 0 || compId >= nComponents)
            throw new IllegalArgumentException("invalid component id");
        return sz[compId];
    }
}
