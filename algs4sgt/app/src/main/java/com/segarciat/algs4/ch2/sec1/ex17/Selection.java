package com.segarciat.algs4.ch2.sec1.ex17;

/**
 * @author Sergio E. Garcia Tapia
 * Based on Algorithm 2.1 in Section 2.1 of page 249 of Algorithms by Sedgewick and Wayne.
 */
public class Selection {
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
            SortUtil.show(a);
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Sorts n values and shows the process by drawing vertical bars");
            System.err.println("Provide a command-line argument: n");
            System.err.println("n    The number of vertical bars");
        }
        int n = Integer.parseInt(args[0]);
        Double[] a = SortUtil.randomValues(n);
        SortUtil.show(a);
        sort(a);
    }
}
