package com.segarciat.algs4.ch2.sec4.ex33;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * <strong>2.4.34)</strong>
 * Implements the basic of operations of the index (minimum-oriented)
 * priority-queue abstract data type specified in Section 2.4.
 * @param <Key> The type of the keys to store.
 * @author Sergio E. Garcia Tapia
 */
public class IndexMinPQ <Key extends Comparable<Key>> {
    int[] pq;
    int[] qp;
    Key[] keys;
    private int n = 0;

    public IndexMinPQ(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("capacity must be positive");
        pq = new int[capacity + 1];
        qp = new int[pq.length];
        keys = (Key[]) new Comparable[pq.length];

        // -1 means item not in queue
        for (int i = 0; i < qp.length; i++) {
            qp[i] = - 1;
        }
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(int i) {
        if (i < 0 || i >= qp.length - 1)
            throw new IndexOutOfBoundsException("invalid index");
        return qp[i] != -1;
    }

    public Key minKey() {
        if (isEmpty())
            throw new NoSuchElementException("the queue is empty");
        return keys[1];
    }

    public Key keyOf(int i) {
        if (!contains(i))
            throw new NoSuchElementException("no associated key");
        return keys[qp[i]];
    }

    public void insert(int i, Key key) {
        if (key == null)
            throw new NullPointerException("cannot add a null key");
        if (i < 0 || i >= qp.length - 1)
            throw new IndexOutOfBoundsException("invalid index");

        ++n;
        keys[n] = key;
        pq[n] = i;
        qp[i] = n;
        swim(n);
    }

    public int delMin() {
        if (isEmpty())
            throw new NoSuchElementException("the queue is empty");
        int minIndex = pq[1];
        exchange(1, n--);
        keys[n + 1] = null; // avoid loitering
        qp[minIndex] = -1;
        sink(1);
        return minIndex;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exchange(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(j, j + 1))
                j = j + 1;
            if (!less(k, j))
                break;
            exchange(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        // reversed inequality to implement min PQ instead of max PQ
        return keys[i].compareTo(keys[j]) > 0;
    }

    private void exchange(int i, int j) {
        Key tempKey = keys[i];
        keys[i] = keys[j];
        keys[j] = tempKey;

        int tempIndex = pq[i];
        pq[i] = pq[j];
        pq[j] = tempIndex;

        tempIndex = qp[pq[i]];
        qp[pq[i]] = qp[pq[j]];
        qp[pq[j]] = tempIndex;
    }

    public static void main(String[] args) {
        final int n = 10;

        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = 5 * i;
        StdRandom.shuffle(a);
        System.out.print("Array: ");
        System.out.println(Arrays.toString(a));

        IndexMinPQ<Integer> pq = new IndexMinPQ<>(n);
        for (int i = 0; i < n; i++)
            pq.insert(i, a[i]);

        System.out.print("queue: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(pq.keyOf(i) + " ");
        }
        System.out.println();
        System.out.println("deleting min");
        int i = pq.delMin();
        System.out.printf("returned index %d which %s in the queue%n", i, pq.contains(i) ? "is" : "is not");

        while (!pq.isEmpty()) {
            i = pq.delMin();
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
}
