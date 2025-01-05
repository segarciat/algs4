package com.segarciat.algs4.ch3.sec4.ex19;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SequentialSearchST;

public final class SeparateChainingHashST <Key, Value> {
    private int m;
    private int n;

    private SequentialSearchST<Key, Value>[] st;

    public SeparateChainingHashST(int m) {
        this.m = m;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[this.m];
        for (int i = 0; i < st.length; i++)
            st[i] = new SequentialSearchST<>();
    }

    public SeparateChainingHashST() {
        this(997);
    }

    public int size() {
        return n;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7ffffff) % m;
    }

    public Value get(Key key) {
        if (key == null)
            return null;
        return st[hash(key)].get(key);
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public void put(Key key, Value val) {
        if (key == null)
            throw new NullPointerException("cannot use a null key");
        st[hash(key)].put(key, val);
    }

    public Iterable<Key> keys() {
        Queue<Key> keys = new Queue<>();

        for (int i = 0; i < m; i++)
            for (Key key: st[i].keys())
                keys.enqueue(key);

        return keys;
    }
}
