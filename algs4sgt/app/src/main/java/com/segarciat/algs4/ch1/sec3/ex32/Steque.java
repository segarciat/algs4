package com.segarciat.algs4.ch1.sec3.ex32;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <strong>1.3.32)</strong>
 * <em>Steque</em>. A stack-ended queue or <em>steque</em> is a data type that
 * supports <em>push</em>, <em>pop</em>, and <em>enqueue</em>. Articulate an API
 * for this ADT. Develop a linked list-based implementation.
 */
public class Steque<Item> implements Iterable<Item>{
    private int n;
    private Node<Item> first;
    private Node<Item> last;

    private static class Node<Item> {
        Node<Item> next;
        Item item;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void enqueue(Item item) {
        if (item == null)
            throw new NullPointerException("cannot enqueue null");

        Node<Item> oldLast = last;
        last = new Node<>();
        last.item = item;
        last.next = null;

        if (oldLast == null)
            first = last;
        else
            oldLast.next = last;
        n++;
    }

    public void push(Item item) {
        if (item == null)
            throw new NullPointerException("cannot push null");
        Node<Item> oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
        if (oldFirst == null)
            last = first;
        n++;
    }

    public Item pop() {
        if (isEmpty())
            throw new NoSuchElementException("container is empty");
        Item item = first.item;
        first = first.next;
        if (first == null)
            last = null;
        n--;
        return item;
    }

    @Override
    @Nonnull
    public Iterator<Item> iterator() {
        return new StequeIterator();
    }

    private class StequeIterator implements Iterator<Item> {
        private Node<Item> current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("no more items");
            Item item  = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Steque<Integer> steque = new Steque<>();
        for (int i = 5; i >= 0; i--)
            steque.enqueue(i);
        for (int j = 6; j <= 9; j++)
            steque.push(j);

        for (Integer i: steque)
            System.out.print(i + " ");
        System.out.println();

        while (!steque.isEmpty())
            System.out.print(steque.pop() + " ");
        System.out.println();
    }
}
