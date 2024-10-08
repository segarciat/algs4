package com.segarciat.algs4.ch1.sec5.ex11;

import com.segarciat.algs4.ch1.sec5.UF;

import java.util.Arrays;

/**
 * Implements the {@link UF} API to solve the dynamic connectivity problem
 * by using weighted quick find, where all components in the smaller tree
 * are renamed to the identifier of the larger tree.
 */
public class WeightedQuickFind implements UF {
    private int count;
    private final int[] id;
    private final int[] sz;

    public WeightedQuickFind(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("must have at least 1 component");
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
        return id[p];
    }

    @Override
    public void union(int p, int q) {
        int pID = find(p);
        int qID = find(q);

        if (pID == qID)
            return;
        if (sz[pID] < sz[qID]) {
            sz[qID] += sz[pID];
            for (int i = 0; i < id.length; i++)
                if (id[i] == pID)
                    id[i] = qID;
        } else {
            sz[pID] += sz[qID];
            for (int i = 0; i < id.length; i++)
                if (id[i] == qID)
                    id[i] = pID;
        }
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
