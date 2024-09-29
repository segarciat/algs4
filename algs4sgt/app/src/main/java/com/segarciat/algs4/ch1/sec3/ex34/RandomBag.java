package com.segarciat.algs4.ch1.sec3.ex34;

import edu.princeton.cs.algs4.StdRandom;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <strong>1.3.34)</strong>
 * <em>Random bag</em>. A <em>random bag</em> stores a collection of items
 * and supports the following API:
 * <pre>
 *     {@code
 *      public class RandomBag<Item> implements Iterable<Item>
 *          RandomBag()             // create an empty random bag
 *          boolean isEmpty()       // is the bag empty?
 *          int size()              // number of items in the bag
 *          void add(Item item)     // add an item
 *     }
 * </pre>
 * Write a class <code>RandomBag</code> that implements this API. Note that
 * this API is the same as <code>Bag</code>, except for the adjective
 * <em>random</em>, which indicates that the iteration should provide the
 * items in <code>random</code> order (all <em>n!</em> permutations equally
 * likely, for each iterator). <em>Hint</em>: Put the items in an array and
 * randomize their order in the iterator's constructor.
 * @param <Item> type of item to add to bag.
 */
public class RandomBag<Item> implements Iterable<Item> {
    private int n;
    private Item[] a;

    public RandomBag() {
        a = (Item[]) new Object[1];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void resize(int max) {
        Item[] temp = (Item[])  new Object[max];
        for (int i = 0; i < n; i++)
            temp[i] = a[i];
        a = temp;
    }

    public void add(Item item) {
        if (item == null)
            throw new NullPointerException("cannot add null");
        if (n == a.length)
            resize(a.length * 2);
        a[n++] = item;
    }

    @Override
    @Nonnull
    public Iterator<Item> iterator() {
        return new RandomBagIterator();
    }

    private class RandomBagIterator implements Iterator<Item> {
        private int current;

        RandomBagIterator() {
            StdRandom.shuffle(a, 0, n - 1);
            current = 0;
        }

        @Override
        public boolean hasNext() {
            return current < n;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("no more items");
            return a[current++];
        }
    }

    public static void main(String[] args) {
        RandomBag<Integer> bag = new RandomBag<>();
        for (int i = 0; i < 10; i++)
            bag.add(i);

        for (int i: bag)
            System.out.print(i + " ");
        System.out.println();
    }
}
