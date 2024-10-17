package com.segarciat.algs4.ch2.sec1.ex17;

import com.segarciat.algs4.ch2.SortUtil;

/**
 * @author Sergio E. Garcia Tapia
 * Based on Algorithm 2.1 in Section 2.2 of page 249 of Algorithms by Sedgewick and Wayne.
 */
public class Insertion {
    private Insertion() {}

    private static void sort(Double[] a) {
        if (a == null)
            throw new NullPointerException("a cannot be null");
        int n = a.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && SortUtil.less(a[j], a[j-1]); j--) {
                SortUtil.exchange(a, j, j - 1);
                PlotBars.show(a);
            }
        }
    }

    public static void main(String[] args) {
        Double[] a = PlotBars.createAndShowRandomArray(args);
        sort(a);
    }
}
