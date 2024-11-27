package com.segarciat.algs4.ch3.sec1.ex03;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

/**
 * <strong>3.1.3)</strong>
 * An implementation of an ordered symbol table using a linked list.
 * @author Sergio E. Garcia Tapia
 */
public final class OrderedSequentialSearchST <Key extends Comparable<Key>, Value> {
    private Node<Key, Value> first = null;
    private Node<Key, Value> last = null;
    private int n = 0;

    private static class Node<Key, Val> {
        Node<Key, Val> next;
        Key key;
        Val val;

        public Node(Key key, Val val) {
            this.key = key;
            this.val = val;
        }
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Value get(Key key) {
        if (key == null)
            return null;

        for (var current = first; current != null; current = current.next) {
            if (key.equals(current.key)) {
                return current.val;
            }
        }
        // key not present
        return null;
    }

    public void put(Key key, Value val) {
        if (key == null)
            throw new NullPointerException("cannot add a null key");
        if (val == null) {
            delete(key);
            return;
        }

        // Update val of key if key is present
        Node<Key, Value> current = first;
        Node<Key, Value> prev = null;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp == 0) {
                current.val = val;
                return;
            } else if (cmp > 0) {
                prev = current;
                current = current.next;
            } else { // insert before current
                break;
            }
        }

        // if we reach this point, must add new key before current
        Node<Key, Value> node = new Node<>(key, val);
        if (prev == null) {
            node.next = first;
            first = node;
        } else { // prev -> node -> current
            node.next = prev.next;
            prev.next = node;
        }

        if (current == null) {
            last = prev;
        }
        n++;
    }

    public void delete(Key key) {
        if (key == null)
            throw new NullPointerException("cannot delete a null key");
        if (first == null)
            throw new NoSuchElementException("the symbol table is empty");

        // Find matching key
        Node<Key, Value> current = first;
        Node<Key, Value> prev = null;
        while (current != null && !key.equals(current.key)) {
            prev = current;
            current = current.next;
        }

        if (current == null)
            throw new NoSuchElementException("invalid key");

        // delete current
        if (prev == null) {
            first = first.next;
        } else {
            prev.next = current.next;
        }

        if (current.next == null)
            last = prev;
        n--;
    }

    public void deleteMin() {
        delete(min());
    }

    public void deleteMax() {
        delete(max());
    }

    public Key min() {
        if (isEmpty())
            throw new NoSuchElementException("the symbol table is empty");
        return first.key;
    }

    public Key max() {
        if (isEmpty())
            throw new NoSuchElementException("the symbol table is empty");
        return last.key;
    }

    public Key floor(Key key)   {
        if (key == null)
            throw new NullPointerException("cannot use a null key");
        if (isEmpty())
            throw new NoSuchElementException("the symbol table is empty");

        Node<Key, Value> prev = null;
        Node<Key, Value> current = first;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp == 0) {
                return key;
            } else if (cmp > 0) {
                prev = current;
                current = current.next;
            } else { // first key larger than the argument
                break;
            }
        }

        if (prev == null)
            return null;
        return prev.key;
    }

    public Key ceiling(Key key) {
        if (key == null)
            throw new NullPointerException("cannot use a null key");
        if (isEmpty())
            throw new NoSuchElementException("the symbol table is empty");

        Node<Key, Value> current = first;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp == 0) {
                return key;
            } else if (cmp > 0) {
                current = current.next;
            } else { // first key larger than the argument
                break;
            }
        }

        if (current == null)
            return null;
        return current.key;
    }

    public int rank(Key key) {
        if (key == null)
            throw new NullPointerException("cannot have a null key");
        if (isEmpty())
            throw new NoSuchElementException("symbol table is empty");
        int count = 0;

        for (var current = first; current != null; current = current.next) {
            int cmp = key.compareTo(current.key);
            if (cmp <= 0) {
                break;
            } else {
                count++;
            }
        }
        return count;
    }

    public Key select(int k) {
        if (k < 0 || k >= n)
            throw new IllegalArgumentException("invalid rank");
        var current = first;
        for (int i = 0; i < k; i++) {
            current = current.next;
        }
        return current.key;
    }

    public int size(Key lo, Key hi) {
        if (lo == null || hi == null)
            throw new NullPointerException("keys cannot be null");
        if (hi.compareTo(lo) < 0)
            return 0;
        else if (contains(hi))
            return rank(hi) - rank(lo) + 1;
        else
            return rank(hi) - rank(lo);
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null || hi == null)
            throw new NullPointerException("keys cannot be null");

        Queue<Key> queue = new Queue<>();
        if (hi.compareTo(lo) < 0)
            return queue;

        var current = first;
        while (current != null) {
            int cmp = current.key.compareTo(lo);
            if (cmp >= 0)
                break;
            current = current.next;
        }

        while (current != null) {
            int cmp = current.key.compareTo(hi);
            if (cmp > 0)
                break;
            queue.enqueue(current.key);
            current = current.next;
        }
        return queue;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    /**
     * Reads words from standard input and displays their frequency in no
     * particular order.
     */
    public static void main(String[] args) {
        OrderedSequentialSearchST<String, Integer> st = new OrderedSequentialSearchST<>();
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString().toUpperCase();
            if (!st.contains(key)) {
                st.put(key, 1);
            } else {
                st.put(key, st.get(key) + 1);
            }
        }

        for (String key: st.keys()) {
            StdOut.printf("%s: %d%n", key, st.get(key));
        }
    }
}
