package com.segarciat.algs4.ch1.sec3.ex30;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <strong>1.3.30)</strong>
 * Write a function that takes the first <code>Node</code> in a linked
 * list as argument and (destructively) reverses the list, returning
 * the first <code>Node</code> in the result.
 * <p>
 * Solution: Items added so that the most recently added item is "last"
 * @param <Item> Type of Item to be added.
 */
public class LinkedList<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int n;

    private class Node {
        Node next;
        Item item;
    }

    /**
     * Returns the number of elements in the list.
     * @return the size of the list.
     */
    public int size() {
        return n;
    }

    /**
     * Decides whether the list is empty.
     * @return true if there are no elements in the list, false otherwise.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Adds the given item to the end of the list.
     *
     * @param item The item to add.
     */
    public void add(Item item) {
        if(item == null)
            throw new NullPointerException("cannot add null items");
        n++;
        Node oldLast = last;

        last = new Node();
        last.item = item;
        last.next = null;

        if (oldLast == null)
            first = last;
        else
            oldLast.next = last;
    }

    public void reverse() {
        first = reverse(first);
    }

    private Node reverse(Node start) {
        if (start == null)
            return null;
        if (start.next == null)
            return start;
        Node newFirst = reverse(start.next);
        start.next.next = start;
        start.next = null;
        return newFirst;
    }

    @Override
    @Nonnull
    public Iterator<Item> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("no more elements");
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10; i++)
            list.add(i);

        for (Integer i: list)
            System.out.print(i + " ");
        System.out.println();

        list.reverse();
        for (Integer i: list)
            System.out.print(i + " ");
        System.out.println();
    }
}
