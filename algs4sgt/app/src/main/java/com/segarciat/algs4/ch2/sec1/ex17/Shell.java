package com.segarciat.algs4.ch2.sec1.ex17;

import edu.princeton.cs.algs4.Stack;

/**
 * @author Sergio E. Garcia Tapia
 * Based on Algorithm 2.3 in Algorithms by Sedgewick and Wayne
 */
public class Shell {
    private static void sort(Double[] a) {
        if (a == null)
            throw new NullPointerException("array cannot be null");
        int n = a.length;
        Stack<Integer> increments = new Stack<>();
        int h = 1;
        increments.push(h);
        while (h < n / 3) {
            h = 3 * h + 1;
            increments.push(h);
        }
        while (!increments.isEmpty()) {
            h = increments.pop();
            for (int i = h; i < n; i++) {
                for (int  j = i; j >= h && SortUtil.less(a[j], a[j - h]); j -= h) {
                    SortUtil.exchange(a, j, j - h);
                    SortUtil.show(a);
                }
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
