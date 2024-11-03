package com.segarciat.algs4.ch2.sec4;

import java.util.NoSuchElementException;

/**
 * Represents a priority queue abstract data type.
 * The API is as specified in Section 2.4 of Algorithms by
 * Sedgewick and Wayne.
 *
 * @param <Key> The type of items in the queue.
 *
 * @author Sergio E. Garcia Tapia
 */
public interface IMaxPQ <Key extends Comparable<Key>>{
    /**
     * @return The number of items in the queue.
     */
    int size();

    /**
     * @return <code>true</code> if the queue is empty, <code>false</code> otherwise.
     */
    boolean isEmpty();

    /**
     * Inserts the given item to the priority queue.
     * @param key The item to insert.
     */
    void insert(Key key);

    /**
     * Gets the largest item in the queue, but does not remove it.
     * @return The largest key in the queue.
     * @throws NoSuchElementException If the queue is empty.
     */
    Key max() throws NoSuchElementException;

    /**
     * Gets the largest ite in the queue and removes it.
     * @return The largest item in the queue.
     * @throws NoSuchElementException If the queue is empty.
     */
    Key delMax() throws NoSuchElementException;
}
