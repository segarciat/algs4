package com.segarciat.algs4.ch1.sec4.ex37;

import java.util.NoSuchElementException;

public class FixedCapacityStack<Item> {
    private final Item[] a;
    private int n;

    public FixedCapacityStack(int max) {
        a = (Item[]) new Object[max];
        n = 0;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public void push(Item key) {
        if (n >= a.length)
            throw new ArrayIndexOutOfBoundsException("the stack is full");
        a[n++] = key;
    }

    public Item pop() {
        if (n <= 0)
            throw new NoSuchElementException("the stack is empty");
        return a[--n];
    }
}
