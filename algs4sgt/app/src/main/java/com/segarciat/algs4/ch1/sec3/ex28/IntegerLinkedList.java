package com.segarciat.algs4.ch1.sec3.ex28;

/**
 * <strong>1.3.28)</strong>
 * Develop a recursive solution to the previous question.
 * <p>
 * Solution: Items added so that the most recently added item is "last"
 */
public class IntegerLinkedList {
    private Node first;
    private Node last;
    private int n;

    private static class Node {
        Node next;
        int val;
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
     * Adds the given value to the end of the list.
     *
     * @param val The value to add, which must be positive.
     * @throws IllegalArgumentException if the value given is not positive.
     */
    public void add(int val) {
        if (val <= 0)
            throw new IllegalArgumentException("key must be a positive integer");
        n++;
        Node oldLast = last;

        last = new Node();
        last.val = val;
        last.next = null;

        if (oldLast == null)
            first = last;
        else
            oldLast.next = last;
    }

    /**
     * @return The maximum value in the list, or 0 if the list is empty.
     */
    public int max() {
        return max(first);
    }

    private int max(Node start) {
        if (start == null)
            return 0;
        return Math.max(start.val, max(start.next));
    }
}
