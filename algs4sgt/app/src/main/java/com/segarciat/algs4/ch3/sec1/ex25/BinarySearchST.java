package com.segarciat.algs4.ch3.sec1.ex25;

import edu.princeton.cs.algs4.Queue;

import java.util.NoSuchElementException;

/**
 * <strong>3.1.25)</strong>
 * Builds on Exercise 3.1.17) by caching the most recently
 * accessed key.
 * @author Sergio E. Garcia Tapia
 */
public final class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Item<Key, Value>[] items;
    private int n = 0;
    private int mostRecent = -1;

    public static class Item<Key extends Comparable<Key>, Value> implements Comparable<Item<Key, Value>> {
        private final Key key;
        private Value val;

        public Item(Key key, Value val) {
            this.key = key;
            this.val = val;
        }

        public Key key() {
            return key;
        }

        public Value val() {
            return val;
        }

        @Override
        public int compareTo(Item<Key, Value> item) {
            return this.key().compareTo(item.key());
        }
    }

    public BinarySearchST(Item<Key, Value>[] clientItems) {
        if (clientItems == null)
            throw new NullPointerException("array cannot be null");

        items = (Item<Key, Value>[]) new Item[clientItems.length == 0 ? 1 : clientItems.length];

        // Defensive copy
        for (int i = 0; i < clientItems.length; i++) {
            put(clientItems[i].key(), clientItems[i].val());
        }
    }

    private void resize(int newSize) {
        Item<Key, Value>[] temp = (Item<Key, Value>[]) new Item[items.length];
        for (int i = 0; i < n; i++)
            temp[i] = items[i];
        items = temp;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int rank(Key key) {
        if (key == null)
            throw new NullPointerException("null key has no rank");
        int lo = 0;
        int hi = n - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(items[mid].key());
            if (cmp < 0) {
                hi = mid - 1;
            } else if (cmp > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return lo;
    }

    public Value get(Key key) {
        if (key == null)
            throw new NullPointerException("cannot add a null key");
        // get from cache
        if (mostRecent >=0 && key.compareTo(items[mostRecent].key()) == 0) {
            return items[mostRecent].val();
        }

        int i = rank(key);
        if (i < n && key.compareTo(items[i].key()) == 0) {
            // search hit
            mostRecent = i;
            return items[i].val();
        } else {
            // search miss
            return null;
        }
    }

    public void put(Key key, Value val) {
        if (key == null)
            throw new NullPointerException("cannot add a null key");
        if (val == null) {
            delete(key);
            return;
        }

        int i = rank(key);
        if (i < n && key.compareTo(items[i].key()) == 0) {
            items[i].val = val;
            return;
        }
        if (n == items.length)
            resize(2 * n);

        // move items to make room for new item
        for (int j = n; j > i; j--) {
            items[j] = items[j - 1];
        }

        // to support caching; most recent moved up
        if (mostRecent >= i) {
            mostRecent++;
        }

        items[i] = new Item<>(key, val);
        n++;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Key min() {
        if (isEmpty())
            throw new NoSuchElementException("symbol table is empty");
        return items[0].key();
    }

    public Key max() {
        if (isEmpty())
            throw new NoSuchElementException("symbol table is empty");
        return items[n - 1].key();
    }

    public Key select(int k) {
        if (k < 0 || k >= n)
            throw new IllegalArgumentException("no key with such a rank");
        return items[k].key();
    }

    public Key ceiling(Key key) {
        int i = rank(key);
        return items[i].key();
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<>();
        for (int i = rank(lo); i < rank(hi); i++)
            queue.enqueue(items[i].key());
        if (contains(hi))
            queue.enqueue(hi);
        return queue;
    }

    public void delete(Key key) {
        if (key == null)
            throw new NullPointerException("cannot delete a null key");

        int i = rank(key);

        if (i >= n || key.compareTo(items[i].key()) != 0)
            throw new NoSuchElementException("key is not present");

        // shift all keys down, overwrite key-to-delete
        for (int j = n - 1; j > i; j--)
            items[j - 1] = items[j];

        // to support caching
        if (mostRecent >= i) {
            if (key.compareTo(items[mostRecent].key()) == 0) {
                // deleted cached key
                mostRecent = -1;
            } else {
                // cached key shifted down
                mostRecent--;
            }
        }

        items[--n] = null; // avoid loitering
        if (n == items.length / 4)
            resize(items.length / 2);
    }

    public Key floor(Key key) {
        if (key == null)
            throw new NullPointerException("cannot have floor of null");
        if (isEmpty())
            throw new NoSuchElementException("the symbol table is empty");

        int i = rank(key);
        if (i < n && key.compareTo(items[i].key()) == i) {
            return key;
        } else if (i == 0) {
            return null;
        } else {
            return items[i - 1].key();
        }
    }
}
