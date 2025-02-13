package com.segarciat.algs4.ch3.sec2.ex07;

import edu.princeton.cs.algs4.Queue;

import java.util.NoSuchElementException;

/**
 * <strong>3.2.7)</strong>
 * Extends the BST implementation from Exercise 3.2.8. to provide a
 * non-recursive implementation of {@link BSTAvgComparesRec#avgCompares()}, which
 * computes the average number of compares required by a random hit in a given
 * BST.
 * @author Sergio E. Garcia Tapia
 */
public final class BSTAvgComparesNonRec<Key extends Comparable<Key>, Value>{
    private Node<Key, Value> root = null;

    private static class Node<Key, Value> {
        private final Key key;
        private Value value;
        private int n;
        int ipl;
        private Node<Key, Value> left;
        private Node<Key, Value> right;

        private Node(Key key, Value value, int n, int ipl) {
            this.key = key;
            this.value = value;
            this.n = n;
            this.ipl = ipl;
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size(root);
    }

    private int size(Node<Key, Value> node) {
        if (node == null) {
            return 0;
        } else {
            return node.n;
        }
    }

    public int size(Key lo, Key hi) {
        if (lo == null || hi == null)
            throw new NullPointerException("not defined for null key");

        if (hi.compareTo(lo) < 0) {
            return 0;
        } else if (contains(hi)) {
            return rank(hi) - rank(lo) + 1;
        } else {
            return rank(hi) - rank(lo);
        }
    }

    public int height() {
        return height(root);
    }

    private int height(Node<Key, Value> node) {
        if (node == null || (node.left == null && node.right == null)) {
            return 0;
        } else {
            return 1 + Math.max(height(node.left), height(node.right));
        }
    }

    public double avgCompares() {
        if (root == null) {
            return 0;
        } else {
            return 1 + (ipl(root)) / (size(root) + 0.0);
        }
    }

    private int ipl(Node<Key, Value> node) {
        if (node == null) {
            return 0;
        } else {
            return node.ipl;
        }
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Value get(Key key) {
        if (key == null)
            throw new NullPointerException("cannot search for a null key");
        return get(root, key);
    }

    private Value get(Node<Key, Value> node, Key key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return get(node.left, key);
        } else if (cmp > 0) {
            return get(node.right, key);
        } else {
            return node.value;
        }
    }

    public void put(Key key, Value val) {
        if (key == null)
            throw new NullPointerException("cannot add a null key");
        if (val == null) {
            delete(key);
            return;
        }

        root = put(root, key, val, 0);
    }

    private Node<Key, Value> put(Node<Key, Value> node, Key key, Value value, int depth) {
        if (node == null) {
            return new Node<>(key, value, 1, depth);
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, value, depth + 1);
        } else if (cmp > 0) {
            node.right = put(node.right, key, value, depth + 1);
        } else {
            node.value = value;
        }

        node.n = size(node.left) + size(node.right) + 1;
        node.ipl = depth + ipl(node.left) + ipl(node.right);
        return node;
    }

    public Key min() {
        if (isEmpty())
            throw new NoSuchElementException("symbol table is empty");
        Node<Key, Value> minNode = min(root);
        return minNode.key;
    }

    private Node<Key, Value> min(Node<Key, Value> node) {
        if (node.left == null) {
            return node;
        } else {
            return min(node.left);
        }
    }

    public Key max() {
        if (isEmpty())
            throw new NoSuchElementException("symbol table is empty");
        Node<Key, Value> maxNode = max(root);
        return maxNode.key;
    }

    private Node<Key, Value> max(Node<Key, Value> node) {
        if (node.right == null) {
            return node;
        } else {
            return max(node.right);
        }
    }

    public Key floor(Key key) {
        if (key == null)
            throw new NullPointerException("null key is invalid");

        Node<Key, Value> node = floor(root, key);
        if (node == null)
            throw new NoSuchElementException();
        return node.key;
    }

    private Node<Key, Value> floor(Node<Key, Value> node, Key key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return node;
        }

        if (cmp < 0) {
            return floor(node.left, key);
        }

