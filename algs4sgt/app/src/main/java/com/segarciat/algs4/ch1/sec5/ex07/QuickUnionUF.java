package com.segarciat.algs4.ch1.sec5.ex07;

public class QuickUnionUF implements UF {
    private final int[] id;
    private int count;

    public QuickUnionUF(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("number of components must be positive");
        count = n;

        // Add every site to their own component
        this.id = new int[n];
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

        // Navigate to the root of the tree
        while (p != id[p])
            p = id[p];

        return p;
    }

    @Override
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);

        // Already belong to the same component (tree)
        if (i == j)
            return;

        // Attach tree with root i to tree with root j
        id[i] = j;

        count--;
    }

    private void assertValidSite(int p) {
        if (p < 0 || p >= id.length)
            throw new IndexOutOfBoundsException("invalid site identifier");
    }
}
