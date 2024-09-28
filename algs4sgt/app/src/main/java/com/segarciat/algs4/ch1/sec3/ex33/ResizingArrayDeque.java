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
public class ResizingArrayDeque<Item> implements Iterable<Item> {
    private Item[] a;
    /**
     * Number of items in deque.
     */
    private int n;
    /**
     * Index of first item in deque. Always points to valid element, except
     * when deque is empty.
     */
    private int head;
    /**
     * Index of last item in deque. Always points to a valid  element, except
     * when deque is empty.
     */
    private int tail;

    public ResizingArrayDeque() {
        a = (Item[]) new Object[1];
        n = 0;
        head =  1;
        tail = -1;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < n; i++) {
            int wrapAroundIndex = ((head + i) >= a.length) ? head + i - a.length : head + i;
            temp[i] = a[wrapAroundIndex];
        }
        a = temp;
        head = 0;
        tail = n - 1;
    }

    public void pushLeft(Item item) {
        if (item == null)
            throw new NullPointerException("cannot add null");
        if (n == a.length)
            resize(a.length * 2);
        if (head == 0)
            head = a.length;
        a[--head] = item;
        n++;
        if (n == 1)
            tail = head;
    }

    public void pushRight(Item item) {
        if (item == null)
            throw new NullPointerException("cannot add null");
        if (n == a.length)
            resize(a.length * 2);
        if (tail == a.length - 1)
            tail = -1;
        a[++tail] = item;
        n++;
        if (n == 1)
            head = tail;
    }

    public Item popLeft() {
        if (isEmpty())
            throw new NoSuchElementException("deque is empty");

        Item item = a[head];
        a[head++] = null; // avoid loitering
        n--;

        if (n > 0 && n == a.length / 4)
            resize(a.length / 2);
        if (n == 0)
            tail  = head - 2;
        return item;
    }

    public Item popRight()  {
        if (isEmpty())
            throw new NoSuchElementException("deque is empty");

        Item item = a[tail];
        a[tail--] = null; // avoid loitering
        n--;

        if (n > 0 && n == a.length / 4)
            resize(a.length / 2);
        if (n == 0)
            head = tail + 2;
        return item;
    }

    @Override
    @Nonnull
    public Iterator<Item> iterator() {
        return new ResizingArrayDequeueIterator();
    }

    private class ResizingArrayDequeueIterator implements Iterator<Item> {
        private int current = head;
        private int remaining = n;

        @Override
        public boolean hasNext() {
            return remaining > 0;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("no more items");
            Item item = a[current++];
            if (current == a.length)
                current = 0;
            remaining--;
            return item;
        }
    }

    public static void main(String[] args) {
        ResizingArrayDeque<Integer> deque = new ResizingArrayDeque<>();

        for (int k = 0; k  < 2; k++) {
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
