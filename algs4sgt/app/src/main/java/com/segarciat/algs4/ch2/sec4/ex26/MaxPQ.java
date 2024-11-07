package com.segarciat.algs4.ch2.sec4.ex26;

import com.segarciat.algs4.ch2.SortUtil;
import com.segarciat.algs4.ch2.sec4.IMaxPQ;
import edu.princeton.cs.algs4.StdRandom;

import java.util.NoSuchElementException;

/**
 * <strong>2.4.26)</strong>
 * Implements a <code>MaxPQ</code> as in section 2.4 of Algorithms
 * by Sedgewick and Wayne, with half exchanges for <code>swim()</code>
 * and <code>sink()</code>.
 * @param <Key>
 * @author Sergio E. Garcia Tapia
 */
public class MaxPQ<Key extends Comparable<Key>> implements IMaxPQ<Key> {
    /**
     * The array consisting of the keys for the priority queue.
     */
    private Key[] pq;
    /**
     * The number of items currently in the priority queue.
     */
    private int n = 0;

    public MaxPQ(int maxN) {
        if (maxN <= 0)
            throw new IllegalArgumentException("capacity must be positive");
        pq = (Key[]) new Comparable[maxN + 1];
    }

    public MaxPQ() {
        this(1);
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public void insert(Key key) {
        if (key == null)
            throw new NullPointerException("cannot add null key");
        if (n == pq.length - 1)
            resize(2 * n + 1);
        pq[++n] = key;
        swim(n);
    }

    @Override
    public Key max() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException("the queue is empty");
        return pq[1];
    }

    @Override
    public Key delMax() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException("the queue is empty");
        Key maxKey = pq[1];
        SortUtil.exchange(pq, 1, n--);
        pq[n + 1] = null; // avoid loitering
        sink(1);
        if (n == ((pq.length - 1) / 4))
            resize( ((pq.length - 1) / 2) + 1);
        return maxKey;
    }

    private void swim(int k) {
        Key key = pq[k];
        while (k > 1 && SortUtil.less(pq[k / 2], key)) {
            pq[k] = pq[k / 2]; // move parent down
            k = k / 2;
        }
        pq[k] = key;
    }

    private void sink(int k) {
        Key key = pq[k];
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && SortUtil.less(pq[j], pq[j + 1]))
                j++;
            if (!SortUtil.less(pq[k], pq[j]))
                break;
            pq[k] = pq[j]; // move child up
            k = j;
        }
        pq[k] = key;
    }

    private void resize(int newSize) {
        Key[] temp = (Key[]) new Comparable[newSize];
        for (int i = 0; i <= n; i++)
            temp[i] = pq[i];
        pq = temp;
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[16];
        for (int i = 0; i < a.length; i++)
            a[i] = StdRandom.uniformInt(1_000);
        com.segarciat.algs4.ch2.sec4.ex19.MaxPQ<Integer> pq = new com.segarciat.algs4.ch2.sec4.ex19.MaxPQ<>(a);
        System.out.printf("pq size = %d, and max element is %d%n", pq.size(), pq.max());
        Integer prev = null;
        while (!pq.isEmpty()) {
            Integer max = pq.delMax();
            System.out.print(max + " ");
            if (prev != null && SortUtil.less(prev, max)){
                System.out.println();
                System.out.println("max was less than previous: " + max + " and " + prev);
            }
            prev = max;
        }
        System.out.println();
    }
}
