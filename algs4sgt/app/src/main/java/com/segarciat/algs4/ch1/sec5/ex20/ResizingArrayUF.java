package com.segarciat.algs4.ch1.sec5.ex20;

import com.segarciat.algs4.ch1.sec5.UF;

import java.util.Arrays;

/**
 * @author Sergio E. Garcia Tapia
 */
public class ResizingArrayUF implements UF {
    private int[] id;
    private int[] sz;
    private int count;
    private int sites;

    public ResizingArrayUF() {
        id = new int[2];
        for (int i = 0; i < id.length; i++)
            id[i] = i;

        sz = new int[2];
        Arrays.fill(sz, 1);
    }

    public int newSite() {
        if (sites >= id.length)
            resize(2 * sites);
        id[sites] = sites;
        sz[sites] = 1;
        return ++sites;
    }

    @Override
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public int find(int p) {
        assertValidSite(p);

        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    @Override
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);

        if (rootP == rootQ)
            return;
        if (sz[rootP] < sz[rootQ]) {
            id[rootP] = rootQ;
            sz[rootQ] += sz[rootP];
        } else {
            id[rootQ] = rootP;
            sz[rootP] = rootQ;
        }
        ++count;
    }

    @Override
    public int count() {
        return count;
    }

    private void assertValidSite(int p) {
        if (p < 0 || p >= id.length)
            throw new IllegalArgumentException("invalid site identifier");
    }

    private void resize(int max)  {
        int[] tempId = new int[max];
        int[] tempSz = new int[max];
        for (int i = 0; i < sites; i++) {
            tempId[i] = id[i];
            tempSz[i] = sz[i];
        }
        id = tempId;
        sz = tempSz;
    }
}
