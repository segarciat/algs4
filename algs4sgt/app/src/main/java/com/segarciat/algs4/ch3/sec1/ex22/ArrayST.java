package com.segarciat.algs4.ch3.sec1.ex22;

import edu.princeton.cs.algs4.Queue;

import java.util.NoSuchElementException;

/**
 * <strong>3.1.22)</strong>
 * An implementation of an unordered symbol table using arrays,
 * based on {@link com.segarciat.algs4.ch3.sec1.ex02.ArrayST}, except
 * that it implements the <em>move-to-front</em> heuristic as requested
 * in this exercise.
 * @author Sergio E. Garcia Tapia
 */
public final class ArrayST<Key, Value>{
    private Key[] keys;
    private Value[] vals;
    private int n = 0;

    public ArrayST() {
        keys = (Key[]) new Object[2];
        vals = (Value[]) new Object[2];
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Value get(Key key) {
        if (key == null)
            return null;

    int i;
        for (i = 0; i < n; i++) {
            if (key.equals(keys[i]))
                break;
        }

        // search miss
        if (i == n)
            return null;

        // search hit
        Key k = keys[i];
        Value v = vals[i];

        // move found key to front
        while (i > 0) {
            vals[i] = vals[i - 1];
            keys[i] = keys[i - 1];
            i--;
        }
        keys[0] = k;
        vals[0] = v;

        return v;
    }

    public void put(Key key, Value val) {
        if (key == null)
            throw new NullPointerException("cannot add a null key");
        if (val == null) {
            delete(key);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (key.equals(keys[i])) {
                vals[i] = val;
                return;
            }
        }
        // If we reach this point, the key does not exist
        if (n == keys.length)
            resize(2 * n);
        keys[n] = key;
        vals[n] = val;
        n++;
    }

    public void delete(Key key) {
        if (key == null)
            throw new NullPointerException("cannot delete a null key");
        if (isEmpty())
            throw new NoSuchElementException("the symbol table is empty");
        int i;
        for (i = 0; i < n; i++) {
            if (keys.equals(keys[i]))
                break;
        }

        // Not in symbol table
        if (i >= n) {
            return;
        }

        while (i + 1 < n) {
            keys[i] = keys[i + 1];
            vals[i] = vals[i + 1];
            i++;
        }
        // avoid loitering
        keys[n - 1] = null;
        vals[n - 1] = null;
        n--;
        if (n <= keys.length / 4) {
            resize(keys.length / 2);
        }
    }


    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (int i = 0; i < n; i++)
            queue.enqueue(keys[i]);
        return queue;
    }

    private void resize(int newSize) {
        Key[] tmpKeys = (Key[]) new Object[newSize];
        Value[] tmpVals = (Value[]) new Object[newSize];
        for (int i = 0; i < n; i++) {
            tmpKeys[i] = keys[i];
            tmpVals[i] = vals[i];
        }
        keys = tmpKeys;
        vals = tmpVals;
    }
}
