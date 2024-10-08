package com.segarciat.algs4.ch1.sec5.ex07;

import com.segarciat.algs4.ch1.sec5.UF;

public class QuickFindUF implements UF {
    private final int[] id;
    private int count;

    public QuickFindUF(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("Must have at least one site");
        count = n;
        id = new int[n];
        // Make each site its own component.
        for (int i = 0; i < n; i++)
            id[i] = i;
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public int find(int p) {
        assertValidSite(p);
        return id[p];
    }

    @Override
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void union(int p, int q) {
        int pID = find(p);
        int qID = find(q);

        // Already in the same component.
        if (pID == qID)
            return;
        // Move all sites from component pID to component qID.
        for (int i = 0; i < id.length; i++)
            if (id[i] == pID)
                id[i] = qID;
        count--;
    }

    private void assertValidSite(int p) {
        if (p < 0 || p >= id.length)
            throw new IndexOutOfBoundsException("invalid site identifier");
    }
}
