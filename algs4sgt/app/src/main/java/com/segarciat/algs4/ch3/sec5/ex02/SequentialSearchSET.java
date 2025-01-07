package com.segarciat.algs4.ch3.sec5.ex02;

import java.util.NoSuchElementException;

/**
 * <strong>3.5.1</strong>
 * Implements a set using {@link edu.princeton.cs.algs4.SequentialSearchST} as
 * a starting point.
 * @author Sergio E. Garcia Tapia
 */
public class SequentialSearchSET <Key> {
    private int n;
    private Node<Key> first;

    private static class Node<Key> {
        Key key;
        Node<Key> next;

        public Node(Key key, Node<Key> next) {
            this.key = key;
            this.next = next;
        }
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public boolean contains(Key key) {
        if (key == null)
            return false;

        for (Node<Key> x = first; x != null; x = x.next)
            if (x.key.equals(key))
                return true;
        return false;
    }

    public void add(Key key) {
        if (key == null)
            throw new NullPointerException("cannot add a null item");

        for (Node<Key> x = first; x != null; x = x.next)
            if (x.key.equals(key))
                return;

        first = new Node<>(key, first);
        n++;
    }

    public void delete(Key key) {
        if (key == null)
            throw new NullPointerException("cannot delete null key");

        Node<Key> prev = null;
        Node<Key> curr = first;

        while (curr != null && !curr.key.equals(key)) {
            prev = curr;
            curr = curr.next;
        }

        if (curr == null)
            throw new NoSuchElementException("key does not belong to set");

        if (prev == null)
            first = null;
        else
            prev.next = curr.next;
        n--;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        for (Node<Key> x = first; x != null; x = x.next) {
            sb.append(x.key.toString()).append(", ");
        }
        sb.append(" }");
        return sb.toString();
    }
}
