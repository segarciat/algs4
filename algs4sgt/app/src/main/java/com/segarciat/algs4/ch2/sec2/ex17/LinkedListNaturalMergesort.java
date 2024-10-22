package com.segarciat.algs4.ch2.sec2.ex17;

import com.segarciat.algs4.ch2.SortUtil;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * @author Sergio E. Garcia Tapia
 */
public class LinkedListNaturalMergesort {
    private LinkedListNaturalMergesort() {}
    private static class Node<T extends Comparable<T>> {
        Node<T> next;
        T item;
    }

    /**
     * Finds the node with the smallest item, removes it, and reads it as the head
     * of the list.
     *
     * @param first The head of the linked list.
     * @return The node with a minimum item, now the head of the list
     * @param <T> The type of the item in a node.
     */
    private static <T extends Comparable<T>> Node<T> findAndSetMin(Node<T> first) {
        if (first == null || first.next == null)
            return first;

        Node<T> prevSmallest = null;
        Node<T> current = first;
        Node<T> smallest = first;
        while (current.next != null) {
            if (SortUtil.less(current.next.item, smallest.item)) {
                prevSmallest = current;
                smallest = current.next;
            }
            current = current.next;
        }
        // Remove from list and reinsert it in the front.
        if (prevSmallest != null) {
            prevSmallest.next = smallest.next;
            smallest.next = first;
        }
        return smallest;
    }

    /**
     * Sorts the given linked list in-place, and returns the new head of the list.
     * @param first The head of the list.
     * @return A node in the original list which is now head of the sorted list
     * (possibly <code>first</code>, but could be another node).
     * @param <T> The type of the items in the nodes.
     */
    private static <T extends Comparable<T>> Node<T> sort(Node<T> first) {
        if (first == null)
            return null;

        first = findAndSetMin(first);
        Node<T> lo = first;
        Node<T> prevHi = null;
        while (true) {
            // Find maximal increasing sublist
            Node<T> mid = lo;
            while (mid.next != null && !SortUtil.less(mid.next.item, mid.item)) {
                mid = mid.next;
            }
            if (mid.next == null) {
                if (lo == first)  // first sublist encompasses entire list; it's sorted.
                    break;
                else {
                    // Restart search for increasing sequence from the beginning.
                    lo = first;
                    prevHi = null;
                    continue;
                }
            }

            // Find another adjacent maximal increasing sublist
            Node<T> hi = mid.next;
            while (hi.next != null && !SortUtil.less(hi.next.item, hi.item)) {
                hi = hi.next;
            }

            Node<T> temp = hi.next;
            hi = merge(lo, mid, hi);
            // hi from previous iteration must point to new low
            if (prevHi != null) {
                prevHi.next = hi.next; // hi.next is the new lo
            }
            prevHi = hi;
            hi.next = temp;

            lo = temp;
            if (lo == null) {
                lo = first;
                prevHi = null;
            }
        }
        return first;
    }

    /**
     * Given a list <code>first -> ... -> mid -> ... -> hi</code>, where
     * <code>first -> ... -> mid</code> and <code>mid.next -> ... -> hi</code>
     * are both sorted sub-lists, merges them into a single sorted list.
     * Returns the tail of the merged list, such that it points to the head of the
     * merged list; that is, the result is a circular linked list.
     * <strong>NOTE</strong>: <code>hi.next</code> is eliminated, so the caller
     * <em>must</em> save it beforehand.
     *
     * @param lo The head of the first sorted sublist.
     * @param mid The tail of the first sorted sublist, which points to the head of the second sorted sublist.
     * @param hi The tail of the second sublist. The value of <code>hi.next</code>
     *           will be eliminated.
     * @return The node with the highest key (either <code>mid</code> or <code>hi</code>),
     * pointing to the first node (of smallest key) in the merged list (either <code>first</code>
     * or <code>mid.next</code>).
     * @param <T> The type of the items in the nodes.
     */
    private static <T extends Comparable<T>> Node<T> merge(Node<T> lo, Node<T> mid, Node<T> hi) {
        assert lo != mid.next;
        Node<T> x = lo;
        Node<T> y = mid.next;
        Node<T> newFirst;
        // Place smallest at the start
        if (SortUtil.less(y.item, x.item)) {
            newFirst = y;
            y = y.next;
        } else {
            newFirst = x;
            x = x.next;
        }
        var current = newFirst;

        while (x != mid.next && y != hi.next) {
            if (SortUtil.less(y.item, x.item)) {
                current.next = y;
                current = y;
                y = y.next;
            } else {
                current.next = x;
                current = x;
                x = x.next;
            }
        }
        // Make a circular linked list to enable access to both first and last easily.
        if (x != mid.next) {
            current.next = x;
            mid.next = newFirst;
            return mid;
        } else {
            current.next = y;
            hi.next = newFirst;
            return hi;
        }
    }

    private static <T extends Comparable<T>> boolean isSorted(Node<T> head, int size) {
        if (head == null)
            throw new NullPointerException("list cannot be null");
        if (size <= 0)
            throw new IllegalArgumentException("size should be positive");
        int n = 1;
        Node<T> current = head;
        while (current.next != null) {
            n++;
            if (SortUtil.less(current.next.item, current.item))
                return false;
            current = current.next;
        }
        return n == size;
    }

    /**
     * Performs a timed trial of the sort implemented by this class on
     * an array of <code>n</code> values of type <code>Double</code>.
     * @param n The size of the array for the experiment.
     * @return The time it took to sort an array of <code>n</code> random <code>Double</code>
     * values.
     */
    private static double timeTrial(int n) {
        Node<Double> head = new Node<>();
        head.item = StdRandom.uniformDouble();
        Node<Double> current = head;
        for (int i = 0; i < n - 1; i++) {
            Node<Double> node = new Node<>();
            node.item = StdRandom.uniformDouble();
            current.next = node;
            current = node;
        }
        Stopwatch timer = new Stopwatch();
        head = sort(head);
        double elapsed = timer.elapsedTime();
        assert isSorted(head, n);
        return elapsed;
    }

    public static void main(String[] args) {
        double prev = timeTrial(256);
        for (int n = 512; true; n *= 2) {
            double time = timeTrial(n);
            StdOut.printf("n=%d, ratio=%.1f%n", n, time / prev);
            prev = time;
        }
    }
}
