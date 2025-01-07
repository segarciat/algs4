package com.segarciat.algs4.ch3.sec5.ex03;

import java.util.NoSuchElementException;

/**
 * <strong>3.5.3</strong>
 * Set implementation based on {@link edu.princeton.cs.algs4.BinarySearchST}.
 * @author Sergio E. Garcia Tapia
 */
public class BinarySearchSET <Key extends Comparable<Key>> {
    private int n;
    private Key[] keys;

    public BinarySearchSET(int capacity) {
        keys = (Key[]) new Comparable[capacity];
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    private int rank(Key key) {
        int lo = 0;
        int hi = n - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0)
                hi = mid - 1;
            else if (cmp > 0)
                lo = mid + 1;
            else
                return mid;
        }
        return lo;
    }

    public boolean contains(Key key) {
        if (key == null || isEmpty())
            return false;

        int r = rank(key);
        return r < n && key.compareTo(keys[r]) == 0;
    }

    private void resize(int capacity) {
        Key[] temp = (Key[]) new Comparable[capacity];
        for (int i = 0; i < n; i++)
            temp[i] = keys[i];
        keys = temp;
    }

    public void add(Key key) {
        if (key == null)
            throw new NullPointerException("cannot add null key");

        int r = rank(key);

        // already added
        if (r < n && key.compareTo(keys[r]) == 0)
            return;
        if (n == keys.length)
            resize(2 * keys.length);

        for (int j = n; j > r; j--)
            keys[j] = keys[j - 1];
        keys[r] = key;
    }

    public void delete(Key key) {
        if (key == null)
            throw new NullPointerException("cannot delete null key");

        int r = rank(key);
        if (r >= n || key.compareTo(keys[r]) != 0)
            throw new NoSuchElementException("key not in set");

        for (int j = n - 1; j > r; j--)
            keys[j - 1] = keys[j];
        keys[--n] = null; // avoid loitering
        if (n <= keys.length / 4)
            resize(keys.length / 2);
    }
}
