package com.segarciat.algs4.ch2.sec4.ex03;

import com.segarciat.algs4.ch2.SortUtil;
import com.segarciat.algs4.ch2.sec4.IMaxPQ;

import java.util.NoSuchElementException;

/**
 * <strong>2.4.3)</strong>
 * (Max) priority queue implementation using an unordered list.
 * The time complexity of getting and removing the maximum is O(n),
 * and that of inserting an item is O(1).
 * @param <Key> The type of the items in the list.
 * @author Sergio E. Garcia Tapia
 */
public class UnorderedListMaxPQ<Key extends Comparable<Key>> implements IMaxPQ<Key> {
    /**
     * The head of the list.
     */
    private Node<Key> first = null;

    /**
     * The number of nodes in the list.
     */
    private int n = 0;

    private static class Node<Key> {
        Node<Key> next;
        Key key;
    }

    /**
     * @return <code>true</code> if the list has no items, <code>false</code> otherwise.
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * @return The number of items in the list.
     */
    public int size() {
        return n;
    }

    /**
     * Inserts the given item to the list.
     * @param v The item to insert.
     * @throws NullPointerException if the item is <code>null</code>.
     */
    public void insert(Key v) {
        if (v == null)
            throw new NullPointerException("cannot add null");
        // add items at the front of the list.
        Node<Key> newFirst = new Node<>();
        newFirst.key = v;
        newFirst.next = first;
        first = newFirst;
        n++;
    }

    /**
     * @return The largest key from the list.
     * @throws NoSuchElementException if the list is empty.
     */
    public Key max() {
        return putMaxAtStart();
    }

    /**
     * Removes the largest key from the list.
     * @return The largest key.
     * @throws NoSuchElementException if the list is empty.
     */
    public Key delMax() {
        Key maxKey = putMaxAtStart();
        first = first.next;
        n--;
        return maxKey;
    }

    /**
     * Finds the node with the maximum key and swaps its key
     * with that of the first node in the list.
     * @return The maximum key.
     * @throws NoSuchElementException if the queue is empty.
     */
    private Key putMaxAtStart() {
        if (isEmpty())
            throw new NoSuchElementException("queue is empty");

        // Find the max key
        Key maxSoFar = first.key;
        Node<Key> nodeBeforeMax = null;
        for (var current = first; current.next != null; current = current.next) {
            if (SortUtil.less(maxSoFar, current.next.key)) {
                maxSoFar = current.next.key;
                nodeBeforeMax = current;
            }
        }
        // Swap it with the first node's key
        if (nodeBeforeMax != null) {
            nodeBeforeMax.next.key = first.key;
            first.key = maxSoFar;
        }
        return maxSoFar;
    }
}
