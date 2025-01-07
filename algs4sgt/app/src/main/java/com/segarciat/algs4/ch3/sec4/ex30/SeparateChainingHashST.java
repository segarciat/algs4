package com.segarciat.algs4.ch3.sec4.ex30;

import edu.princeton.cs.algs4.SequentialSearchST;

/**
 * <strong>3.4.30</strong>
 * Implements {@link #chiSquaredStatistic()}, which computes the
 * ChiSquared statistic for the hash table.
 * @author Sergio E. Garcia Tapia
 */
public final class SeparateChainingHashST <Key, Value> {
    private int m;
    private int n;
    private SequentialSearchST<Key, Value>[] st;

    public SeparateChainingHashST(int m) {
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
        for (int i = 0; i < m; i++)
            st[i] = new SequentialSearchST<>();
        this.m = m;
    }

    public SeparateChainingHashST() {
        this(997);
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7ffffff) % m;
    }

    public Value get(Key key) {
        if (key == null)
            return null;
        return st[hash(key)].get(key);
    }

    private void resize(int cap) {
        SeparateChainingHashST<Key, Value> temp = new SeparateChainingHashST<>(cap);
        for (int i = 0; i < m; i++) {
            for (Key key: st[i].keys()) {
                temp.put(key, st[i].get(key));
            }
        }

        st = temp.st;
        n = temp.n;
        m = temp.m;
    }

    public void put(Key key, Value val) {
        if (key == null)
            throw new NullPointerException("cannot use a null key");
        if (n >= 8 * m)
            resize(2 * m);
        st[hash(key)].put(key, val);
        n++;
    }

    public void delete(Key key) {
        if (key == null)
            throw new NullPointerException("cannot use a null key");
        st[hash(key)].delete(key);
        n--;
        if (n > 0 && n <= 2 * m)
            resize(m / 2);
    }

    public double chiSquaredStatistic() {
        if (n == 0.0)
            return 0.0;

        double alpha = (n + 0.0) / m;
        double acc = 0.0;
        for (int i = 0; i < m; i++) {
            acc += Math.pow((st[i].size() - alpha), 2);
        }

        return acc * (1.0 / alpha);
    }
}
