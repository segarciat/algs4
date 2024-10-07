package com.segarciat.algs4.ch1.sec4.ex37;

import java.util.NoSuchElementException;

public class FixedCapacityStackOfInts {
    private final int[] a;
    int n;

    public FixedCapacityStackOfInts(int max) {
        if (max <= 0)
            throw new IllegalArgumentException("max must be a positive integer");
        a = new int[max];
        n = 0;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public void push(int key) {
        if (n >= a.length)
            throw new ArrayIndexOutOfBoundsException("the stack is full");
        a[n++] = key;
    }

    public int pop() {
        if (n <= 0)
            throw new NoSuchElementException("the stack is empty");
        return a[--n];
    }
}
