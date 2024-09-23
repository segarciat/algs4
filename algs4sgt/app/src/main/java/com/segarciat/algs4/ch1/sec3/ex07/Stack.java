package com.segarciat.algs4.ch1.sec3.ex07;

import java.util.Iterator;

/**
 * <strong>1.3.7</strong>
 * Add a method <code>peek()</code> to <code>Stack</code> that returns the most
 * recently inserted item on the stack (without popping it).
 * Implementation is from Algorithm 1.2 in Algorithms by Sedgewick and Wayne,
 * for the exception of <code>peek()</code>.
 */
public class Stack<Item> implements Iterable<Item>{
    private Node first;
    private int n;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public void push(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    public Item peek() {
        if (isEmpty())
            return null;
        return first.item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {}

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
