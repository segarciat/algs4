package com.segarciat.algs4.ch3.sec3.ex25;

/**
 * <strong>Exercise 3.3.25</strong>
 * Implements a balanced 2-3-4 tree using the red-black representation
 * and insertion method described in the text.
 */
public final class TwoThreeFourST <Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private static class Node<Key, Value> {
        Key key;
        Value val;
        int n;
        Node<Key, Value> left, right;
        boolean color;

        Node(Key key, Value val, int n, boolean color) {
            this.key = key;
            this.val = val;
            this.n = n;
            this.color = color;
        }
    }

    private boolean isRed(Node<Key, Value> x) {
        if (x == null)
            return false;
        return x.color == RED;
    }

    private Node<Key, Value> rotateLeft(Node<Key, Value> h) {
        Node<Key, Value> x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.n = h.n;
        h.n = size(h.left) + size(h.right) + 1;
        return x;
    }

    private Node<Key, Value> rotateRight(Node<Key, Value> h) {
        Node<Key, Value> x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.n = h.n;
        h.n = size(h.left) + size(h.right) + 1;
        return x;
    }

    private void flipColors(Node<Key, Value> h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    private Node<Key, Value> root;

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size(root);
    }

    private int size(Node<Key, Value> x) {
        if (x == null)
            return 0;
        return x.n;
    }

    public Value get(Key key) {
        if (key == null)
            return null;
        return get(root, key);
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
            return x.val;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public void put(Key key, Value val) {
        if (key == null)
            throw new NullPointerException("cannot use a null key");
        root = put(root, key, val);
        root.color = BLACK;
    }

    private Node<Key, Value> put(Node<Key, Value> x, Key key, Value val) {
        if (x == null)
            return new Node<>(key, val, 1, RED);

        // split 4-nodes on the way down
        if (isRed(x.left) && isRed(x.right))
            flipColors(x);

        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = put(x.left, key, val);
        else if (cmp > 0)
            x.right = put(x.right, key, val);
        else
            x.val = val;

        // balance 4-nodes on the way up
        if (isRed(x.right) && !isRed(x.left)) // second check needed because otherwise we'd flip colors.
            x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left))
            x = rotateRight(x);

        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }
}
