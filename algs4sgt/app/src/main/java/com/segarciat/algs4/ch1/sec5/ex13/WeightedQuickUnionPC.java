package com.segarciat.algs4.ch1.sec5.ex13;

import com.segarciat.algs4.ch1.sec5.UF;

import java.util.Arrays;

/**
 * Implements the {@link UF} API to solve the dynamic connectivity
 * problem by using weighted quick-union with path compression.
 */
public class WeightedQuickUnionPC implements UF {
    private int count;
    private final int[] id;
    private final int[] sz;

    public WeightedQuickUnionPC(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("must have at least 1 site");

        count = n;
        id = new int[n];
        for (int i = 0; i < id.length; i++)
            id[i] = i;
        sz = new int[n];
        Arrays.fill(sz, 1);
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

        if (sz[i] < sz[j]) {
            sz[j] += sz[i];
            id[i] = id[j];
        } else {
            sz[i] += sz[j];
            id[j] = id[i];
        }
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
