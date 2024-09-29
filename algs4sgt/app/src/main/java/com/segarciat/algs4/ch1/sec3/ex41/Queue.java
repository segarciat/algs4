package com.segarciat.algs4.ch1.sec3.ex41;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <strong>1.3.41</strong>
 */
public class Queue<Item> implements Iterable<Item> {

    private Node last;
    private int n;

    private class Node {

        private Node next;
        private Item item;
    }

    /**
     * Creates an empty queue
     */
    public Queue() {
        // Default constructor
    }

    /**
     * Creates an independent copy of <code>queue</code>
     * @param queue The queue to copy.
     */
    public Queue(Queue<Item> queue) {
        if (queue == null)
            throw new NullPointerException("cannot be null");
        for (Item item: queue)
            enqueue(item);
    }

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
        private Node current = last == null ? null : last.next;
        private int remaining = n;

        @Override
        public boolean hasNext() {
            return remaining > 0;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("No more items");
            Item item = current.item;
            current = current.next;
            remaining--;
            return item;
        }
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
        for (int i = 0; i < 10; i++)
            queue.enqueue(i);
        Queue<Integer> copy = new Queue<>(queue);
        while (!queue.isEmpty())
            System.out.print(queue.dequeue() + " ");
        System.out.println();
        System.out.printf("queue size=%d, copy size =%d%n", queue.size(), copy.size());
        while (!copy.isEmpty())
            System.out.print(copy.dequeue() + " ");
        System.out.println();
    }
}
