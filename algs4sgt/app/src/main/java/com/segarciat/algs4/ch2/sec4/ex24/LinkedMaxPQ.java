package com.segarciat.algs4.ch2.sec4.ex24;

import com.segarciat.algs4.ch2.SortUtil;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.NoSuchElementException;

/**
 * <strong>2.4.24)</strong>
 * Implements a max-oriented priority queue using a heap-ordered binary
 * tree that is a linked structure (not an array).
 * @param <Key>
 * @author Sergio E. Garcia Tapia
 */
public class LinkedMaxPQ<Key extends Comparable<Key>> {
    Node<Key> root;
    /**
     * The largest size for both subtrees of the root after the next insertion.
     * Used to ensure the binary tree is always "complete".
     */
    private int subtreeTargetSize = 1;

    private static class Node<T> {
        T key;
        Node<T> left;
        Node<T> right;
        Node<T> parent;
        /**
         * Number of nodes in the tree rooted at this node.
         */
        int size = 1;

        Node(Node<T> parent, T key) {
            this.parent = parent;
            this.key = key;
        }
    }

    /**
     * @return <code>true</code> if the priority queue is empty, and <code>false</code> otherwise.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * @return The number key of keys in the priority queue.
     */
    public int size() {
        return (isEmpty()) ? 0 : root.size;
    }

    /**
     * @return The largest key in the priority queue.
     * @throws NoSuchElementException if the priority queue is empty.
     */
    public Key max() {
        if (isEmpty())
            throw new NoSuchElementException("the queue is empty");
        return root.key;
    }

    /**
     * Inserts the given key to the priority queue.
     * @param key The key to insert.
     * @throws NullPointerException if the key is <code>null</code>.
     */
    public void insert(Key key) {
        if (key == null)
            throw new NullPointerException("cannot add null");

        if (root == null) {
            root = new Node<>(null, key);
            return;
        }

        // Find the next eligible parent, biased left
        int targetSize = subtreeTargetSize;
        Node<Key> eligibleParent = root;
        while(eligibleParent.left != null && eligibleParent.right != null) {
            // Node will be added as a leaf of this subtree
            eligibleParent.size++;

            if ((eligibleParent.left.size == eligibleParent.right.size) || (eligibleParent.left.size < targetSize)) {
                eligibleParent = eligibleParent.left;
            } else {
                eligibleParent = eligibleParent.right;
            }
            targetSize /= 2;
        }

        // Set up parent-child
        eligibleParent.size++;
        Node<Key> child = new Node<>(eligibleParent, key);
        if (eligibleParent.left == null)
            eligibleParent.left = child;
        else
            eligibleParent.right = child;

        // The binary tree is "perfect"
        if (root.size >= 3 && (root.left.size == root.right.size))
            subtreeTargetSize = root.size;

        // Restore heap condition
        swim(child);
    }

    /**
     * Removes the largest key from the priority queue and returns it.
     * @return The largest key in the priority queue.
     * @throws NoSuchElementException if the priority queue is empty.
     */
    public Key delMax() {
        Key maxKey = max();
        if (root.size == 1) {
            root = null;
            return maxKey;
        }

        // Find parent whose child will be removed
        int targetSize = subtreeTargetSize;
        Node<Key> eligibleParent = root;
        while(eligibleParent.size > 3) {
            // Leaf will be removed from this subtree
            eligibleParent.size--;
            if ((eligibleParent.left.size == eligibleParent.right.size) || eligibleParent.right.size >= targetSize) {
                eligibleParent = eligibleParent.right;
            } else {
                eligibleParent = eligibleParent.left;
            }
            targetSize /= 2;
        }

        // Remove child (leaf) node
        if (eligibleParent.right != null) {
            root.key = eligibleParent.right.key;
            eligibleParent.right.parent = null;
            eligibleParent.right = null;
        } else {
            root.key = eligibleParent.left.key;
            eligibleParent.left.parent = null;
            eligibleParent.left = null;
        }
        eligibleParent.size--;

        // After removal, left subtree is larger
        if (eligibleParent == root)
            subtreeTargetSize = 1;
        else if (root.left.size > root.right.size)
            subtreeTargetSize = root.left.size;

        // Restore heap condition
        sink(root);

        return maxKey;
    }

    /**
     * Exchanges the keys of the given nodes.
     */
    private void exchange(Node<Key> x, Node<Key> y) {
        assert x != null && y != null;
        Key temp = x.key;
        x.key = y.key;
        y.key = temp;
    }

    /**
     * Restore the heap condition by ensuring moving the given node up the tree
     * (closer to the root) to the appropriate level. Based on the array-based
     * implementations given in the book.
     */
    private void swim(Node<Key> current) {
        while (current != root && SortUtil.less(current.parent.key, current.key)) {
            exchange(current.parent, current);
            current = current.parent;
        }
    }

    /**
     * Restores the heap condition by moving the given node down
     * the tree (towards the leaves) to the appropriate level.
     * Based on the array-based implementations given in the book.
     */
    private void sink(Node<Key> current) {
        while (current.left != null) {
            Node<Key> child = current.left;
            if (current.right != null && SortUtil.less(current.left.key, current.right.key))
                child = current.right;
            if (!SortUtil.less(current.key, child.key)) {
                break;
            }
            exchange(current, child);
            current = child;
        }
    }

    /**
     * @return <code>true</code> if the queue is heap-ordered (max-oriented), <code>false</code> otherwise.
     */
    private boolean isMaxHeapOrdered() {
        return isMaxHeapOrdered(root);
    }

    private boolean isMaxHeapOrdered(Node<Key> node) {
        if (node == null)
            return true;
        if (node.left != null && SortUtil.less(node.key, node.left.key))
            return false;
        if (node.right != null && SortUtil.less(node.key, node.right.key))
            return false;
        return isMaxHeapOrdered(node.left) && isMaxHeapOrdered(node.right);
    }

    /**
     * Runs an experiment that builds a heap of n items, asserts
     * that it is heap-ordered, and then destroys the heap by placing
     * the items in an array and asserts that the array is sorted.
     * @param n The size of the heap to build
     * @return The time to build the heap (but not to destroy it).
     */
    private static double timeRandomInput(int n) {
        // Prepare input
        Double[] a = new Double[n];
        for (int i = 0; i < n; i++)
            a[i] = StdRandom.uniformDouble();
        StdRandom.shuffle(a);

        // Build heap
        LinkedMaxPQ<Double> pq = new LinkedMaxPQ<>();
        Stopwatch timer = new Stopwatch();
        for (var v : a)
            pq.insert(v);
        double elapsed = timer.elapsedTime();

        assert pq.isMaxHeapOrdered();

        // Destroy heap
        for (int k = n - 1; !pq.isEmpty(); k--)
            a[k] = pq.delMax();
        assert SortUtil.isSorted(a, 0, n - 1);

        return elapsed;
    }

    public static void main(String[] args) {
        double prev = timeRandomInput(256);
        for (int n = 512; true; n *= 2) {
            double time = timeRandomInput(n);
            StdOut.printf("n=%d, ratio=%.1f%n", n, time / prev);
            prev = time;
        }
    }
}
