package com.segarciat.algs4.ch1.sec3.ex21;

import edu.princeton.cs.algs4.StdOut;

/**
 * <strong>1.3.21)</strong>
 * Write a method <code>find()</code> that takes a linked list and a string
 * <code>key</code> as arguments and returns <code>true</code> if some node
 * in the list has <code>key</code> as its item field, <code>false</code>
 * otherwise.
 * <p>
 * Solution: Items added so that the most recently added item is "last"
 * @param <Item> Type of Item to be added.
 */
public class LinkedList<Item> {
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

    /**
     * Deletes the kth element from the list.
     *
     * @param k A non-negative integer less than the list size.
     *
     * @return The item removed from the list
     *
     * @throws IndexOutOfBoundsException if <code>k</code> is not in range.
     */
    public Item delete(int k) {
        if (k < 0 || k >= n)
            throw new IndexOutOfBoundsException("Invalid index for list");
        Node prev = null;
        Node current = first;
        for (int i = 0; i < k; i++) {
            prev = current;
            current = current.next;
        }
        Item item = current.item;
        if (prev == null)
            first = first.next;
        else
            prev.next = current.next;
        n--;
        return item;
    }

    public boolean find(Item key) {
        if (key == null)
            throw new NullPointerException("key cannot be null");
        for (Node current = first; current != null; current = current.next)
            if (key.equals(current.item))
                return true;
        return false;
    }

    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<String>();
        String[] friends = {
                "sam", "vivian", "yanni", "herbert", "thaysha", "juan", "brian", "emily"
        };
        for (String friend : friends) {
            list.add(friend);
            StdOut.println("New friend: " + friend);
        }
        StdOut.println();
        StdOut.println(list.find("yanni") ? "yanni is a friend" : "yanni is not a friend");
        StdOut.println(list.find("sean") ? "sean is a friend" : "sean is not a friend");
        StdOut.println(list.find("josh") ? "jack is a friend" : "jack is not a friend");
    }
}