        Node<Key, Value> t = floor(node.right, key);
        if (t != null) {
            return t;
        } else {
            return node;
        }
    }

    public Key ceiling(Key key) {
        if (key == null)
            throw new NullPointerException("null key is invalid");
        Node<Key, Value> node = ceiling(root, key);
        if (node == null)
            throw new NoSuchElementException("no appropriate node exists");
        return node.key;
    }

    private Node<Key, Value> ceiling(Node<Key, Value> node, Key key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return node;
        }

        if (cmp > 0) {
            return ceiling(node.right, key);
        }

        Node<Key, Value> t = ceiling(node.left, key);
        if (t != null) {
            return t;
        } else {
            return node;
        }
    }

    public Key select(int k) {
        if (k < 0 || k >= size())
            throw new IllegalArgumentException("no key of rank " + k);
        Node<Key, Value> node = select(root, k);
        return node.key;
    }

    private Node<Key, Value> select(Node<Key, Value> node, int k) {
        if (node == null) {
            return null;
        }
        int t = size(node.left);
        if (k < t) {
            return select(node.left, k);
        } else if (k > t){
            return select(node.right, k - t - 1);
        } else {
            return node;
        }
    }

    public int rank(Key key) {
        if (key == null)
            throw new NullPointerException("rank is not defined for null");
        return rank(root, key);
    }

    private int rank(Node<Key, Value> node, Key key) {
        if (node == null)
            return 0;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return rank(node.left, key);
        } else if (cmp > 0) {
            return 1 + size(node.left) + rank(node.right, key);
        } else {
            return size(node.left);
        }
    }

    public void deleteMin() {
        if (isEmpty())
            throw new NoSuchElementException("cannot delete from empty symbol table");
        root = deleteMin(root, 0);
    }

    private Node<Key, Value> deleteMin(Node<Key, Value> node, int depth) {
        if (node.left == null)
            return node.right;
        node.left = deleteMin(node.left, depth + 1);
        node.n = size(node.left) + size(node.right) + 1;
        node.ipl = depth + ipl(node.left) + ipl(node.right);
        return node;
    }

    public void deleteMax() {
        if (isEmpty())
            throw new NoSuchElementException("cannot delete from empty symbol table");
        root = deleteMax(root, 0);
    }

    private Node<Key, Value> deleteMax(Node<Key, Value> node, int depth) {
        if (node.right == null)
            return node.left;
        node.right = deleteMax(node.right, depth + 1);
        node.n = size(node.left) + size(node.right) + 1;
        node.ipl = depth + ipl(node.left) + ipl(node.right);
        return node;
    }

    public void delete(Key key) {
        if (key == null)
            throw new NullPointerException("cannot delete a null key");
        if (isEmpty())
            throw new NoSuchElementException("cannot delete from empty symbol table");
        root = delete(root, key, 0);
    }

    private Node<Key, Value> delete(Node<Key, Value> node, Key key, int depth) {
        if (node == null)
            return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key, depth + 1);
        } else if (cmp > 0) {
            node.right = delete(node.right, key, depth + 1);
        } else {
            if (node.left == null)
                return node.right;
            if (node.right == null)
                return node.left;

            Node<Key, Value> t = node;
            node = min(t.right);
            node.right = deleteMin(t.right, depth + 1);
            node.left = t.left;
        }
        node.n = size(node.left) + size(node.right) + 1;
        node.ipl = depth + ipl(node.left) + ipl(node.right);
        return node;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null || hi == null)
            throw new NullPointerException("keys cannot be null");
        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node<Key, Value> node, Queue<Key> queue, Key lo, Key hi) {
        if (node == null) {
            return;
        }

        int cmpLo = lo.compareTo(node.key);
        int cmpHi = hi.compareTo(node.key);

        // there may be more eligible keys to the smaller than node.key
        if (cmpLo < 0) {
            keys(node.left, queue, lo, hi);
        }

        // lo <= node.key <= hi.key
        if (cmpLo <= 0 && cmpHi >= 0) {
            queue.enqueue(node.key);
        }

        // there may be more eligible keys to the right of node.key
        if (cmpHi > 0) {
            keys(node.right, queue, lo, hi);
        }
    }
}
