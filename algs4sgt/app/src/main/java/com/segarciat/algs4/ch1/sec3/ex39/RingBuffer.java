package com.segarciat.algs4.ch1.sec3.ex39;

import java.util.NoSuchElementException;

/**
 * <strong>1.3.39)</strong>
 * According to the exercise, when the buffer is empty, consumers wait until
 * data is deposited; when the buffer is full, the producer waits to deposit
 * data. I interpreted these to mean that the caller blocks, suggesting
 * the need for a lock or a condition variable or a mutex. In my implementation
 * I simply throw exceptions under these conditions.
 */
public class RingBuffer<Item> {
    private Item[] a;
    private int head;
    private int tail;
    private int n;


    public RingBuffer(int size) {
        if (size <= 0)
            throw new IllegalArgumentException("size must be positive");
        a = (Item[]) new Object[n];
        head = -1;
        tail = -1;
        n = size;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void enqueue(Item item) {
        if (item == null)
            throw new NullPointerException("cannot add null");
        if (n == a.length)
            return;
        a[++tail] = item;
        if (tail == a.length - 1) // wrap around
            tail = -1;
        n++;
        if (n == 1)
            head = tail;
    }

    public Item dequeue() {
        if (n == 0)
            throw new NoSuchElementException("buffer is empty");
        Item item = a[head];
        a[head++] = null; // avoid loitering
        if (head == a.length)
            head = 0;
        n--;
        return item;
    }
}
