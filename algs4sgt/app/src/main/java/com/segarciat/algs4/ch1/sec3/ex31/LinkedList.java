package com.segarciat.algs4.ch1.sec3.ex31;

/**
 * <strong>1.3.31)</strong>
 * Implement a nested class <code>DoubleNode</code> for building doubly-linked lists, where
 * each node contains a reference to the item preceding it and the item following it
 * in the list (<code>null</code> if there is no such item). Then implement a static method
 * for the following tasks: insert at the beginning, insert at the end, remove from the
 * beginning, remove from the end, insert before a given node, insert after a given node,
 * and remove a given node.
 */
public class LinkedList<Item> {
    private static class DoubleNode<Item> {
        Item item;
        DoubleNode<Item> prev;
        DoubleNode<Item> next;

        static <Item> void insertBeginning(DoubleNode<Item> start, DoubleNode<Item> newStart) {
            if (start == null || newStart == null)
                return;
            newStart.next = start;
            newStart.prev=  null;
            start.prev = newStart;
        }

        static <Item> void insertEnd(DoubleNode<Item> last, DoubleNode<Item> newLast) {
            if (last == null || newLast == null)
                return;
            last.next = newLast;
            newLast.prev = last;
            newLast.next = null;
        }

        static <Item> Item removeBeginning(DoubleNode<Item> start) {
            if (start == null || start.next == null)
                return null;
            Item item = start.item;
            DoubleNode<Item> second = start.next;
            second.prev = null;
            start.next = null;
            return item;
        }

        static <Item> Item removeEnd(DoubleNode<Item> end) {
            if (end == null || end.prev == null)
                return null;
            Item item = end.item;
            DoubleNode<Item> secondToLast = end.prev;
            secondToLast.next = null;
            end.prev = null;
            return item;
        }

        static <Item> void insertBefore(DoubleNode<Item> toInsert, DoubleNode<Item> node) {
            if (node == null || toInsert == null)
                return;
            DoubleNode<Item> before = node.prev;
            node.prev = toInsert;
            toInsert.prev = before;
            toInsert.next = node;
            if (before != null)
                before.next = toInsert;
        }

        static <Item> void insertAfter(DoubleNode<Item> node, DoubleNode<Item> toInsert) {
            if (node == null || toInsert == null)
                return;
            DoubleNode<Item> after = node.next;
            node.next = toInsert;
            toInsert.prev = node;
            toInsert.next = after;
            if (after != null)
                after.prev = toInsert;
        }

        static <Item> Item remove(DoubleNode<Item> node) {
            if (node == null)
                return null;
            Item item = node.item;
            DoubleNode<Item> before = node.prev;
            DoubleNode<Item> after = node.next;
            node.prev = null;
            node.next = null;
            if (before != null)
                before.next = after;
            if (after != null)
                after.prev = before;
            return item;
        }
    }
}
