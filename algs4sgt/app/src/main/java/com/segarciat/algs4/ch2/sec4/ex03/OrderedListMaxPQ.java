package com.segarciat.algs4.ch2.sec4.ex03;

import com.segarciat.algs4.ch2.SortUtil;
import com.segarciat.algs4.ch2.sec4.IMaxPQ;

import java.util.NoSuchElementException;

/**
 * <strong>2.4.3)</strong>
 * (Max) priority queue implementation using an ordered list.
 * The time complexity of getting and removing the maximum is O(1),
 * and that of inserting an item is O(n).
 * @param <Key> The type of the items in the list.
 * @author Sergio E. Garcia Tapia
 */
public class OrderedListMaxPQ<Key extends Comparable<Key>> implements IMaxPQ<Key> {
    /**
     * The head of the list.
     */
    private Node<Key> first = null;
    /**
     * The number of items  in the list.
     */
    private int n = 0;

    private static class Node<Key> {
        Node<Key> next;
        Key key;
    }

    /**
     * @return <code>true</code> if the list does not have items, and <code>false</code> otherwise.
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * @return The number of keys in the list.
     */
    public int size() {
        return n;
    }

    /**
     * Adds the given key to the list and maintains the list in order.
     * @param keyToInsert The item to add to the list.
     * @throws NullPointerException if the given item is <code>null</code>.
     */
    public void insert(Key keyToInsert) {
        if (keyToInsert == null)
            throw new NullPointerException("key may not be null");

        // invariant: list is always in non-increasing order

        // find a node with an equal or smaller key; it comes after prev
        Node<Key> prev = null;
        for (var current = first; current != null; current = current.next) {
            if (!SortUtil.less(keyToInsert, current.key))
                break;
            prev = current;
        }

        // insert node with new key before equal or smaller key
        Node<Key> nodeToInsert = new Node<>();
        nodeToInsert.key = keyToInsert;
        if (prev == null) {
            nodeToInsert.next = first;
            first = nodeToInsert;
        } else {
            nodeToInsert.next = prev.next;
            prev.next = nodeToInsert;
        }
        n++;
    }

    /**
     * @return The maximum key in the list.
     * @throws NoSuchElementException if the list is empty.
     */
    public Key max() {
        if (isEmpty())
            throw new NoSuchElementException("the queue is empty");
        return first.key;
    }

    /**
     * Removes the largest key from the list.
     * @return The largest key.
     * @throws NoSuchElementException if the list is empty.
     */
    public Key delMax() {
        if (isEmpty())
            throw new NoSuchElementException("the queue is empty");
        Key maxKey = first.key;
        first = first.next;
        return maxKey;
    }
}
