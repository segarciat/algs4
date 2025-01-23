package com.segarciat.algs4.ch5.sec1.ex01;

import edu.princeton.cs.algs4.LinearProbingHashST;
import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/**
 * <strong>5.1.1</strong>
 * Employs a symbol-table and key-indexed counting to sort an array of integers.
 * Assumes the number of different keys is not large.
 * @author Sergio E. Garcia Tapia
 */
public final class STKeyIndexCounting {
    public static void sort(int[] a) {
        if (a == null)
            throw new NullPointerException("cannot sort a null array");

        int n = a.length;
        if (n <= 1)
            return;

        // Identify the unique keys
        LinearProbingHashST<Integer, Integer> keyRanks = new LinearProbingHashST<>();
        for (int key: a)
            keyRanks.put(key, 0);
       Integer[] unique = sortAndRank(keyRanks);
       int R = unique.length;

        // Count frequencies of keys
        int[] count = new int[unique.length + 1];
        for (int key: a)
            count[keyRanks.get(key) + 1]++;

        // Transform frequency counts to indices
        for (int r  = 0; r < R; r++)
            count[r + 1] += count[r];

        // Distribute keys
        int[] aux = new int[n];
        for (Integer key: a)
            aux[count[keyRanks.get(key)]++] = key;

        // Copy-back
        System.arraycopy(aux, 0, a, 0, n);
    }

    /**
     * Given a set of integer keys, sorts the keys and modifies the given map
     * to associate each key with its rank as a side effect. Then it provides
     * a sort array of unique keys.
     * @param st A map with integer keys.
     * @return A sorted array of the unique keys in the map.
     */
    private static Integer[] sortAndRank(LinearProbingHashST<Integer, Integer> st) {
        int R = st.size();
        Integer[] unique = new Integer[R];
        int i = 0;
        for (Integer key: st.keys())
            unique[i++] = key;
        // Sort the unique keys and assign ranks
        Quick.sort(unique);
        for (int r = 0; r < R; r++)
            st.put(unique[r], r);
        return unique;
    }

    public static void main(String[] args) {
        final int N = 100;
        int[] a = new int[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniformInt(70, 73);
        sort(a);
        StdOut.print(Arrays.toString(a));
    }
}
