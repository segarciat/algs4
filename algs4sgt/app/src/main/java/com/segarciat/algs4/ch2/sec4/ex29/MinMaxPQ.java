package com.segarciat.algs4.ch2.sec4.ex29;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * <strong>2.4.29)</strong>
 * Implements a data type that supports operations to insert
 * a key, delete minimum and maximum in logarithmic time (all three
 * of these), as well as get the minimum and maximum (in constant time).
 * @param <Key>
 * @author Sergio E. Garcia Tapia
 */
public class MinMaxPQ <Key extends Comparable<Key>> {
    private Node<Key>[] minpq;
    private Node<Key>[] maxpq;
    private int n = 0;

    /**
     * Holds the key maintained by the two priority queues.
     * Used so that when a key is removed from one queue,
     * we can use the index to remove it from the other as well.
     */
    private static class Node<T> {
        T key;
        int minIndex;
        int maxIndex;
    }

    /**
     * Creates a MinMaxPQ with the given initial capacity.
     * @param capacity A positive number indicating the initial size of the (empty) queue.
     */
    public MinMaxPQ(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("capacity must be positive");
        minpq = (Node<Key>[]) new Node[capacity + 1];
        maxpq = (Node<Key>[]) new Node[capacity + 1];
    }

    /**
     * Creates an empty priority queue
     */
    public MinMaxPQ() {
        this(2);
    }

    /**
     * @return The number of keys in the queue.
     */
    public int size() {
        return n;
    }

    /**
     * @return <code>true</code> if there are no keys in the queue, and
     * <code>false</code> otherwise.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * @return The minimum key in the queue in constant time.
     * @throws NoSuchElementException if the queue is empty.
     */
    public Key min() {
        if (isEmpty())
            throw new NoSuchElementException("queue is empty");
        return minpq[1].key;
    }

    /**
     * @return The maximum key in the queue in constant time.
     * @throws NoSuchElementException if the queue is empty.
     */
    public Key max() {
        if (isEmpty())
            throw new NoSuchElementException("queue is empty");
        return maxpq[1].key;
    }

    /**
     * Inserts the given item in the queue.
     * @param key The item to insert.
     * @throws NullPointerException if the given key is <code>null</code>.
     */
    public void insert(Key key) {
        if (key == null)
            throw new NullPointerException("cannot add a null key");
        if (n == maxpq.length -1) {
            resize(2 * n + 1);
        }
        ++n;

        // Initial index when key is added to both queues.
        Node<Key> node = new Node<>();
        node.key = key;
        node.maxIndex = n;
        node.minIndex = n;

        minpq[n] = node;
        maxpq[n] = node;

        // Restore heap condition
        swim(minpq, n);
        swim(maxpq, n);
    }

    /**
     * Removes and returns the minimum key from the queue.
     * @return The minimum key from the queue.
     * @throws NoSuchElementException if the queue is empty.
     */
    public Key delMin() {
        if (isEmpty())
            throw new NoSuchElementException("the queue is empty");

        var node = minpq[1];

        n--;
        exchange(minpq, 1, n + 1);
        sink(minpq, 1);
        minpq[n + 1] = null;

        exchange(maxpq, node.maxIndex, n + 1);
        swim(maxpq, node.maxIndex);
        sink(maxpq, node.maxIndex);
        maxpq[n + 1] = null;

        if (n == ((minpq.length - 1) / 4)) {
            resize(((minpq.length - 1) / 2) + 1);
        }

        return node.key;
    }

    /**
     * Removes and returns the maximum key from the queue.
     * @return The maximum key from the queue.
     * @throws NoSuchElementException if the queue is empty.
     */
    public Key delMax() {
        if (isEmpty())
            throw new NoSuchElementException("the queue is empty");

        var node = maxpq[1];

        n--;
        exchange(maxpq, 1, n + 1);
        sink(maxpq, 1);
        maxpq[n + 1] = null;

        exchange(minpq, node.minIndex, n + 1);
        swim(minpq, node.minIndex);
        sink(minpq, node.minIndex);
        minpq[n + 1] = null;

        if (n == ((minpq.length - 1) / 4)) {
            resize(((minpq.length - 1) / 2) + 1);
        }

        return node.key;
    }


    private void swim(Node<Key>[] pq, int k) {
        while (k > 1 && less(pq, k / 2, k)) {
            exchange(pq, k/2, k);
            k = k / 2;
        }
    }

    private void sink(Node<Key>[] pq, int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(pq, j, j + 1))
                j++;
            if (!less(pq, k, j))
                break;
            exchange(pq, k, j);
            k = j;
        }
    }

    /**
     * Determines how the item in the queue at the given indices
     * compare. The word <code>less()</code> is correct for the
     * maximum priority queue, but should probably be greater
     * <code>bigger()</code> in the case of the min-priority queue.
     * In either case, this operates correctly by changing the sense
     * of the inequality.
     */
    private boolean less(Node<Key>[] pq, int i, int j) {
        // One queue is max-oriented and the other is min-oriented.
        if (pq == maxpq)
            return maxpq[i].key.compareTo(maxpq[j].key) < 0;
        else
            return minpq[i].key.compareTo(minpq[j].key) > 0;
    }

    private void exchange(Node<Key>[] pq, int i, int j) {
        // Keeping track of the positions helps during deletion.
        var iNode = pq[i];
        var jNode = pq[j];
        if (pq == maxpq) {
            iNode.maxIndex = j;
            jNode.maxIndex = i;
        } else {
            iNode.minIndex = j;
            jNode.minIndex = i;
        }

        var temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    private void resize(int newSize) {
        var tempMinPQ = (Node<Key>[]) new Node[newSize];
        var tempMaxPQ = (Node<Key>[]) new Node[newSize];
        for (int i = 1; i <= n; i++) {
            tempMinPQ[i] = minpq[i];
            tempMaxPQ[i] = maxpq[i];
        }
        maxpq = tempMaxPQ;
        minpq = tempMinPQ;
    }

    public static void main(String[] args) {
        int n = 16;
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = 10 * (i + 1);
        StdRandom.shuffle(a);
        System.out.println(Arrays.toString(a));

        MinMaxPQ<Integer> pq = new MinMaxPQ<>();
        for (int i = 0; i < n; i++)
            pq.insert(a[i]);

        System.out.printf("max=%d, min=%d%n", pq.max(), pq.min());

        int minIndex = 0;
        int maxIndex = n - 1;
        int[] b = new int[n];
        while (!pq.isEmpty()) {
            b[minIndex++] = pq.delMin();
            b[maxIndex--] = pq.delMax();
            System.out.println(pq.size());
        }
        System.out.println(Arrays.toString(b));
        System.out.println("queue size is now: " + pq.size());
    }
}
