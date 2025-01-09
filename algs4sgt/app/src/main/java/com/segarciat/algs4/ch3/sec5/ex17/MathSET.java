package com.segarciat.algs4.ch3.sec5.ex17;

import edu.princeton.cs.algs4.LinearProbingHashST;

/**
 * <strong>3.5.17)</strong>
 * Implements a finite mathematical set.
 * @author Sergio E. Garcia Tapia
 */
public final class MathSET <Key> {
    LinearProbingHashST<Key, Integer> universe;
    LinearProbingHashST<Key, Integer> st;

    public MathSET(Key[] u) {
        if (u == null)
            throw new NullPointerException("cannot have a null universe");
        this.universe = new LinearProbingHashST<>();
        for (Key key: u)
            this.universe.put(key, 0);
    }

    private MathSET(LinearProbingHashST<Key, Integer> u) {
        universe = new LinearProbingHashST<>();
        for (Key key: u.keys())
            universe.put(key, 0);;
    }

    public int size() {
        return st.size();
    }

    public boolean isEmpty() {
        return st.isEmpty();
    }

    public boolean contains(Key key) {
        return st.contains(key);
    }

    public void delete(Key key) {
        st.delete(key);
    }

    public void add(Key key) {
        if (!universe.contains(key))
            throw new IllegalArgumentException("key does not belong to universe");
        st.put(key, 0);
    }

    public MathSET<Key> complement() {
        MathSET<Key> comp = new MathSET<>(universe);
        for (Key key: universe.keys()) {
            if (!this.st.contains(key))
                comp.add(key);
        }
        return comp;
    }

    public void union(MathSET<Key> a) {
        if (a == null)
            throw new NullPointerException("cannot union with a null set");
        for (Key key: a.st.keys())
            if (!this.st.contains(key))
                this.st.put(key, 0);
    }

    public void intersection(MathSET<Key> a) {
        if (a == null)
            throw new NullPointerException("cannot intersect with a null set");

        LinearProbingHashST<Key, Integer> temp = new LinearProbingHashST<>();
        for (Key key: this.st.keys()) {
            if (a.st.contains(key))
                temp.put(key, 0);
        }
        st = temp;
    }
}
