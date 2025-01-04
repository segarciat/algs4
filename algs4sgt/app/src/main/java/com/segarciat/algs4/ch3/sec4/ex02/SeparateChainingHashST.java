package com.segarciat.algs4.ch3.sec4.ex02;

import java.util.NoSuchElementException;

/**
 * <strong>3.4.2</strong> Implements {@link edu.princeton.cs.algs4.SeparateChainingHashST}
 * by directly using the linked-list code from {@link edu.princeton.cs.algs4.SequentialSearchST}.
 *
 * @author Sergio E. Garcia Tapia
 */
public final class SeparateChainingHashST <Key, Value> {
    private final int m;
    private int n;
    private final Node<Key, Value>[] st;

    private static class Node<Key, Value> {
        Key key;
        Value val;
        Node<Key, Value> next;

        public Node(Key key, Value val, Node<Key, Value> next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public SeparateChainingHashST(int m) {
        st = (Node<Key, Value>[]) new Node[m];
        this.m = m;
    }

    public SeparateChainingHashST() {
        this(997);
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    public Value get(Key key) {
        if (key == null)
            return null;

        for (Node<Key, Value> x = st[hash(key)]; x != null; x = x.next) {
            if (x.key.equals(key))
                return x.val;
        }

        return null;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public void put(Key key, Value val) {
        if (key == null)
            throw new NullPointerException("cannot use a null key");

        // find the node
        int h = hash(key);
        for (Node<Key, Value> x = st[h]; x != null; x = x.next) {
            if (x.key.equals(key)) {
                x.val = val;
                return;
            }
        }
        Node<Key, Value> head = new Node<>(key, val, st[h]);
        st[h] = head;
        n++;
    }

    public void delete(Key key) {
        if (key == null)
            throw new NullPointerException("key cannot be null");

        int h = hash(key);
        Node<Key, Value> prev = null;
        Node<Key, Value> current = st[h];
        while (current != null && !current.key.equals(key)) {
            prev = current;
            current = current.next;
        }

        // did not find the key
        if (current == null)
            throw new NoSuchElementException("key does not exist");

        if (prev == null)
            st[h] = current.next;
        else
            prev.next = current.next;
        n--;
    }
}
