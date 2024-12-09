package com.segarciat.algs4.ch3.sec2.ex25;

import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.Quick;

/**
 * <strong>3.2.25)</strong>
 * Inserts a set of keys into a BST that achieves perfect balance.
 * @author Sergio E. Garcia Tapia
 */
public final class PerfectBalance {
    /**
     * Creates a perfectly balanced binary search tree, so that searching for keys
     * is equivalent to doing binary search on the given array.
     * @param keys A set of keys to add to the tree.
     * @return A balanced binary search tree with the same keys as the given array.
     */
    public static <Key extends Comparable<Key>> BST<Key, Key> perfectlyBalancedTree(Key[] keys) {
        if (keys == null)
            throw new NullPointerException("keys cannot be empty");

        BST<Key, Key> bst = new BST<>();
        if (keys.length < 1)
            return bst;

        Key[] keysCopy = (Key[]) new Comparable[keys.length];
        for (int i = 0; i < keys.length; i++)
            keysCopy[i] = keys[i];

        Quick.sort(keys);

        perfectlyBalancedTree(bst, keys, 0, keys.length - 1);
        return bst;
    }

    /**
     * Helper method to {@link #perfectlyBalancedTree(Comparable[])}.
     */
    private static <Key extends Comparable<Key>> void perfectlyBalancedTree(BST<Key, Key> bst, Key[] keys, int lo, int hi) {
        if (hi < lo)
            return;
        int mid = lo + (hi - lo) / 2;
        Key key = keys[mid];
        bst.put(key, key);
        perfectlyBalancedTree(bst, keys, lo, mid - 1);
        perfectlyBalancedTree(bst, keys, mid + 1, hi);
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[100];
        for (int i = 0; i < 100; i++)
            a[i] = i;

        var bst = perfectlyBalancedTree(a);
    }
}
