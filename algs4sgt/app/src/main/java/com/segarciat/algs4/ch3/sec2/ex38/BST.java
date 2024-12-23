package com.segarciat.algs4.ch3.sec2.ex38;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.Color;
import java.awt.Font;
import java.util.NoSuchElementException;

/**
 * <strong>3.2.38)</strong>
 * BST implementation based on {@link com.segarciat.algs4.ch3.sec2.ex32.BST}.
 * Implements a method {@link #draw()}
 * @author Sergio E. Garcia Tapia
 */
public final class BST<Key extends Comparable<Key>, Value>{
    private static final double RADIUS = 10;
    private Node<Key, Value> root = null;

    private static class Node<Key, Value> {
        private final Key key;
        private Value value;
        private int n;
        Point2D coords;
        private Node<Key, Value> left;
        private Node<Key, Value> right;

        private Node(Key key, Value value, int n) {
            this.key = key;
            this.value = value;
            this.n = n;
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
            return new Node<>(key, value, 1);
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else if (cmp > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }

        node.n = size(node.left) + size(node.right) + 1;
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

    /**
     * @return A random key from the tree.
     */
    public Key randomKey() {
        if (isEmpty())
            throw new NoSuchElementException("the tree is empty");
        return select(StdRandom.uniformInt(0, size() - 1));
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
        root = deleteMin(root);
    }

    private Node<Key, Value> deleteMin(Node<Key, Value> node) {
        if (node.left == null)
            return node.right;
        node.left = deleteMin(node.left);
        node.n = size(node.left) + size(node.right) + 1;
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
        node.n = size(node.left) + size(node.right) + 1;

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
        node.n = size(node.left) + size(node.right) + 1;
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

    /**
     * Draws a binary tree using {@link StdDraw} in a style similar
     * to the text.
     */
    public void draw() {
        double[] xLimits = { Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY };
        double[] yLimits = { Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY };
        setCoordinates(root, 0, 0, xLimits, yLimits);

        double range = Math.max(xLimits[1] - xLimits[0], yLimits[1] - yLimits[0]);
        StdDraw.setXscale(xLimits[0] - 2 * RADIUS, xLimits[0] + range + 2 * RADIUS);
        StdDraw.setYscale(yLimits[0] - 2 * RADIUS, yLimits[0] + range + 2 * RADIUS);

        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setPenRadius(0.005);
        StdDraw.setFont(new Font("Arial", Font.BOLD, 12));

        draw(root);
    }

    /**
     * Helper method to {@link #draw()}. Recursively computes the center coordinate each
     * node in the binary tree. It uses each node's depth and its rank, and scale it by
     * {@value RADIUS} to ensure the center of any two nodes is a scalar multiple of {@value RADIUS}
     * It computes the maximum and minimum x and y coordinates along the way.
     */
    private void setCoordinates(Node<Key, Value> node, int baseRank, int depth, double[] xLimits, double[] yLimits) {
        if (node == null)
            return;

        setCoordinates(node.left, baseRank, depth - 1, xLimits, yLimits);
        setCoordinates(node.right, baseRank + size(node.left) + 1,  depth - 1, xLimits, yLimits);
        node.coords = new Point2D(2 * RADIUS * (baseRank + size(node.left)), 2 * RADIUS * depth);
        xLimits[0] = Math.min(xLimits[0], node.coords.x());
        xLimits[1] = Math.max(xLimits[1], node.coords.x());
        yLimits[0] = Math.min(yLimits[0], node.coords.y());
        yLimits[1] = Math.max(yLimits[1], node.coords.y());
    }

    /**
     * Draws the binary tree rooted at the given node. For each node,
     * a circle is drawn centered at its x and y coordinate, the key
     * is drawn inside the circle, and a line is drawn to the edge
     * of the circle of any child nodes.
     */
    private void draw(Node<Key, Value> node) {
        if (node == null)
            return;

        StdDraw.circle(node.coords.x(), node.coords.y(), RADIUS);
        StdDraw.text(node.coords.x(), node.coords.y(), node.key.toString());
        if (node.left != null) {
            double theta = Math.atan(
                    (node.coords.y() - node.left.coords.y()) / (node.coords.x() - node.left.coords.x())
            );
            double deltaX = RADIUS * Math.cos(theta);
            double deltaY = RADIUS * Math.sin(theta);

            double parentX = node.coords.x() - deltaX;
            double parentY = node.coords.y() - deltaY;
            double childX = node.left.coords.x() + deltaX;
            double childY = node.left.coords.y() + deltaY;

            StdDraw.line(parentX, parentY, childX, childY);
            draw(node.left);
        }
        if (node.right != null) {
            double theta = Math.atan(
                    (node.coords.y() - node.right.coords.y()) / (node.right.coords.x() - node.coords.x())
            );
            double deltaX = RADIUS * Math.cos(theta);
            double deltaY = RADIUS * Math.sin(theta);

            double parentX = node.coords.x() + deltaX;
            double parentY = node.coords.y() - deltaY;
            double childX = node.right.coords.x() - deltaX;
            double childY = node.right.coords.y() + deltaY;

            StdDraw.line(parentX, parentY, childX, childY);
            draw(node.right);
        }
    }

    public static void main(String[] args) {
        Character[] keys = new Character[26];
        for (int i = 0; i < keys.length; i++)
            keys[i] = (char) ('A' + i);
        StdRandom.shuffle(keys);

        BST<Character, Integer> bst = new BST<>();
        for (Character key: keys) {
            bst.put(key, 0);
        }

        bst.draw();
    }
}
