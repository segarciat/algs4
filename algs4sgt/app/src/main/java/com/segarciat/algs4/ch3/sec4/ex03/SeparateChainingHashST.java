package com.segarciat.algs4.ch3.sec4.ex03;

import java.util.NoSuchElementException;

/**
 * <strong>3.4.3</strong> Implements {@link edu.princeton.cs.algs4.SeparateChainingHashST}
 * by directly using the linked-list code from {@link edu.princeton.cs.algs4.SequentialSearchST}.
 *
 * @author Sergio E. Garcia Tapia
 */
public final class SeparateChainingHashST<Key, Value> {
    private final int m;
    private int n;
    private final Node<Key, Value>[] st;

    private static class Node<Key, Value> {
        Key key;
        Value val;
        Node<Key, Value> next;
        int count = 0;

        public Node(Key key, Value val, Node<Key, Value> next, int count) {
            this.key = key;
            this.val = val;
            this.next = next;
            this.count = count;
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
        Node<Key, Value> head = new Node<>(key, val, st[h], n++);
        st[h] = head;
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

    /**
     * Deletes all nodes in the list whose count is greater than k.
     */
    public void deleteGreater(int k) {
        for (int i = 0; i < st.length; i++)
            st[i] = deleteGreater(st[i], k);
    }

    /**
     * Helper method for {@link #deleteGreater(int)}. Deletes all
     * nodes whose count is greater than k from the linked list pointed
     * to by head.
     * @param head The head of the list.
     * @param k The threshold value.
     * @return The new head of the list.
     */
    private Node<Key, Value> deleteGreater(Node<Key, Value> head, int k) {
        // delete from the start
        while (head != null && head.count > k) {
            head = head.next;
            n--;
        }

        if (head == null)
            return null;

        // if we reached this point, head is not greater than k
        Node<Key, Value> prev = head;
        Node<Key, Value> curr = head.next;
        while (curr != null) {
            if (curr.count > k) {
                prev.next = curr.next;
                n--;
            } else {
                prev = curr;
            }
            curr = curr.next;
        }

        return head;
    }
}
