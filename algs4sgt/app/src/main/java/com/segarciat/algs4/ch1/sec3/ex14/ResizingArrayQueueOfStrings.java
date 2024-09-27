package com.segarciat.algs4.ch1.sec3.ex14;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

/**
 * <code>1.3.14</code>
 * Develop a class <code>ResizingArrayQueueOfStrings</code> that implements the
 * queue abstraction with a fixed-size array, and then extend your implementation
 * to use array resizing to remove the size restriction.
 */
public class ResizingArrayQueueOfStrings {
    private String[] a;
    private int head;
    private int tail;
    private int n;

    public ResizingArrayQueueOfStrings() {
        a = new String[2];
        head = 0;
        tail = -1;
        n = 0;
    }

    /**
     * Returns <code>true</code> if the queue contains no elements,
     * and <code>false</code> otherwise.
     * @return whether the string contains elements.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * The number of elements on the queue.
     * @return The number of elements on the queue.
     */
    public int size() {
        return n;
    }

    /**
     * Resizes the array to be of size <code>max</code> and migrates all
     * elements to the new container.
     * @param max New size of array.
     */
    private void resize(int max) {
        String[] temp = new String[max];
        for (int i = 0; i < n; i++) {
            int wrapAroundAwareIndex = ((head + i) == a.length) ? i - 1 : head + i;
            temp[i] = a[wrapAroundAwareIndex];
        }
        head = 0;
        tail = n - 1;
        a = temp;
    }

    /**
     * Adds strings <code>s</code> to queue.
     * @param s String to be added to queue.
     */
    public void enqueue(String s) {
        if (n == a.length)
            resize(a.length * 2);
        if (tail == a.length - 1)
            tail = -1;
        n++;
        a[++tail] = s;
    }

    /**
     * Removes the string at the front of the queue and returns it.
     * @return The string at the front of the queue
     * @throws NoSuchElementException if the queue is empty.
     */
    public String dequeue() {
        if (n == 0)
            throw new NoSuchElementException("queue is empty");
        n--;
        String s = a[head++];
        a[head - 1] = null; // Avoid loitering
        if (head == a.length)
            head = 0;
        if (n == a.length / 4)
            resize(a.length / 2);
        return s;
    }

    public static void main(String[] args) {
        ResizingArrayQueueOfStrings q = new ResizingArrayQueueOfStrings();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-"))
                StdOut.println(q.dequeue());
            else
                q.enqueue(s);
        }
    }
}
