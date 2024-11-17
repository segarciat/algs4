package com.segarciat.algs4.ch2.sec5.ex19;

import com.segarciat.algs4.ch2.sec2.ex19.Inversions;

import java.util.Arrays;

/**
 * <code>2.5.19)</code>
 * Implements an algorithm to compute the Kendall tau distance between
 * two given permutations.
 * @author Sergio E. Garcia Tapia
 */
public final class KendallTau {
    private KendallTau() {}

    /**
     * Computes the Kendall tau distance between the two given arrays,
     * both of which are the same length <code>n</code>, which are permutations
     * of the non-negative integers <code>0</code> through <code>n-1</code>,
     * with no duplicates. The method does not verify that the arrays are
     * indeed permutations, and it does not modify the arrays.
     *
     * @param a A permutation of the non-negative integers.
     * @param b A permutation of the non-negative integers.
     * @return The number of pairs that are in different order in the two permutations.
     * @throws NullPointerException     if either array is <code>null</code>.
     * @throws IllegalArgumentException if the arrays are distinct length.
     */
    public static int distance(int[] a, int[] b) {
        if (a == null || b == null)
            throw new NullPointerException("arrays may not be null");
        if (a.length != b.length)
            throw new IllegalArgumentException("arrays must be of same length");

        int n = a.length;

        if (n <= 1)
            return 0;

        // Array b defines the sort values
        int[] sortValue = new int[n];
        for (int i = 0; i < n; i++)
            sortValue[b[i]] = i;

        // Neither array will be modified
        int[] aCopy = new int[n];
        for (int i = 0; i < n; i++)
            aCopy[i] = a[i];
        int[] aux = new int[n];
        return count(aCopy, aux, sortValue, 0, n - 1);
    }

    /**
     * Helper recursive method for {@link KendallTau#distance(int[], int[])}, which operates
     * similar to mergesort.
     * @param a The array for which we are computing inversions.
     * @param aux An auxiliary array for sorting.
     * @param sortOrder An array defining the value of integers <code>0</code> through <code>n - 1</code>,
     *                  where <code>n</code> is the length of <code>a</code> and <code>aux</code>.
     * @param lo The lower index on which we are operating (inclusive).
     * @param hi The upper index on which we are operating (inclusive).
     * @return The number of inversions in <code>a</code> according to the order defined by <code>sortedOrder</code>.
     */
    private static int count(int[] a, int[] aux, int[] sortOrder, int lo, int hi) {
        if (hi <= lo)
            return 0;
        int mid = lo + (hi - lo) / 2;
        int outOfOrder = 0;
        outOfOrder += count(a, aux, sortOrder, lo, mid);
        outOfOrder += count(a, aux, sortOrder, mid + 1, hi);
        outOfOrder += merge(a, aux, sortOrder, lo, mid, hi);
        return outOfOrder;
    }

    /**
     * Helper recursive method for {@link KendallTau#count(int[], int[], int[], int, int)},
     * which merges sorted sub-arrays <code>a[lo..mid]</code> and <code>a[mid+1..hi]</code> into
     * sorted array <code>a[lo..hi]</code> according to the order defined by <code>sortedOrder</code>.
     * Th array <code>aux</code> is an auxiliary array of the same length.
     * @return
     */
    private static int merge(int[] a, int[] aux, int[] sortOrder, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];

        int i = lo;
        int j = mid + 1;
        int outOfOrder = 0;

        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (sortOrder[aux[j]] < sortOrder[aux[i]]) {
                outOfOrder += mid - i + 1;
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
        return outOfOrder;
    }

    public static void main(String[] args) {
        int[] identity = {0, 1, 2, 3, 4, 5, 6};
        int[] a = {0, 3, 1, 6, 2, 5, 4};
        int[] b = {1, 0, 3, 6, 4, 2, 5};

        // Kendall tau distance between a and b
        System.out.printf("Kendall tau distance between a and b: %d%n", distance(a, b));
        System.out.printf("Kendall tau distance between b and a: %d%n", distance(b, a));

        // Inversions in a
        System.out.println();
        System.out.printf("Kendall tau distance between a and identity: %d%n", distance(a, identity));
        System.out.printf("Kendall tau distance between identity and a: %d%n", distance(identity, a));
        System.out.printf("Inversions in a: %d%n",
                Inversions.inversions(Arrays.stream(a).boxed().toArray(Integer[]::new)));

        // Inversions in b
        System.out.println();
        System.out.printf("Kendall tau distance between b and identity: %d%n", distance(b, identity));
        System.out.printf("Kendall tau distance between identity and b: %d%n", distance(identity, b));
        System.out.printf("Inversions in b: %d%n",
                Inversions.inversions(Arrays.stream(a).boxed().toArray(Integer[]::new)));

    }
}