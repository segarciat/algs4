package com.segarciat.algs4.ch3.sec4.ex18;

import edu.princeton.cs.algs4.SequentialSearchST;

/**
 * <strong>Exercise 3.4.18</strong>
 * Adds a constructor {@link edu.princeton.cs.algs4.SeparateChainingHashST}
 * that allows the user to specify a tolerated average number of probes,
 * and resizes the array (to a prime-numbered size) to satisfy that constraint.
 * @author Sergio E. Garcia Tapia
 */
public final class SeparateChainingHashST <Key, Value> {
    private int m;
    private int n;
    private final int avgProbesTolerated;

    private static final int[] mersennePrimes = { 3, 7, 31, 127, 8191, 131071, 524287, 2147483647 };
    private SequentialSearchST<Key, Value>[] st;

    public SeparateChainingHashST(int m, int avgProbesTolerated) {
        this.avgProbesTolerated = avgProbesTolerated;
        this.m = smallestMersenne(m);

        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[this.m];
        for (int i = 0; i < st.length; i++)
            st[i] = new SequentialSearchST<>();
    }

    public SeparateChainingHashST(int m) {
        this(m, 0);
    }

    public SeparateChainingHashST() {
        this(997);
    }

    /**
     * Finds the smallest Mersenne prime greater than m to use
     * as a table size.
     */
    private static int smallestMersenne(int m) {
        // find the smallest Mersenne prime
        for (int prime: mersennePrimes) {
            if (prime >= m) {
                return prime;
            }
        }

        return mersennePrimes[mersennePrimes.length - 1];
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

    private void resize() {
        // Create a new table
        SeparateChainingHashST<Key, Value> temp = new SeparateChainingHashST<>(smallestMersenne(m));
        // Rehash all values
        for (int i = 0; i < m; i++) {
            for (Key key: st[i].keys()) {
                Value val = st[i].get(key);
                temp.put(key, val);
            }
        }
        m = temp.m;
        n = temp.n;
        st = temp.st;
    }

    public void put(Key key, Value val) {
        if (key == null)
            throw new NullPointerException("cannot use a null key");

        int h = hash(key);
        if (!st[h].contains(key)) {
            n++;
            if (avgProbesTolerated != 0 && n / m > avgProbesTolerated)
                resize();
        }
        st[h].put(key, val);
    }
}
