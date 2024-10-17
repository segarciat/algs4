package com.segarciat.algs4.ch2.sec1.ex17;

import com.segarciat.algs4.ch2.SortUtil;

/**
 * @author Sergio E. Garcia Tapia
 * Based on Algorithm 2.1 in Section 2.1 of page 249 of Algorithms by Sedgewick and Wayne.
 */
public class Selection {
    private Selection() {}

    private static void sort(Double[] a) {
        if (a == null)
            throw new NullPointerException("a cannot be null");
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (SortUtil.less(a[j], a[min]))
                    min = j;
            }
            SortUtil.exchange(a, i, min);
            PlotBars.show(a);
        }
    }

    public static void main(String[] args) {
        Double[] a = PlotBars.createAndShowRandomArray(args);
        sort(a);
    }
}
