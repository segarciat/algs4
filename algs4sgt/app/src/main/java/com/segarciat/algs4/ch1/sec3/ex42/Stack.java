package com.segarciat.algs4.ch1.sec3.ex42;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <strong>1.3.42</strong>
 */
public class Stack<Item> implements Iterable<Item> {

    private Node first;
    private int n;

    private class Node {
        private Node next;
        private Item item;
    }

    /**
     * Creates an empty stack
     */
    public Stack() {
        // Default constructor
    }

    /**
     * Creates an independent copy of <code>stack</code>
     * @param stack The stack to copy.
     */
    public Stack(Stack<Item> stack) {
        copy(this, stack.first);
    }

    private void copy(Stack<Item> stack, Node node) {
        if (node == null)
            return;
        copy(stack, node.next);
        stack.push(node.item);
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void push(Item item) {
        if (item == null)
            throw new NullPointerException("cannot push null item");
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        n++;
    }

    public Item pop() {
        if (isEmpty())
            throw new NoSuchElementException("stack is empty");
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    @Override
    @Nonnull
    public Iterator<Item> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("No more items");
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 10; i++)
            stack.push(i);
        Stack<Integer> copy = new Stack<>(stack);
        while (!stack.isEmpty())
            System.out.print(stack.pop() + " ");
        System.out.println();
        System.out.printf("stack size=%d, copy size=%d%n", stack.size(), copy.size());
        while (!copy.isEmpty())
            System.out.print(copy.pop() + " ");
        System.out.println();
    }
}
