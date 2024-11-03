package com.segarciat.algs4.ch2.sec4.ex03;

import com.segarciat.algs4.ch2.SortUtil;
import com.segarciat.algs4.ch2.sec4.IMaxPQ;

import java.util.NoSuchElementException;

/**
 * <code>2.4.3</code>
 * (Max) priority queue implementation using an ordered array.
 * The time complexity of getting and removing the maximum is O(1),
 * and that of inserting an item is O(n). The given time complexities
 * are amortized.
 * @param <Key> The type of items to in the queue.
 * @author Sergio E. Garcia Tapia
 */
public class OrderedArrayMaxPQ <Key extends Comparable<Key>> implements IMaxPQ<Key> {
    private Key[] pq;
    private int n =0;

    public OrderedArrayMaxPQ(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("capacity must be positive");
        pq = (Key[]) new Comparable[capacity];
    }

    public OrderedArrayMaxPQ() {
        this(2);
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Adds the given key to the priority queue.
     * @param v The key to add.
     * @throws NullPointerException if the key is <code>null</code>.
     */
    public void insert(Key v) {
        if (v == null)
            throw new NullPointerException("cannot add a null item");
        if (size() == pq.length) {
            resize(2 * pq.length);
        }
        n++;
        int j;
        for (j = n - 1; j > 0 && SortUtil.less(v, pq[j - 1]); j--)
            pq[j] = pq[j-1];
        pq[j] = v;
    }

    /**
     * @return The largest element in the priority queue.
     * @throws NoSuchElementException if the queue is empty.
     */
    public Key max() {
        if (isEmpty())
            throw new NoSuchElementException("the queue is already empty");
        return pq[n - 1];
    }

    /**
     * Removes the largest element in the priority queue.
     * @return The largest element in the priority queue.
     * @throws NoSuchElementException if the queue is empty.
     */
    public Key delMax() {
        if (isEmpty())
            throw new NoSuchElementException("the queue is already empty");
        Key maxKey = pq[--n];
        pq[n] = null; // avoid loitering
        if (size() == pq.length / 4)
            resize(pq.length / 2);
        return maxKey;
    }

    private void resize(int newMax) {
        Key[] temp = (Key[]) new Comparable[newMax];
        for (int i = 0; i < n; i++)
            temp[i] = pq[i];
        pq = temp;
    }
}
