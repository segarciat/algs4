package com.segarciat.algs4.ch2.sec4.ex15;

import com.segarciat.algs4.ch2.SortUtil;

/**
 * <strong>2.4.15)</strong>
 * Implements a method to verify that an array is a minimum-oriented
 * priority queue.
 * @author Sergio E. Garcia Tapia
 */
public class MinPQCertification {
    /**
     * Determines whether the given array is <code>pq</code> is a minimum-oriented
     * priority queue. An array of size 0 is considered a minimum-oriented priority
     * queue, and a <code>null</code> array is not. Similarly, the presence
     * of any <code>null</code> element causes this method to return <code>false</code>.
     *
     * @param pq The array to verify.
     * @return <code>true</code> if the array is a minimum-oriented priority heap,
     * and <code>false</code> otherwise.
     * @param <Key> The type of elements in the array.
     */
    public static <Key extends Comparable<Key>> boolean isMinPQ(Key[] pq) {
        if (pq == null)
            return false;
        if (pq.length == 0) // vacuously true
            return true;
        if (pq[0] == null)
            return false;

        int n = pq.length;
        int k = 0;
        while (2 * k + 1 < n) {
            // children should not be null, or smaller than their parent
            if (pq[2 * k + 1] == null || SortUtil.less(pq[2 * k + 1], pq[k]))
                return false;
            if (2 * k + 2 < n && (pq[2 * k + 1] == null || SortUtil.less(pq[2 * k + 2], pq[k])))
                return false;
            k++;
        }
        // remaining items are leaves, which we've checked to not be null.
        return true;
    }
}
