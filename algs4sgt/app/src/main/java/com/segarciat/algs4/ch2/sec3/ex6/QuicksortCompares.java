package com.segarciat.algs4.ch2.sec3.ex6;

import com.segarciat.algs4.ch2.SortUtil;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * <strong>2.3.6)</strong>
 * Computes the exact value of the compares done by <code>Quick.sort()</code>
 * for some array sizes, and compares it against the derived approximation
 * for the average case.
 * @author Sergio E. Garcia Tapia
 */
public class QuicksortCompares {
    private static int compares;
    /**
     * @param n The number of elements in the array, which must be positive.
     * @return The approximate number of compares done by
     * {@link edu.princeton.cs.algs4.Quick#sort(Comparable[])} in the average
     * case.
     * @throws IllegalArgumentException if <code>n</code> is not positive.
     */
    public static double quickSortAvgComparesApprox(int n) {
        if (n < 1)
            throw new IllegalArgumentException("must be positive");
        return 2 *  n * Math.log(n);
    }

    /**
     * @param n The number of elements in the array, which must be positive.
     * @return The "exact" number of compares done by
     * {@link edu.princeton.cs.algs4.Quick#sort(Comparable[])} in the average
     * case.
     * @throws IllegalArgumentException if <code>n</code> is not positive.
     */
    public static double quickSortAvgComparesExact(int n) {
        if (n < 1)
            throw new IllegalArgumentException("must be positive");
        double sum = 0.0;
        for (int k = 3; k <= n + 1; k++)
            sum += 1.0 / k;
        return 2 * (n + 1) * sum;
    }

    public static <T extends Comparable<T>> void sort(T[] a) {
        if (a == null)
            throw new NullPointerException("array cannot be null");
        if (a.length < 2)
            return;
        StdRandom.shuffle(a);
        compares = 0;
        sort(a, 0, a.length - 1);
    }

    private static <T extends Comparable<T>> void sort(T[] a, int lo, int hi) {
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
            while (less(a[++i], v))
                if (i == hi)
                    break;
            while (less(v, a[--j]))
                if (j == lo)
                    break;
            if (i >= j)
                break;
            SortUtil.exchange(a, i, j);
        }
        SortUtil.exchange(a, lo, j);
        return j;
    }

    public static <T extends Comparable<T>> boolean less(T v, T w) {
        compares++;
        return SortUtil.less(v, w);
    }

    public static void main(String[] args) {
        for (int n = 100; n <= 10000; n *= 10) {
            Double[] a = new Double[n];
            for (int i = 0; i <  n; i++)
                a[i] = StdRandom.uniformDouble();
            sort(a);
            assert SortUtil.isSorted(a, 0, n - 1);

            double averageExact = quickSortAvgComparesExact(n);
            double averageApprox = quickSortAvgComparesApprox(n);
            StdOut.printf("n=%d, actual=%d, averageExact=%.0f, averageExactToActualRatio=%.2f, averageApprox=%.0f, averageApproxToActualRatio=%.2f%n",
                    n, compares, averageExact, averageExact / compares, averageApprox, averageApprox / compares);
        }
    }
}
