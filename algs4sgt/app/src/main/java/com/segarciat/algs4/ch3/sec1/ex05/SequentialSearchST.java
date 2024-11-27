package com.segarciat.algs4.ch3.sec1.ex05;

import edu.princeton.cs.algs4.Queue;

import java.util.NoSuchElementException;

/**
 * <strong>3.1.5)</strong>
 * Implementation of a symbol table using an unordered linked list
 * and sequential search. Based on Algorithm 3.1 of "Algorithms"
 * by Sedgewick and Wayne (page 375), except for the methods
 * <code>size()</code>, <code>delete()</code>, and <code>keys()</code>,
 * which were implemented for this exercise.
 * @author Sergio E. Garcia Tapia
 */
public final class SequentialSearchST <Key, Value> {
    private Node<Key, Value> first = null;
    private int n = 0;

    private static class Node <Key, Value> {
        Key key;
        Value val;
        Node<Key, Value> next;

        Node(Key key, Value val, Node<Key, Value> next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Value get(Key key) {
        if (key == null)
            return null;

        for (var current = first; current != null; current = current.next) {
            if (current.key.equals(key))
                return current.val;
        }
        // Key not found
        return null;
    }

    public void put(Key key, Value val) {
        if (key == null)
            throw new NullPointerException("cannot add a null key");

        if (val == null) {
            delete(key);
            return;
        }

        // See if node is already present
        for (var current = first; current != null; current = current.next) {
            if (current.key.equals(key)) {
                current.val = val;
                return;
            }
        }

        // Prepend the node
        first = new Node<>(key, val, first);
        n++;
    }

    public void delete(Key key) {
        if (key == null)
            throw new NullPointerException("cannot delete null");
        if (first == null)
            throw new NoSuchElementException("queue is empty");

        // Find the key to delete.
        Node<Key, Value> current = first;
        Node<Key, Value> prev = null;
        while (current != null && !current.key.equals(key)) {
            prev = current;
            current = current.next;
        }

        if (current == null)
            throw new NoSuchElementException("no matching key");

        // Remove the node
        if (prev == null) {
            first = first.next;
        } else {
            prev.next = current.next;
        }
        n--;
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (var current = first; current != null; current = current.next)
            queue.enqueue(current.key);
        return queue;
    }
}

