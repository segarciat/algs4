package com.segarciat.algs4.ch1.sec3.ex38;

public class ListGeneralizedQueue<Item> implements GeneralizedQueue<Item> {
    private int n;
    /**
     * When queue is not empty, <code>last.next</code> points to the first item in the queue,
     * thereby implementing a circular linked list.
     */
    private Node<Item> last;

    private static class Node<Item> {
        Node<Item> next;
        Item item;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public void insert(Item x) {
        if (x == null)
            throw new NullPointerException("cannot add null");
        Node<Item> oldLast = last;
        last = new Node<>();
        last.item = x;
        if (oldLast == null)
            last.next = last;
        else {
            last.next = oldLast.next;
            oldLast.next = last;
        }
        n++;
    }

    @Override
    public Item delete(int k) {
        if (k < 0 || k >= n)
            throw new IndexOutOfBoundsException("must be non-negative integer no greater than the queue size");
        Node<Item> prev = last;
        Node<Item> current = last.next;
        for (int i = 0; i < n - 1 - k; i++) {
            prev = current;
            current = current.next;
        }
        Item item = current.item;
        n--;
        if (n == 0)
            last = null;
        else
            prev.next = current.next;
        return item;
    }
}
