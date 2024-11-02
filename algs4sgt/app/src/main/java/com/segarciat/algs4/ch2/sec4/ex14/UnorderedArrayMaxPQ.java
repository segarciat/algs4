package com.segarciat.algs4.ch2.sec4.ex14;/* *****************************************************************************
 *  Name:              Sergio E. Garcia Tapia
 *  Last modified:     09/17/2020
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class UnorderedArrayMaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int n = 0;

    public UnorderedArrayMaxPQ() {
        pq = (Key[]) new Comparable[1];
    }

    public void insert(Key key) {
        if (n == pq.length)
            resize(2 * pq.length);
        pq[n++] = key;
    }

    public Key delMax() {
        // Find the maximum.
        int maxIndex = 0;
        for (int i = 0; i < n; i++) {
            if (less(maxIndex, i))
                maxIndex = i;
        }

        exch(maxIndex, n - 1);
        Key max = pq[--n];
        pq[n + 1] = null;
        if (n == pq.length / 4)
            resize(pq.length / 2);
        return max;
    }

    private void resize(int newSize) {
        Key[] b = (Key[]) new Comparable[newSize];
        for (int i = 0; i < n; i++) {
            b[i] = pq[i];
        }
        pq = b;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    public static void main(String[] args) {
        UnorderedArrayMaxPQ<Integer> pq = new UnorderedArrayMaxPQ<Integer>();
        int n;
        StdOut.print("Random generated: ");
        for (int i = 0; i < 10; i++) {
            n = StdRandom.uniform(0, 1000);
            StdOut.print(n + " ");
            pq.insert(n);
        }

        StdOut.println();
        StdOut.print("Deleting from PQ: ");
        while (!pq.isEmpty()) {
            StdOut.print(pq.delMax() + " ");
        }
        StdOut.println();
    }
}
