package com.segarciat.algs4.ch3.sec5.ex09;

import edu.princeton.cs.algs4.Queue;

import java.util.NoSuchElementException;

/**
 * <strong>3.5.9)</strong>
 * Builds on {@link edu.princeton.cs.algs4.BST} by adding the ability
 * to associate more than one value for each key.
 * @author Sergio E. Garcia Tapia
 */
public final class BST <Key extends Comparable<Key>, Value> {
    private Node<Key, Value> root;

    private static class Node <Key, Value> {
        private final Key key;
        private final Queue<Value> vals;
        private Node<Key, Value> left;
        private Node<Key, Value> right;
        private int n;

        public Node(Key key, Value val, int n) {
            this.key = key;
            vals = new Queue<>();
            vals.enqueue(val);
            this.n = n;
        }
    }

    private int size(Node<Key, Value> x) {
        if (x == null)
            return 0;
        return x.n;
    }

    public int size() {
        return size(root);
    }

    public boolean isEmpty() {
        return root == null;
    }

    private Value get(Node<Key, Value> x, Key key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return get(x.left, key);
        else if (cmp > 0)
            return get(x.right, key);
        else
            return x.vals.peek();
    }

    public Value get(Key key) {
        if (key == null)
            return null;
        return get(root, key);
    }

    private Node<Key, Value> put(Node<Key, Value> x, Key key, Value val) {
        if (x == null)
            return new Node<>(key, val, 1);

        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = put(x.left, key, val);
        if (cmp > 0)
            x.right = put(x.right, key, val);
        else
            x.vals.enqueue(val);

        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void put(Key key, Value val) {
        if (key == null)
            throw new NullPointerException("cannot insert a null key");

        root = put(root, key, val);
    }

    private Node<Key, Value> deleteMin(Node<Key, Value> x) {
        if (x.left == null)
            return x.right;
        x.left = deleteMin(x.left);
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMin() {
        if (isEmpty())
            throw new NoSuchElementException("the tree is empty");
        root = deleteMin(root);
    }

    private Node<Key, Value> min(Node<Key, Value> x) {
        if (x.left == null)
            return x;
        return min(x.left);
    }

    public Key min() {
        if (isEmpty())
            throw new NoSuchElementException("tree is empty");
        Node<Key, Value> x = min(root);
        return x.key;
    }

    private Node<Key, Value> delete(Node<Key, Value> x, Key key) {
        if (x == null)
            return null;

        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = delete(x.left, key);
        else if (cmp > 0)
            x.right = delete(x.right, key);
        else if (x.left == null)
            return x.right;
        else if (x.right == null)
            return x.left;
        else {
            Node<Key, Value> t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key) {
        if (key == null)
            throw new NullPointerException("cannot delete null key");

        root = delete(root, key);
    }
}
