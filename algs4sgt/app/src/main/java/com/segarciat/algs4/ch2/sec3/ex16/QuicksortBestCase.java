package com.segarciat.algs4.ch2.sec3.ex16;

import com.segarciat.algs4.ch2.SortUtil;
import com.segarciat.algs4.ch2.sec3.ex06.QuicksortCompares;
import edu.princeton.cs.algs4.StdOut;

/**
 * <strong>2.2.16</strong>
 * Implements a method {@link QuicksortBestCase#bestCase(int)} that produces a
 * best-case array of distinct elements for quicksort. That is,
 * if quicksort did not shuffle the array beforehand, then
 * the partition key chosen by the algorithm at each step
 * would cut the array in nearly half each time.
 * @author Sergio E. Garcia Tapia
 */
public class QuicksortBestCase {
    /**
     * Counts the number of compares used by quicksort.
     */
    private static int compares;

    /**
     * Produces a best-case array for quicksort, assuming that quicksort
     * would not shuffle the array before working on it.
     * @param n The size of the desired array.
     * @return An array of n distinct elements that quicksort would sort efficiently.
     */
    public static Integer[] bestCase(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("must positive");
        Integer[] a = new Integer[n];
        a[0] = median(a, 0, n - 1);
        return a;
    }

    /**
     * A helper recursive method for {@link QuicksortBestCase#bestCase(int)}.
     * Computes the median, <code>mid</code>, of <code>lo..hi</code>.
     * Then sets the medians of the left and right sub-arrays at
     * <code>a[mid]</code> and <code>a[mid+1]</code>, respectively.
     * By the end, we have <code>a[lo+1..mid] <=  a[mid]</code>
     * and <code>a[mid] < a[mid+1..hi]</code>.
     * @param a The array to work on recursively.
     * @param lo The smallest valid array index.
     * @param hi The smallest valid
     * @return The (floor of the) median of <code>lo..hi</code>.
     */
    private static int median(Integer[] a, int lo, int hi) {
        if (hi <= lo) {
            return lo;
        }
        int mid = lo + (hi - lo) / 2;
        // in quicksort, a[lo] swaps with a[mid]; the latter becomes partition key of left sub-array.
        a[mid] = median(a, lo, mid - 1);
        // with partition key as above, a[mid+1] is the partition key of the right sub-array.
        a[mid + 1] = median(a, mid + 1, hi);
        return mid;
    }

    /**
     * Uses quicksort to sort an array as-is (that is, it's not shuffled
     * randomly beforehand), making the algorithm sensitive to the
     * distribution of the keys.
     * @param a The array to sort.
     * @return The number of compares used by quicksort
     * @param <T> The type of items in the array.
     */
    public static <T extends Comparable<T>> int sort(T[] a) {
        if (a == null)
            throw new NullPointerException("array cannot be null");
        if (a.length < 2)
            return 0;
        // do away with random shuffle to test best case array
        // StdRandom.shuffle(a);
        compares = 0;
        sort(a, 0, a.length - 1);
        return compares;
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

    /**
     * Determines whether the subarray a[lo..hi] of <code>a</code> is sorted
     * strictly, with all elements distinct.
     */
    public static <T extends Comparable<T>> boolean isSortedAndDistinct(T[] a, int lo, int hi) {
        if (a == null)
            throw new NullPointerException("array cannot be null");
        if (lo < 0 || hi < 0 || lo >= a.length || hi >= a.length)
            throw new IndexOutOfBoundsException("invalid sub-array indices");
        for (int i = lo + 1; i <= hi; i++)
            if (!less(a[i-1], a[i]))
                return  false;
        return true;
    }

    public static void main(String[] args) {
        for (int n = 2; true; n *= 2) {
            Integer[] a = bestCase(n);
            int actualCompares = sort(a);
            assert isSortedAndDistinct(a, 0, n - 1);

            double average = QuicksortCompares.quickSortAvgComparesExact(n);
            StdOut.printf("n=%d, actual=%d, average=%.0f, actualToAverageRatio=%.2f%n",
                    n, actualCompares, average, actualCompares / average);
        }
    }

}
