package com.segarciat.algs4.ch2.sec3.ex20;

import com.segarciat.algs4.ch2.SortUtil;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class NonRecQuick {
    private NonRecQuick() {}

    public static <T extends Comparable<T>> void sort(T[] a) {
        if (a == null)
            throw new NullPointerException("array cannot be null");
        if (a.length < 2)
            return;

        StdRandom.shuffle(a);

        // Always push lo before hi
        Stack<Integer> subArrays = new Stack<>();
        subArrays.push(0);
        subArrays.push(a.length - 1);

        while (!subArrays.isEmpty()) {
            int hi = subArrays.pop();
            int lo = subArrays.pop();

            if (hi <= lo)
                continue;

            int j = partition(a, lo, hi);
            // push larger subarray first
            if ((j - lo) > (hi - j)) {
                subArrays.push(lo);
                subArrays.push(j-1);
                subArrays.push(j+1);
                subArrays.push(hi);
            } else {
                subArrays.push(j+1);
                subArrays.push(hi);
                subArrays.push(lo);
                subArrays.push(j-1);
            }
        }
    }

    private static <T extends Comparable<T>> int partition(T[]a, int lo, int hi) {
        T v = a[lo];
        int i = lo;
        int j = hi + 1;

        while(true) {
            while (SortUtil.less(a[++i], v))
                if (i >= hi)
                    break;
            while (SortUtil.less(v, a[--j]))
                if (j <= lo)
                    break;
            if (i >= j)
                break;
            SortUtil.exchange(a, i, j);
        }
        SortUtil.exchange(a, j, lo);
        return j;
    }

    private static double timeRandom(int n, int trials) {
        Double[] a = new Double[n];
        for (int i = 0; i < n; i++)
            a[i] = StdRandom.uniformDouble();
        double elapsed = 0.0;
        for (int i = 0; i < trials; i++) {
            Stopwatch timer = new Stopwatch();
            sort(a);
            elapsed += timer.elapsedTime();
            assert SortUtil.isSorted(a, 0, n - 1);
        }
        return elapsed;
    }

    public static void main(String[] args) {
        final int trials = 5;
        double prev = timeRandom(256, trials) / trials;
        for (int n = 256; true; n *= 2) {
            double time = timeRandom(n, trials) / trials;
            StdOut.printf("n=%d, ratio=%.1f%n", n, time / prev);
            prev = time;
        }
    }
}
