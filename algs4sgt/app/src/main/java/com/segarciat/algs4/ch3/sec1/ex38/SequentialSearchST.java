package com.segarciat.algs4.ch3.sec1.ex38;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;

import java.awt.Color;
import java.util.NoSuchElementException;

/**
 * <strong>3.1.38)</strong>
 * Produces an amortized cost plot of the {@link #put(Object, Object)} operations
 * when {@link edu.princeton.cs.algs4.FrequencyCounter} is used as a
 * {@link edu.princeton.cs.algs4.SequentialSearchST} client.
 * @author Sergio E. Garcia Tapia
 */
public final class SequentialSearchST<Key, Value> {
    private Node<Key, Value> first = null;
    private int n = 0;
    private static int compares = 0;

    private static class Node <Key, Value> {
        Key key;
        Value val;
        Node<Key, Value> next;

        Node(Key key, Value val, Node<Key, Value> next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
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

        for (var current = first; current != null; current = current.next) {
            if (current.key.equals(key))
                return current.val;
        }
        // Key not found
        return null;
    }

    public void put(Key key, Value val) {
        if (key == null)
            throw new NullPointerException("cannot add a null key");

        if (val == null) {
            delete(key);
            return;
        }

        // See if node is already present
        compares = 0;
        for (var current = first; current != null; current = current.next) {
            compares++;
            if (current.key.equals(key)) {
                current.val = val;
                return;
            }
        }

        // Prepend the node
        first = new Node<>(key, val, first);
        n++;
    }

    public void delete(Key key) {
        if (key == null)
            throw new NullPointerException("cannot delete null");
        if (first == null)
            throw new NoSuchElementException("queue is empty");

        // Find the key to delete.
        Node<Key, Value> current = first;
        Node<Key, Value> prev = null;
        while (current != null && !current.key.equals(key)) {
            prev = current;
            current = current.next;
        }

        if (current == null)
            throw new NoSuchElementException("no matching key");

        // Remove the node
        if (prev == null) {
            first = first.next;
        } else {
            prev.next = current.next;
        }
        n--;
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (var current = first; current != null; current = current.next)
            queue.enqueue(current.key);
        return queue;
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Provide a single argument: a length cutoff.");
            System.exit(1);
        }
        // Read cutoff length
        int minLen = Integer.parseInt(args[0]);

        // Read words
        int totalPutOperations = 0;
        int maxCost = 0;
        Queue<Integer> costs = new Queue<>();
        SequentialSearchST<String, Integer> st = new SequentialSearchST<>();
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (word.length() < minLen)
                continue;
            if (!st.contains(word)) {
                st.put(word, 1);
            } else {
                st.put(word, st.get(word) + 1);
            }
            costs.enqueue(compares);
            totalPutOperations++;
            if (compares > maxCost)
                maxCost = compares;
        }

        final double scalePct = 0.8;
        double maxScaleX  = totalPutOperations / scalePct;
        double maxScaleY = maxCost / scalePct;
        StdDraw.setXscale(0, maxScaleX);
        StdDraw.setYscale(0, maxScaleY);
        double gapX = maxScaleX * (1.0 - scalePct) / 2.0;
        double gapY = maxScaleY * (1.0 - scalePct) / 2.0;

        // Draw axes
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setPenRadius(0.005);
        // x-axis
        StdDraw.line(gapX, gapY, maxScaleX - gapX, gapY);
        // y-axis
        StdDraw.line(gapX, gapY, gapX, maxScaleY - gapY);

        StdDraw.setPenRadius(0.01);
        // x-axis max label
        StdDraw.text(maxScaleX - gapX, gapY / 2, String.format("%d", totalPutOperations));
        // y-axis max label
        StdDraw.text(gapX / 2, maxScaleY - gapY, String.format("%d", maxCost));

        StdDraw.setPenRadius(0.0075);
        double totalCost = 0.0;
        for (int i = 1; i <= totalPutOperations; i++) {
            int cost = costs.dequeue();

            StdDraw.setPenColor(Color.GRAY);
            StdDraw.point(gapX + i, gapY + cost);

            totalCost += cost;
            StdDraw.setPenColor(Color.RED);
            StdDraw.point(gapX + i, gapY +totalCost / i);
        }
    }
}

