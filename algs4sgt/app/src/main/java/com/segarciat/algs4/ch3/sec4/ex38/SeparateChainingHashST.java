package com.segarciat.algs4.ch3.sec4.ex38;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

/**
 * <strong>3.4.38</strong>
 * @author Sergio E. Garcia Tapia
 */
public final class SeparateChainingHashST<Key, Value> {
    private int m;
    private int n;
    private final Node<Key, Value>[] st;
    private static int totalCompares = 0;

    private static class Node<Key, Value> {
        Key key;
        Value val;
        Node<Key, Value> next;
        int count = 0;

        public Node(Key key, Value val, Node<Key, Value> next, int count) {
            this.key = key;
            this.val = val;
            this.next = next;
            this.count = count;
        }
    }

    public SeparateChainingHashST(int m) {
        st = (Node<Key, Value>[]) new Node[m];
        this.m = m;
    }

    public SeparateChainingHashST() {
        this(997);
    }

    public int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    public void put(Key key, Value val) {
        if (key == null)
            throw new NullPointerException("cannot use a null key");

        // find the node
        int h = hash(key);
        for (Node<Key, Value> x = st[h]; x != null; x = x.next) {
            totalCompares++;
            if (x.key.equals(key)) {
                x.val = val;
                return;
            }
        }
        Node<Key, Value> head = new Node<>(key, val, st[h], n++);
        st[h] = head;
    }

    public static void main(String[] args) {
        final int N = 100_000;
        final int LARGEST_KEY = 1_000_000;
        final int INCREMENTS = 1_000;
        int insertions = 0;
        Queue<Integer> costs = new Queue<>();

        int maxCost = 0;
        SeparateChainingHashST<Integer, Integer> table = new SeparateChainingHashST<>(N);
        for (int i = 0; i < N; i++) {
            int r = StdRandom.uniformInt(0, LARGEST_KEY);
            table.put(r, r);
            insertions++;
            if (insertions >= INCREMENTS) {
                costs.enqueue(totalCompares);
                if (totalCompares > maxCost)
                    maxCost = totalCompares;
                insertions = 0;
                totalCompares = 0;
            }
        }

        final double scalePct = 0.80;
        double maxScaleX = N / scalePct;
        double maxScaleY = maxCost / scalePct;
        StdDraw.setXscale(0, maxScaleX);
        StdDraw.setYscale(0, maxScaleY);
        double gapX = maxScaleX * (1 - scalePct) / 2;
        double gapY = maxScaleY * (1 - scalePct) / 2;

        // x-axis
        StdDraw.line(gapX, gapY, maxScaleX - gapX, gapY);
        StdDraw.text(maxScaleX - gapX, gapY / 2, "%d".formatted(N));

        // y-axis
        StdDraw.line(gapX, gapY, gapX, maxScaleY - gapY);
        StdDraw.text(gapX / 2, maxScaleY - gapY, "%d".formatted(maxCost));

        StdDraw.setPenRadius(0.0075);
        for (int i = 0; i < N / INCREMENTS; i++) {
            StdDraw.point(gapX + INCREMENTS * (i + 1), gapY + costs.dequeue());
        }
    }
}
