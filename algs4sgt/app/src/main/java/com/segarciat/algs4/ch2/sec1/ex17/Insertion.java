package com.segarciat.algs4.ch2.sec1.ex17;

/**
 * @author Sergio E. Garcia Tapia
 * Based on Algorithm 2.1 in Section 2.2 of page 249 of Algorithms by Sedgewick and Wayne.
 */
public class Insertion {
    private static void sort(Double[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && SortUtil.less(a[j], a[j-1]); j--) {
                SortUtil.exchange(a, j, j - 1);
                SortUtil.show(a);
            }
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
