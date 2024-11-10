package com.segarciat.algs4.ch2.sec4.ex30;

import com.segarciat.algs4.ch2.SortUtil;
import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.MinPQ;

import java.util.NoSuchElementException;

/**
 * <strong>2.4.30)</strong>
 * A data type that provides access to the median in constant
 * time, as well as inserting keys and removing the median
 * in logarithmic time.
 * @param <Key>
 * @author Sergio E. Garcia Tapia
 */
public class MedianPQ <Key extends Comparable<Key>>{
    /**
     * Holds half or 1 more than half of the keys. All keys
     * in minpq are no less than the keys in maxpq.
     * The minimum item of minpq is the median.
     */
    private final MinPQ<Key> minpq = new MinPQ<>();
    /**
     * Holds half or 1 less than half of the keys. All keys
     * in maxpq are no greater than the keys in maxpq.
     */
    private final MaxPQ<Key> maxpq = new MaxPQ<>();

    /**
     * The number of items in total among both queues.
     */
    private int n = 0;

    /**
     * @return The number of items in the queue.
     */
    public int size() {
        return n;
    }

    /**
     * @return <code>true</code> if there are no items in the queue, and <code>false</code> otherwise.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Inserts the given key in the queue.
     * @param key The key to insert.
     * @throws NullPointerException if the given key is <code>null</code>.
     */
    public void insert(Key key) {
        if (key == null)
            throw new NullPointerException("cannot add a null key");

        n++;
        // Prioritize elements in pq; min will be median
        if (minpq.isEmpty() && maxpq.isEmpty()) {
            minpq.insert(key);
            return;
        }

        // minpq must have median may have more keys
        if (minpq.size() == maxpq.size()) {
            // re-arrange to have median at minpq[1]
            if (SortUtil.less(key, maxpq.max())) {
                minpq.insert(maxpq.delMax());
                maxpq.insert(key);
            } else {
                minpq.insert(key);
            }
        } else { // minpq has more keys
            // re-arrange to have median at minpq[1]
            if (SortUtil.less(minpq.min(), key)) {
                maxpq.insert(minpq.delMin());
                minpq.insert(key);
            } else {
                maxpq.insert(key);
            }
        }
    }

    /**
     * @return The median of the keys in the queue (in constant time).
     */
    public Key median() {
        if (isEmpty())
            throw new NoSuchElementException("queue is empty");
        return minpq.min();
    }

    /**
     * Removes and returns the median of the keys in the queue (in logarithmic time).
     * @return The median of the keys in the queue.
     */
    public Key delMedian() {
        if (isEmpty())
            throw new NoSuchElementException("queue is empty");

        // invariant: size of minpq is at least that of maxpq
        Key median = minpq.delMin();
        if (minpq.size() < maxpq.size()) {
            minpq.insert(maxpq.delMax());
        }
        n--;
        return median;
    }

    public static void main(String[] args) {
        MedianPQ<Integer> pq = new MedianPQ<>();
        final int n = 19;
        for (int i = 0; i < n; i++)
            pq.insert(10 * (i+ 1));
        while (!pq.isEmpty())
            System.out.print(pq.delMedian() + " ");

    }
}
