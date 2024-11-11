package com.segarciat.algs4.ch2.sec5.ex04;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Quick;

import java.util.Arrays;

/**
 * <strong>2.5.4)</strong>
 * Implements a method {@link DeduplicatedStrings#dedup(String[])} to deduplicate
 * an array of {@link String} objects.
 * @author Sergio E. Garcia Tapia
 */
public class DeduplicatedStrings {
    /**
     * Produces an array containing only the distinct elements in the
     * input array <code>a</code>. The input array is not modified.
     * @param a An array of strings to use as reference for deduplication.
     * @return An array of the distinct elements from the input array, or
     * @throws NullPointerException if the given array is <code>null</code>.
     */
    public static String[] dedup(String[] a) {
        return dedup_v1(a);
    }

    /**
     * Uses a queue to track distinct items and manually copies
     * into result array.
     */
    private static String[] dedup_v1(String[] a) {
        if (a == null)
            throw new NullPointerException("array cannot be null");

        // Defensive copy
        String[] aSorted = Arrays.copyOf(a, a.length);

        if (a.length < 2)
            return aSorted;

        // Find distinct elements
        Quick.sort(aSorted);
        Queue<String> distinct = new Queue<>();
        distinct.enqueue(aSorted[0]);
        for (int j = 1; j < aSorted.length; j++)
            if (!aSorted[j].equals(aSorted[j - 1]))
                distinct.enqueue(aSorted[j]);

        // Copy-back to array
        String[] aDeduped = new String[distinct.size()];
        for (int k = 0; k < aDeduped.length; k++)
            aDeduped[k] = distinct.dequeue();

        return aDeduped;
    }

    /**
     * After copying and sorting the copy, overwrites the array
     * to have only the distinct keys at the beginning, then
     * uses the built-in {@link Arrays#copyOfRange(Object[], int, int)} for
     * the copy-back.
     */
    private static String[] dedup_v2(String[] a) {
        if (a == null)
            throw new NullPointerException("array cannot be null");

        // Defensive copy
        String[] aSorted = Arrays.copyOf(a, a.length);

        if (a.length < 2)
            return aSorted;

        // Find distinct elements
        Quick.sort(aSorted);
        int distinct = 1;
        for (int j = 1; j < aSorted.length; j++)
            if (!aSorted[j].equals(aSorted[j - 1]))
                aSorted[distinct++] = aSorted[j];

        return Arrays.copyOfRange(aSorted, 0, distinct);
    }
}
