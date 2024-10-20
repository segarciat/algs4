package com.segarciat.algs4.ch2.sec2.ex17;

import com.segarciat.algs4.ch2.SortUtil;
import edu.princeton.cs.algs4.StdRandom;

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
     * Sorts the given linked list in-place, and returns the new head of the list.
     * @param first The head of the list.
     * @return A node in the original list which is now head of the sorted list
     * (possibly <code>first</code>, but could be another node).
     * @param <T> The type of the items in the nodes.
     */
    private static <T extends Comparable<T>> Node<T> sort(Node<T> first) {
        if (first == null)
            return null;

        Node<T> hi = first;
        while (hi.next != null) {
            // Find maximal increasing sublist
            Node<T> mid = hi;
            while (mid.next != null && !SortUtil.less(mid.next.item, mid.item))
                mid = mid.next;
            if (mid.next == null)
                break;

            // Find another adjacent maximal increasing sublist
            hi = mid.next;
            while (hi.next != null && !SortUtil.less(hi.next.item, hi.item))
                hi = hi.next;

            Node<T> temp = hi.next;
            hi = merge(first, mid, hi);
            first = hi.next;
            hi.next = temp;
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
     * @param first The head of the first sorted sublist.
     * @param mid The tail of the first sorted sublist, which points to the head of the second sorted sublist.
     * @param hi The tail of the second sublist. The value of <code>hi.next</code>
     *           will be eliminated.
     * @return The node with the highest key (either <code>mid</code> or <code>hi</code>),
     * pointing to the first node (of smallest key) in the merged list (either <code>first</code>
     * or <code>mid.next</code>).
     * @param <T> The type of the items in the nodes.
     */
    private static <T extends Comparable<T>> Node<T> merge(Node<T> first, Node<T> mid, Node<T> hi) {
        assert first != mid.next;

        Node<T> current;
        Node<T> x = first;
        Node<T> y = mid.next;
        if (SortUtil.less(mid.next.item, first.item)) {
            current = y;
            y = y.next;
        } else {
            current = x;
            x = x.next;
        }
        Node<T> newFirst = current;

        while (x != mid.next && y != hi.next) {
            if (SortUtil.less(y.item, x.item)) {
                current.next = y;
                y = y.next;
            } else {
                current.next = x;
                x = x.next;
            }
            current = current.next;
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

    private static <T extends Comparable<T>> void show(Node<T> first) {
        for (Node<T> x = first; x != null; x = x.next)
            System.out.print(x.item + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        Node<Integer> first = new Node<>();
        first.item = StdRandom.uniformInt(100);
        Node<Integer> x = first;
        for (int i = 0; i < 10; i++) {
            Node<Integer> temp = new Node<>();
            temp.item = StdRandom.uniformInt(100);
            x.next = temp;
            x = temp;
        }
        show(first);
        first = sort(first);
        show(first);
    }
}
