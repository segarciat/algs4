package com.segarciat.algs4.ch3.sec2.ex14;

import java.util.NoSuchElementException;

/**
 * <strong>3.2.14)</strong>
 * Implements non-recursive versions of methods in {@link edu.princeton.cs.algs4.BST},
 * continuing from {@link com.segarciat.algs4.ch3.sec2.ex13.BSTNonRec}.
 * @author Sergio E. Garcia Tapia
 */
public final class BSTNonRec<Key extends Comparable<Key>, Value>{
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
        if (cmp < 0) {
            parent.left = current;
        } else {
            parent.right = current;
        }

        if (root == null)
            root = current;
    }

    public Key min() {
        if (isEmpty())
            throw new NoSuchElementException("no minimum in empty tree");

        Node<Key, Value> current = root;
        while (current.left != null)
            current = current.left;

        return current.key;
    }

    public Key max() {
        if (isEmpty())
            throw new NoSuchElementException("no minimum in empty tree");

        Node<Key, Value> current = root;
        while (current.right != null)
            current = current.right;

        return current.key;
    }

    public Key floor(Key key) {
        if (key == null)
            throw new NullPointerException("no floor for null key");

        Node<Key, Value> floorNode = null;
        Node<Key, Value> current = root;

        while (current != null) {
            int cmp = key.compareTo(current.key);

            if (cmp > 0) {
                floorNode = current;
                current = current.right;
            }else if  (cmp < 0)
                current = current.left;
            else {
                return key;
            }
        }
        if (floorNode != null)
            return floorNode.key;
        throw new NoSuchElementException();
    }

    public Key ceiling(Key key) {
        if (key == null)
            throw new NullPointerException("no ceiling for null key");

        Node<Key, Value> ceilingNode = null;
        Node<Key, Value> current = root;

        while (current != null) {
            int cmp = key.compareTo(current.key);

            if (cmp < 0) {
                ceilingNode = current;
                current = current.left;
            } else if  (cmp > 0)
                current = current.right;
            else {
                return key;
            }
        }
        if (ceilingNode != null)
            return ceilingNode.key;
        throw new NoSuchElementException();
    }

    public int rank(Key key) {
        if (key == null)
            throw new NullPointerException("null key does not have a rank");

        Node<Key, Value> current = root;
        int k = 0;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                k += size(current.left) + 1;
                current = current.right;
            } else {
                k += size(current.left);
                break;
            }
        }
        return k;
    }

    public Key select(int k) {
        if (k < 0 || k >= size(root))
            throw new IllegalArgumentException("invalid rank");

        Node<Key, Value> current = root;
        int t;
        while (current != null) {
            t = size(current.left);
            if (t > k) {
                current = current.left;
            } else if (t < k) {
                k = k - t - 1;
                current = current.right;
            } else {
                return current.key;
            }
        }
        return null;
    }

    public Iterable<Key> keys(Key lo, Key hi) {

    }
}
