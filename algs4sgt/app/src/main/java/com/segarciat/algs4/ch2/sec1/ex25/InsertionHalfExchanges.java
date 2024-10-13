package com.segarciat.algs4.ch2.sec1.ex25;

import com.segarciat.algs4.ch2.sec1.ex17.SortUtil;

/**
 * @author Sergio E. Garcia Tapia
 * Implementation of insertion sort using half exchanges.
 */
public class InsertionHalfExchanges {
    public static <T extends Comparable<T>> void sort(T[] a) {
        if (a == null)
            throw new NullPointerException("array cannot be null");
        if (a.length == 0)
            return;
        int n = a.length;

        for (int i = 1; i < n; i++) {
            T temp = a[i];
            int j;
            for (j = i; j > 0 && SortUtil.less(temp, a[j - 1]); j--)
                a[j] = a[j - 1];
            a[j] = temp;
        }

    }
}
