package com.segarciat.algs4.ch3.sec2.ex12;

import edu.princeton.cs.algs4.Queue;

import java.util.NoSuchElementException;

/**
 * <strong>3.2.12)</strong>
 * Implementation of a binary search tree based on {@link edu.princeton.cs.algs4.BST}
 * which does not implement {@link edu.princeton.cs.algs4.BST#select(int)} nor
 * {@link edu.princeton.cs.algs4.BST#rank(Comparable)} and omits the count field
 * in the Node class.
 * @author Sergio E. Garcia Tapia
 */
public final class BST<Key extends Comparable<Key>, Value>{
    private Node<Key, Value> root = null;

    private static class Node<Key, Value> {
        private final Key key;
        private Value value;
        private Node<Key, Value> left;
        private Node<Key, Value> right;

        private Node(Key key, Value value) {
            this.key = key;
            this.value = value;
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
            return 1 + size(node.left) + size(node.right);
        }
    }

    public int size(Key lo, Key hi) {
        if (lo == null || hi == null)
            throw new NullPointerException("not defined for null key");

        if (hi.compareTo(lo) < 0) {
            return 0;
        }
        return size(root, lo, hi);
    }

    private int size(Node<Key, Value> node, Key lo, Key hi) {
        if (node == null) {
            return 0;
        }

        int cmpLo = lo.compareTo(node.key);
        int cmpHi = hi.compareTo(node.key);

        int count = 0;
        if (cmpLo < 0) {
            count += size(node.left, lo, hi);
        }

        if (cmpLo <= 0 && cmpHi >= 0) {
            count += 1;
        }

        if (cmpHi > 0) {
            count += size(node.right, lo, hi);
        }
        return count;
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

        root = put(root, key, val);
    }

    private Node<Key, Value> put(Node<Key, Value> node, Key key, Value value) {
        if (node == null) {
            return new Node<>(key, value);
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else if (cmp > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }

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

    public void deleteMin() {
        if (isEmpty())
            throw new NoSuchElementException("cannot delete from empty symbol table");
        root = deleteMin(root);
    }

    private Node<Key, Value> deleteMin(Node<Key, Value> node) {
        if (node.left == null)
            return node.right;
        node.left = deleteMin(node.left);
        return node;
    }

    public void deleteMax() {
        if (isEmpty())
            throw new NoSuchElementException("cannot delete from empty symbol table");
        root = deleteMax(root);
    }

    private Node<Key, Value> deleteMax(Node<Key, Value> node) {
        if (node.right == null)
            return node.left;
        node.right = deleteMax(node.right);

        return node;
    }

    public void delete(Key key) {
        if (key == null)
            throw new NullPointerException("cannot delete a null key");
        if (isEmpty())
            throw new NoSuchElementException("cannot delete from empty symbol table");
        root = delete(root, key);
    }

    private Node<Key, Value> delete(Node<Key, Value> node, Key key) {
        if (node == null)
            return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null)
                return node.right;
            if (node.right == null)
                return node.left;

            Node<Key, Value> t = node;
            node = min(t.right);
            node.right = deleteMin(t.right);
            node.left = t.left;
        }
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
