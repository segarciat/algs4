package com.segarciat.algs4.ch3.sec4.ex36;

import edu.princeton.cs.algs4.SequentialSearchST;
import edu.princeton.cs.algs4.StdRandom;

/**
 * <strong>3.4.36</strong>
 * @author Sergio E. Garcia Tapia
 */
public final class SeparateChainingHashST<Key, Value> {
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

    public void put(Key key, Value val) {
        if (key == null)
            throw new NullPointerException("cannot use a null key");
        st[hash(key)].put(key, val);
        n++;
    }

    public void delete(Key key) {
        if (key == null)
            throw new NullPointerException("cannot use a null key");
        st[hash(key)].delete(key);
        n--;
    }

    public static void main(String[] args) {
        for (int n = 1_000; n <= 1_000_000; n *= 10) {
            SeparateChainingHashST<Integer, Integer> table = new SeparateChainingHashST<>(n / 100);
            for (int i = 0; i < n; i++) {
                int r = StdRandom.uniformInt(Integer.MAX_VALUE);
                table.put(r, r);
            }

            int longest = table.st[0].size();
            for (int i = 0; i < table.st.length; i++) {
                if (table.st[i].size() > longest) {
                    longest = table.st[i].size();
                }
            }

            System.out.printf("n = %7d, longest=%d%n", n, longest);
        }
    }
}
