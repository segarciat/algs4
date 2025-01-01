package com.segarciat.algs4.ch3.sec3.ex23;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdRandom;

import java.util.NoSuchElementException;

/**
 * <strong>3.3.23</strong>
 * Implementation of the basic symbol table API (page 363) using
 * a (not necessarily balanced) 2-3 tree as the underlying data structure,
 * where 3-nodes can lean either way.
 */
public final class TwoThreeST <Key extends Comparable<Key>, Value>{
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node<Key, Value> root;

    private static class Node<Key, Value> {
        Key key;
        Value val;
        Node<Key, Value> left, right;
        int n;
        int blackDepth;
        boolean color;

        Node(Key key, Value val, int n, boolean color, int blackDepth) {
            this.key = key;
            this.val = val;
            this.n = n;
            this.color = color;
            this.blackDepth = blackDepth;
        }
    }


    private boolean isRed(Node<Key, Value> x) {
        if (x == null)
            return false;
        return x.color == RED;
    }

    public boolean isEmpty() {
        return root == null;
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
            throw new NullPointerException("key cannot be null");
        root = put(root, BLACK, 0, key, val);
        root.color = BLACK;
    }

    private Node<Key, Value> put(Node<Key, Value> x, boolean parentColor, int blackDepth, Key key, Value val) {
        if (x == null) {
            return new Node<>(key, val, 1,
                    parentColor == RED ? BLACK: RED, // color
                    parentColor == RED ? blackDepth: blackDepth - 1); // depth
        }

        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = put(x.left, x.color, isRed(x.left) ? blackDepth: blackDepth + 1, key, val);
        else if (cmp > 0)
            x.right = put(x.right, x.color, isRed(x.right) ? blackDepth: blackDepth + 1, key, val);
        else
            x.val = val;
        x.n = size(x.left) + size(x.right) + 1;

        return x;
    }

    public Key min() {
        if (isEmpty())
            throw new NoSuchElementException("tree is empty");
        Node<Key, Value> x = min(root);
        return x.key;
    }

    private Node<Key, Value> min(Node<Key, Value> x) {
        if (x.left == null)
            return x;
        return min(x.left);
    }

    public Key max() {
        if (isEmpty())
            throw new NoSuchElementException("tree is empty");
        Node<Key, Value> x = max(root);
        return x.key;
    }

    private Node<Key, Value> max(Node<Key, Value> x) {
        if (x.right == null)
            return x;
        return max(x.right);
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<>();
        keys(queue, root, lo, hi);
        return queue;
    }

    private void keys(Queue<Key> queue, Node<Key, Value> x, Key lo, Key hi) {
        if (x == null)
            return;

        int cmpLo = lo.compareTo(x.key);
        int cmpHi = hi.compareTo(x.key);

        if (cmpLo < 0)
            keys(queue, x.left, lo, hi);

        if (cmpLo <= 0 && cmpHi >= 0)
            queue.enqueue(x.key);

        if (cmpHi > 0)
            keys(queue, x.right, lo, hi);
    }

    private Iterable<Integer> depths() {
        Queue<Integer> queue = new Queue<>();
        depths(queue, root);
        return queue;
    }

    private void depths(Queue<Integer> queue, Node<Key, Value> x) {
        if (x == null)
            return;
        depths(queue, x.left);
        queue.enqueue(x.blackDepth);
        depths(queue, x.right);
    }

    private static Integer[] generateKeys(int n) {
        Integer[] keys = new Integer[n];
        for (int i = 0; i < n; i++)
            keys[i] = i;
        StdRandom.shuffle(keys);
        return keys;
    }

    public static void main(String[] args) {
        for (int n = 128; true; n *= 2) {
            Integer[] keys = generateKeys(n);
            TwoThreeST<Integer, Integer> st = new TwoThreeST<>();
            for (Integer key: keys)
                st.put(key, key);

            double averagePathLength = 0.0;
            for (Integer depth: st.depths()) {
                averagePathLength += depth / (n + 0.0);
            }

            System.out.printf("n=%d, averagePathLength=%.1f, log_2(n)=%.1f, log_3(n)=%.1f%n",
                    n, averagePathLength, Math.log(n) / Math.log(2), Math.log(n) / Math.log(3));
        }
    }
}
