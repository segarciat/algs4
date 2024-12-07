package com.segarciat.algs4.ch3.sec2.ex13;

/**
 * <strong>3.2.13)</strong>
 * Implements a non-recursive version of the <code>get()</code> and
 * <code>put()</code> methods available in {@link edu.princeton.cs.algs4.BST},
 * based on recursive implementation given there.
 * @author Sergio E. Garcia Tapia
 */
public final class BSTNonRec <Key extends Comparable<Key>, Value>{
    private Node<Key, Value> root = null;

    private static class Node<Key, Value> {
        private final Key key;
        private Value val;
        private Node<Key, Value> left;
        private Node<Key, Value> right;
        private int n;

        Node(Key key, Value val, int n) {
            this.key = key;
            this.val = val;
            this.n = n;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node<Key, Value> node) {
        if (node == null)
            return 0;
        else
            return node.n;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public Value get(Key key) {
        if (key == null)
            throw new NullPointerException("no null keys allowed");

        Node<Key, Value> current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                return current.val;
            }
        }
        return null;
    }

    public void put(Key key, Value val) {
        if (key == null)
            throw new NullPointerException("cannot add a null key");

        Node<Key, Value> current = root;

        // update value for existing key, if any
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                current.val = val;
                return;
            }
        }

        // search for immediate parent for new node and update counts
        Node<Key, Value> parent = null;
        int cmp = 0;
        current = root;
        while (current != null) {
            current.n++;

            cmp = key.compareTo(current.key);
            parent = current;
            if (cmp < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        current = new Node<>(key, val, 1);
        if (root == null) {
            root = current;
        } else if (cmp < 0) {
            parent.left = current;
        } else {
            parent.right = current;
        }
    }
}
