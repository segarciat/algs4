package com.segarciat.algs4.ch3.sec5.ex08;

import edu.princeton.cs.algs4.Queue;

/**
 * <strong>3.5.8</strong>
 * Builds on {@link edu.princeton.cs.algs4.LinearProbingHashST} by
 * allowing duplicate keys. In particular, any key is returned
 * by a get call, and all keys are removed by a delete() call.
 * @author Sergio E. Garcia Tapia
 */
public final class LinearProbingHashST <Key, Value> {
    private int m = 16;
    private int n;
    private Key[] keys;
    private Queue<Value>[] vals;

    public LinearProbingHashST(int cap) {
        keys = (Key[]) new Object[cap];
        vals = (Queue<Value>[]) new Queue[cap];
        for (int i = 0; i < cap; i++)
            vals[i] = new Queue<>();
        this.m = cap;
    }

    public LinearProbingHashST() {
        this(16);
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

        for (int i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (key.equals(keys[i])) {
                return vals[i].peek();
            }
        }
        return null;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    private void resize(int cap) {
        LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<>(cap);
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) {
                for (Value val: vals[i]) {
                    temp.put(keys[i], val);
                }
            }
        }

        keys = temp.keys;
        vals = temp.vals;
        m = temp.m;
    }

    public void put(Key key, Value val) {
        if (key == null)
            throw new NullPointerException("cannot insert a null key");

        if (n >= m / 2)
            resize(2 * m);
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (key.equals(keys[i])) {
                vals[i].enqueue(val);
                return;
            }
        }
        keys[i] = key;
        vals[i].enqueue(val);
        n++;
    }

    public void delete(Key key) {
        if (key == null)
            throw new NullPointerException("cannot delete a null key");

        if (!contains(key))
            return;

        int i = hash(key);
        while (!key.equals(keys[i]))
            i = (i + 1) % m;
        keys[i] = null;
        vals[i] = new Queue<>(); // removes all associated values
        i = (i + 1) % m;
        while (keys[i] != null) {
            Key keyToRedo = keys[i];
            Queue<Value> valsToRedo = vals[i];
            keys[i] = null;
            vals[i] = null;
            n--;
            for (Value val: valsToRedo)
                put(keyToRedo, val);
            i = (i + 1) % m;
        }
        n--;
        if (n > 0 && n <= m / 8)
            resize(m / 2);
    }
}
