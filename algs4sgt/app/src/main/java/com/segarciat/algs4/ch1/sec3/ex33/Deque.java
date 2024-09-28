package com.segarciat.algs4.ch1.sec3.ex33;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <strong>1.3.33)</strong>
 * <em>Deque</em>. A double-ended dequeue or <em>deque</em> (pronounced "deck") is
 * like a stack or a queue but supports adding and removing items at both ends.
 * A dequeue stores collections of items and supports the following API:
 * <pre>
 *     {@code
 *     public class Deque<Item> implements Iterable<Item>
 *         Deque()
 *         boolean isEmpty()
 *         int size()
 *         void pushLeft(Item item)
 *         void pushRight(Item item)
 *         Item popLeft()
 *         Item popRight()
 *     }
 * </pre>
 * Write a class <code>Deque</code> that uses a doubly-linked list to implement this API
 * and a class <code>ResizingArrayQueue</code> that uses a resizing array.
 */
public class Deque<Item> implements Iterable<Item> {
    private int n;
    private DoubleNode<Item> first;
    private DoubleNode<Item> last;

    private static class DoubleNode<Item> {
        Item  item;
        DoubleNode<Item> next;
        DoubleNode<Item> prev;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public void pushLeft(Item item) {
        if (item == null)
            throw new NullPointerException("cannot add null item");
        DoubleNode<Item> oldFirst = first;

        first = new DoubleNode<>();
        first.item = item;
        first.prev = null;
        first.next = oldFirst;

        if (oldFirst == null)
            last = first;
        else
            oldFirst.prev = first;
        n++;
    }

    public void pushRight(Item item) {
        if (item == null)
            throw new NullPointerException("cannot add null item");
        DoubleNode<Item> oldLast = last;

        last = new DoubleNode<>();
        last.item = item;
        last.prev = oldLast;
        last.next = null;

        if (oldLast == null)
            first = last;
        else
            oldLast.next = last;
        n++;
    }

    public Item popLeft() {
        if (isEmpty())
            throw new NullPointerException("no more items");

        Item item = first.item;
        first = first.next;
        if (first == null)
            last = null;
        else
            first.prev = null;
        n--;
        return item;
    }

    public Item popRight() {
        if (isEmpty())
            throw new NoSuchElementException("no more items");
        Item item = last.item;
        last = last.prev;
        if (last == null)
            first = null;
        else
            last.next = null;
        n--;
        return item;
    }

    @Override
    @Nonnull
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private DoubleNode<Item> current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("no more items");
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        for (int k = 0; k < 2; k++) {
            System.out.printf("%nTrial #%d%n", k);
            for (int i = 5; i >= 0; i--)
                deque.pushLeft(i);
            for (int i = 6; i <= 10; i++)
                deque.pushRight(i);
            for (int i: deque)
                System.out.print(i + " ");
            System.out.println();

            while (deque.size() > 5)
                System.out.print(deque.popLeft() + " ");
            System.out.println();
            while (!deque.isEmpty())
                System.out.print(deque.popRight() + " ");
            System.out.println();
        }

    }
}
