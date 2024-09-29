package com.segarciat.algs4.ch1.sec3.ex36;

import edu.princeton.cs.algs4.StdRandom;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <strong>1.3.36)</strong>
 * @param <Item> The type of each item in the queue.
 */
public class RandomQueue<Item> implements Iterable<Item> {
    private int n;
    private Item[] a;

    public RandomQueue() {
        a = (Item[]) new Object[1];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < n; i++)
            temp[i] = a[i];
        a = temp;
    }

    public void enqueue(Item item) {
        if (item == null)
            throw new NullPointerException("cannot add null");
        if (n == a.length)
            resize(a.length * 2);
        a[n++] = item;
    }

    private void swap(int i, int j) {
        Item temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException("no more items");
        int r = StdRandom.uniformInt(0, n);
        swap(r, n - 1);
        return a[n - 1];
    }

    public Item dequeue() {
        Item item = sample();
        a[--n] = null; // avoid loitering
        if (n > 0 && n == a.length / 4)
            resize(a.length / 2);
        return item;
    }

    @Override
    @Nonnull
    public Iterator<Item> iterator() {
        return new RandomQueueIterator();
    }

    private class RandomQueueIterator implements Iterator<Item> {
        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < n;
        }

        @Override
        public Item next() {
            int r = StdRandom.uniformInt(current, n);
            swap(current, r);
            Item item = a[current++];
            return item;
        }
    }

    public static void main(String[] args) {
        RandomQueue<Integer> queue = new RandomQueue<>();
        for (int i = 0; i < 10; i++)
            queue.enqueue(i);
        for (int i: queue)
            System.out.print(i + " ");
        System.out.println();
    }
}
