package com.segarciat.algs4.ch2.sec1.ex24;

import com.segarciat.algs4.ch2.sec1.ex17.SortUtil;

/**
 * @author Sergio E. Garcia Tapia
 * Implementation of insertion sort using a sentinel.
 */
public class InsertionSentinel {
    public static <T extends Comparable<T>> void sort(T[] a) {
        if (a == null)
            throw new NullPointerException("array cannot be null");
        if (a.length == 0)
            return;
        int n = a.length;

        // Use the smallest element as the sentinel
        for (int i = n-1; i > 0; i--)
            if (SortUtil.less(a[i], a[i-1]))
                SortUtil.exchange(a, i, i - 1);

        for (int i = 1; i < n; i++)
            for (int j = i; SortUtil.less(a[j], a[j-1]); j--)
                SortUtil.exchange(a, j, j - 1);
    }
}
