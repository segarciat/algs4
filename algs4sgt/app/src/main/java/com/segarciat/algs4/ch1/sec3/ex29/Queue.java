package com.segarciat.algs4.ch1.sec3.ex29;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <strong>1.3.29</strong>
 * Write a <code>Queue</code> implementation that uses a <em>circular</em> linked list,
 * which is the same as a linked list except that no links are <em>null</em> and the
 * <code>last.next</code> is <code>first</code> whenever the list is not empty.
 * Keep only one  <code>Node</code> instance variable.
 */
public class Queue<Item> implements Iterable<Item> {

    private class Node {

        private Node next;
        private Item item;
    }

    private Node last;
    private int n;

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void enqueue(Item item) {
        if (item == null)
            throw new NullPointerException("cannot enqueue null item");

        Node oldLast = last;
        last = new Node();
        last.item = item;
        if (oldLast == null)
            last.next = last;
        else {
            last.next = oldLast.next;
            oldLast.next = last;
        }
        n++;
    }

    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("queue is empty");
        Node first = last.next;
        Item item = first.item;
        if (first == last)
            last = null;
        else
            last.next = first.next;
        n--;
        return item;
    }

    @Override
    @Nonnull
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {
        private Node current;


        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("No more items");
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
