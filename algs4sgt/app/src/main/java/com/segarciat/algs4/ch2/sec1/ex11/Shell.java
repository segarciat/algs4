package com.segarciat.algs4.ch2.sec1.ex11;

import edu.princeton.cs.algs4.Stack;

/**
 * @author Sergio E. Garcia Tapia
 * Based on Algorithm 2.3 in Algorithms by Sedgewick and Wayne
 */
public class Shell {
    public static <T extends Comparable<T>> void sort(T[] a) {
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
                for (int  j = i; j >= h && less(a[j], a[j - h]); j -= h)
                    exchange(a, j, j - h);
            }
        }
    }

    public static <T extends Comparable<T>> boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }

    public static <T extends Comparable<T>> void exchange(T[] a, int i, int j) {
        T temp = a[i];
        a[i] = a[j];
        a[j] =  temp;
    }
}
