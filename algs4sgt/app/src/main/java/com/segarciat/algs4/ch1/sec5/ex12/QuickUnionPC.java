package com.segarciat.algs4.ch1.sec5.ex12;

import com.segarciat.algs4.ch1.sec5.UF;

/**
 * @author Sergio E. Garcia Tapia
 * Implements the {@link UF} API to solve the dynamic connectivity
 * problem by using quick-union with path compression.
 */
public class QuickUnionPC implements UF {
    private int count;
    private final int[] id;

    public QuickUnionPC(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("must have at least 1 site");

        count = n;
        id = new int[n];
        for (int i = 0; i < id.length; i++)
            id[i] = i;
    }

    @Override
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public int find(int p) {
        assertValidSite(p);

        // Find the root
        int root = p;
        while (root != id[root]) {
            root = id[root];
        }

        // Rename all sites on the path from p to the root.
        while (p != root) {
            int q = id[p];
            id[p] = root;
            p = q;
        }

        return root;
    }

    @Override
    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);

        if (i == j)
            return;

        id[i] = j;
        count--;
    }

    @Override
    public int count() {
        return count;
    }

    private void assertValidSite(int p) {
        if (p < 0 || p >= id.length)
            throw new IndexOutOfBoundsException("invalid site identifier");
    }
}
