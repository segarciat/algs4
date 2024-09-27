package com.segarciat.algs4.ch1.sec3.ex25;

import edu.princeton.cs.algs4.StdOut;

/**
 * <strong>1.3.25)</strong>
 * Write a method <code>insertAfter()</code> that takes two linked-list
 * <code>Node</code> arguments and inserts the second after the first
 * on its list (and does nothing if either argument is null).
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
            insertAfter(oldLast, last);
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
            removeAfter(prev);
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

    private void removeAfter(Node node) {
        if (node == null || node.next == null)
            return;
        node.next = node.next.next;
    }

    private void insertAfter(Node before, Node newNode) {
        if (before == null || newNode == null)
            return;
        newNode.next = before.next;
        before.next = newNode;
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        for (int i = 0; i < 5; i++) {
            StdOut.printf("Inserting %d \n", i);
            list.add(i);
        }
        StdOut.printf("Deleting element at index 4: %d\n", list.delete(4));
        StdOut.printf("Deleting element at index 2: %d\n", list.delete(2));
        StdOut.print("Deleting the rest: ");
        while (!list.isEmpty())
            StdOut.print(list.delete(0) + " ");
        StdOut.println();
    }
}
