package com.segarciat.algs4.ch1.sec3.ex01;

/**
 * <strong>1.3.1)</strong>
 * Add a method <code>isFull()</code> to <code>FixedCapacityStackOfStrings</code>.
 * Implementation from page 133 from Algorithms 4th edition by Sedgewick and Wayne,
 * except <code>isFull()</code>
 * @author Sergio E. Garcia Tapia
 */
public class FixedCapacityStackOfStrings {

    private final String[] a;
    private int n;

    public FixedCapacityStackOfStrings(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("capacity must be positive");
        a = new String[capacity];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public boolean isFull() {
        return n == a.length;
    }

    public int size() {
        return n;
    }

    public void push(String item) {
        a[n++] = item;
    }

    public String pop() {
        return a[--n];
    }
}
