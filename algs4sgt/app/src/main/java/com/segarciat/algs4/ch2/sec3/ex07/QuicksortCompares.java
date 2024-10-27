package com.segarciat.algs4.ch2.sec3.ex07;

import com.segarciat.algs4.ch2.SortUtil;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * <strong>2.3.7)</strong>
 * Counts the number of sub-arrays of size 2 created during
 * the execution of <code>Quick.sort()</code>.
 * @author Sergio E. Garcia Tapia
 */
public class QuicksortCompares {
    private static int sizeTwoOrLess;
    public static <T extends Comparable<T>> void sort(T[] a) {
        if (a == null)
            throw new NullPointerException("array cannot be null");
        if (a.length < 2)
            return;
        StdRandom.shuffle(a);
        sizeTwoOrLess = 0;
        sort(a, 0, a.length - 1);
    }

    private static <T extends Comparable<T>> void sort(T[] a, int lo, int hi) {
        if (hi <= lo + 1) {
            sizeTwoOrLess++;
        }
        if (hi <= lo)
            return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    /**
     * Partition into a[lo..j-1], a[j], a[j+1..hi] and return j.
     * Exactly as given in Section 2.3 of Algorithms by Sedgewick and Wayne.
     */
    private static <T extends Comparable<T>> int partition(T[] a, int lo, int hi) {
        // left and right scan indices
        int i = lo;
        int j = hi + 1;

        // Partitioning item
        T v = a[lo];

        // scan right, scan left, check for scan complete, and exchange
        while(true) {
            while (SortUtil.less(a[++i], v))
                if (i == hi)
                    break;
            while (SortUtil.less(v, a[--j]))
                if (j == lo)
                    break;
            if (i >= j)
                break;
            SortUtil.exchange(a, i, j);
        }
        SortUtil.exchange(a, lo, j);
        return j;
    }

    public static void main(String[] args) {
        final int numberOfTrials = 5;
        for (int n = 2; true; n *= 2) {
            Integer[] a = new Integer[n];
            double averageCount = 0;
            for (int i = 0; i <  n; i++)
                a[i] = i;
            for (int trial = 0; trial < numberOfTrials; trial++) {
                sort(a);
                averageCount += (double) sizeTwoOrLess / numberOfTrials;
            }
            assert SortUtil.isSorted(a, 0, n - 1);
            StdOut.printf("n = %d, Count of Arrays of size 2 or less = %.0f, Ratio of count to n = %.2f%n",
                    n, averageCount, averageCount / n);
        }
    }
}
