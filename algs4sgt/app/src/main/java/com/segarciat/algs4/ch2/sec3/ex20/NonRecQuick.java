package com.segarciat.algs4.ch2.sec3.ex20;

import com.segarciat.algs4.ch2.SortUtil;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * <strong>2.3.20)</strong>
 * A non-recursive implementation of quicksort using a stack.
 *
 * @author Sergio E. Garcia Tapia
 */
public class NonRecQuick {
    private NonRecQuick() {}

    /**
     * Sorts the given array using quicksort.
     * @param a The array to be sorted
     * @param <T> The type of elements in the array.
     * @throws NullPointerException if the given array is <code>null</code>.
     */
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
            // push larger sub-array first
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

    /**
     * Partitions the array <code>a[lo..hi]</code> into sub-array <code>a[lo..j-1]</code>
     * of keys no larger than a given partition key and sub-array <code>a[j+1..hi]</code>
     * of keys no smaller than a partition key.
     * @param a The array to partition.
     * @param lo The lowest valid array index.
     * @param hi The highest valid array index.
     * @return The index of where the partition element lies.
     * @param <T> The type of elements in the array.
     */
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

    /**
     * Times the {@link NonRecQuick#sort(Comparable[])} operation for the
     * given array size <code>n</code>. Returns the elapsed time to complete
     * a total of <code>trials</code> runs.
     * @param n The size of the array.
     * @param trials The number of trials
     * @return The total elapsed time to finish all the trials.
     * @throws IllegalArgumentException if the given integers are nonpositive.
     */
    private static double timeRandom(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("array size and number of trials must be positive");
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
