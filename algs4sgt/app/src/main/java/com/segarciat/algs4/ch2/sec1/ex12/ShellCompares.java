package com.segarciat.algs4.ch2.sec1.ex12;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdRandom;

/**
 * @author Sergio E. Garcia Tapia
 * Based on Algorithm 2.3 in Algorithms by Sedgewick and Wayne
 */
public class ShellCompares {
    public static int cost = 0;

    public static <T extends Comparable<T>> void sort(T[] a) {
        if (a == null)
            throw new NullPointerException("array cannot be null");
        cost = 0;
        int n = a.length;
        Stack<Integer> increments = new Stack<>();
        int h = 1;
        increments.push(h);
        while (h < n / 3) {
            h = 3 * h + 1;
            increments.push(h);
        }
        while (!increments.isEmpty()) {
            for (int i = h; i < n; i++) {
                for (int  j = i; j >= h && less(a[j], a[j - h]); j -= h)
                    exchange(a, j, j - h);
            }
            h = increments.pop();
        }
    }

    public static <T extends Comparable<T>> boolean less(T v, T w) {
        cost++;
        return v.compareTo(w) < 0;
    }

    public static <T extends Comparable<T>> void exchange(T[] a, int i, int j) {
        T temp = a[i];
        a[i] = a[j];
        a[j] =  temp;
    }

    private static Double[] createRandomArray(int n) {
        Double[] a = new Double[n];
        for (int i = 0; i < n; i++)
            a[i] = StdRandom.uniformDouble();
        return a;
    }

    public static void main(String[] args) {
        for (int n = 100; true; n *= 10) {
            Double[] a = createRandomArray(n);
            sort(a);
            System.out.printf("n=%d, ratio=%.1f%n", n, (double) cost / n);
        }
    }
}
