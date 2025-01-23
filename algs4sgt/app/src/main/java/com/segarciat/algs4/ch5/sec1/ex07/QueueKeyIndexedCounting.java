package com.segarciat.algs4.ch5.sec1.ex07;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/**
 * <strong>5.1.7)</strong>
 * Implements key-indexed sorting with an array of queues.
 * @author Sergio E. Garcia Tapia
 */
public final class QueueKeyIndexedCounting {
    /**
     * Given an array <code>a</code> of integers in the closed interval <code>[0, R - 1]</code>,
     * where <code>R</code> is positive, sorts the array.
     * @param a An array of integers in the interval <code>[0, R - 1]</code>.
     * @param R A positive integer.
     */
    public static void sort(int[] a, int R) {
        if (a == null)
            throw new NullPointerException("cannot sort a null array");
        if (R <= 0)
            throw new IllegalArgumentException("R must be positive");

        int n = a.length;
        if (n <= 1)
            return;

        Queue<Integer>[] keys = (Queue<Integer>[]) new Queue[R];
        for (int r = 0; r < keys.length; r++)
            keys[r] = new Queue<>();

        for (int r: a) {
            if (r < 0 || r >= R)
                throw new IllegalArgumentException("array value must be in the closed interval [0, R-1]");
            keys[r].enqueue(r);
        }

        int i = 0;
        for (Queue<Integer> queue : keys) {
            while (!queue.isEmpty())
                a[i++] = queue.dequeue();
        }
    }

    public static void main(String[] args) {
        final int N = 100;
        final int R = 2;
        int[] a = new int[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniformInt(0, R);
        sort(a, R);
        StdOut.println(Arrays.toString(a));
    }
}
