package com.segarciat.algs4.ch1.sec5.ex14;

import com.segarciat.algs4.ch1.sec5.UF;

import java.util.Arrays;

public class WeightedQuickUnionH implements UF {
    /**
     * Defines a forest of trees, where id[p] == p means that p is the root.
     */
    private final int[] id;
    /**
     * Keeps track of the height of all trees maintained by <code>id[]</code>
     */
    private final int[] ht;
    private int count;

    public WeightedQuickUnionH(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("must have at least 1 site");
        count = n;
        id = new int[n];
        for (int i = 0; i < id.length; i++)
            id[i] = i;
        ht = new int[n];
        Arrays.fill(ht, 0);
    }

    @Override
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public int find(int p) {
        assertValidSite(p);

        while (p != id[p])
            p = id[p];
        return p;
    }

    @Override
    public void union(int p, int q) {
        int pID = find(p);
        int qID = find(q);

        if (pID == qID)
            return;

        if (ht[pID] < ht[qID]) { // height of larger tree is unchanged
            id[pID] = qID;
        } else {
            id[qID] = pID;
            // height changes only if trees are equal height.
            ht[pID] += (ht[pID] == ht[qID]) ? 1 : 0;
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
