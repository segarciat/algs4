package com.segarciat.algs4.ch3.sec2.ex33;

import edu.princeton.cs.algs4.BST;

/**
 * <strong>3.2.33</strong>
 * Implements a method to check {@link BST#rank(Comparable)} and {@link BST#select(int)}
 * are consistent.
 */
public final class SelectRankCheck {
    /**
     * Determines whether {@link BST#rank(Comparable)} and {@link BST#select(int)} are
     * consistent, which occurs if both <code>i == select(rank(i))</code> for all
     * integers <code>i</code> from <code>0</code> to <code>size() - 1</code>, and
     * <code>key.equals(select(rank(key)))</code> for all keys in the BST.
     * @param bst A non-null BST
     * @return <code>true</code> if the methods are consistent for all keys and indices,
     * <code>false</code> otherwise.
     */
    public static <Key extends Comparable<Key>, Value> boolean selectRankCheck(BST<Key, Value> bst) {
        if (bst == null)
            throw new NullPointerException("null bst is not supported");

        for (int i = 0; i < bst.size(); i++) {
            if (i != bst.rank(bst.select(i)))
                return false;
        }

        for (Key key: bst.keys()) {
            if (!key.equals(bst.select(bst.rank(key))))
                return false;
        }
        return true;
    }
}
