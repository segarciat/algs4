package com.segarciat.algs4.ch2.sec1.ex11;

import com.segarciat.algs4.ch2.SortUtil;
import edu.princeton.cs.algs4.Stack;

/**
 * @author Sergio E. Garcia Tapia
 * Based on Algorithm 2.3 in Algorithms by Sedgewick and Wayne
 */
public class Shell {
    private Shell() {}

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
                for (int  j = i; j >= h && SortUtil.less(a[j], a[j - h]); j -= h)
                    SortUtil.exchange(a, j, j - h);
            }
        }
    }
}
