package com.segarciat.algs4.ch3.sec1.ex02;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

/**
 * <strong>3.1.2)</strong>
 * An implementation of an unordered symbol table using arrays.
 * @author Sergio E. Garcia Tapia
 */
public final class ArrayST <Key, Value>{
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
        for (int i = 0; i < n; i++) {
            if (key.equals(keys[i]))
                return vals[i];
        }
        return null;
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

    /**
     * Reads words from standard input and displays their frequency in no
     * particular order.
     */
    public static void main(String[] args) {
        ArrayST<String, Integer> st = new ArrayST<>();
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString().toUpperCase();
            if (!st.contains(key)) {
                st.put(key, 1);
            } else {
                st.put(key, st.get(key) + 1);
            }
        }

        for (String key: st.keys()) {
            StdOut.printf("%s: %d%n", key, st.get(key));
        }
    }
}
