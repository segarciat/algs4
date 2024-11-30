package com.segarciat.algs4.ch3.sec2.ex08;

/**
 * <strong>3.2.8)</strong>
 * Implements a method {@link #optCompares(int)} that computes the number
 * of compares for a random search hit on a binary search tree that is
 * perfect balanced.
 * @author Sergio E. Garcia Tapia
 */
public final class BSTOptCompares {
    private BSTOptCompares() {}

    /**
     * Computes the number of compares for a random search hit on a binary
     * search tree of size <code>n</code> that is perfectly balanced.
     * @param n A non-negative tree size.
     * @return The average number of compares for a random search hit on a perfect
     * binary tree.
     * @throws IllegalArgumentException if <code>n</code> is negative.
     */
    public static double optCompares(int n) {
        if (n < 0)
            throw new IllegalArgumentException("illegal BST size");
        if (n == 0)
            return 0;

        int ipl = 0;
        int depth = 0;
        int nodesAtDepth = 1;
        int k = n;

        while (k >= nodesAtDepth) {
            ipl += depth * nodesAtDepth;
            k -= nodesAtDepth;
            depth++;
            nodesAtDepth *= 2;
        }

        // adds depths of remaining nodes at last level
        if (k > 0) {
            ipl += depth * k;
        }

        return 1 + (ipl / (n + 0.0));
    }

    public static void main(String[] args) {
        for (int n = 1; n <= 31; n++) {
            System.out.println(optCompares(n));
        }
    }
}
