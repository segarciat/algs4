package com.segarciat.algs4.ch2.sec4.ex03;

import com.segarciat.algs4.ch2.SortUtil;
import com.segarciat.algs4.ch2.sec4.IMaxPQ;

import java.util.NoSuchElementException;

/**
 * <code>2.4.3</code>
 * (Max) priority queue implementation using an unordered array.
 * The time complexity of getting and removing the maximum is O(n),
 * and that of inserting an item is O(1). The given time complexities
 * are amortized.
 * @param <Key> The type of items to in the queue.
 * @author Sergio E. Garcia Tapia
 */
public class UnorderedArrayMaxPQ<Key extends Comparable<Key>> implements IMaxPQ<Key> {
    private Key[] pq;
    private int n = 0;

    public UnorderedArrayMaxPQ(int maxN) {
        if (maxN <= 0)
            throw new IllegalArgumentException("capacity must be positive");
        pq = (Key[]) new Comparable[maxN];
    }

    public UnorderedArrayMaxPQ() {
        this(2);
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    /**
     * Adds the given key to the priority queue.
     * @param v The key to add.
     * @throws NullPointerException if the key is <code>null</code>.
     * @throws RuntimeException if the queue does not have room for more elements.
     */
    public void insert(Key v) {
        if (v == null)
            throw new NullPointerException("cannot add null");
        if (size() == pq.length)
            resize(pq.length * 2);
        pq[n++] = v;
    }

    /**
     * @return The largest element in the priority queue.
     * @throws NoSuchElementException if the queue is empty.
     */
    public Key max() {
        return pq[maxIndex()];
    }

    /**
     * Removes the largest element in the priority queue.
     * @return The largest element in the priority queue.
     * @throws NoSuchElementException if the queue is empty.
     */
    public Key delMax() {
        int i = maxIndex();
        Key maxKey = pq[i];
        SortUtil.exchange(pq, i, --n);
        pq[n] = null; // avoid loitering
        if (size() == pq.length / 4)
            resize(pq.length / 2);
        return maxKey;
    }

    /**
     * @return The index of the largest item in the priority queue.
     * @throws NoSuchElementException if the queue is empty.
     */
    private int maxIndex() {
        if (isEmpty())
            throw new NoSuchElementException("the queue is empty");
        // find the maximum
        int i = 0;
        for (int j = 1; j < n; j++) {
            if (SortUtil.less(pq[i], pq[j]))
                i = j;
        }
        return i;
    }

    private void resize(int newMax) {
        Key[] temp = (Key[]) new Comparable[newMax];
        for (int i = 0; i < n; i++)
            temp[i] = pq[i];
        pq = temp;
    }
}
