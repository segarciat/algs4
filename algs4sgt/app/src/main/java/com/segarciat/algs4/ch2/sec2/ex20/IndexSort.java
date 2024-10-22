package com.segarciat.algs4.ch2.sec2.ex20;

import com.segarciat.algs4.ch2.SortUtil;

/**
 * @author Sergio E. Garcia Tapia
 */
public class IndexSort {
    private IndexSort() {}

    /**
     * Computes the permutation of the indices such that <code>a[perm[0]..perm[n-1]]</code> is
     * sorted, where <code>n</code> is the length of the array <code>a</code>. The array
     * itself will not be sorted.
     * @param a An array with at least 2 elements.
     * @return A permutation of integers 0 through <code>n - 1</code>, where
     * <code>n</code> is the length of the array.
     * @param <T> The type of the elements.
     */
    public static <T extends Comparable<T>> int[] indexSort(T[] a) {
        if (a == null)
            throw new NullPointerException("array cannot be null");
        if (a.length == 0)
            return new int[0];
        else if (a.length == 1)
            return new int[] { 0 };

        int n = a.length;
        int[] perm = new int[n];
        for (int i = 0; i < n; i++)
            perm[i] = i;
        int[] auxPerm = new int[n];
        sort(a, auxPerm, perm, 0, n - 1);
        return perm;
    }

    private static <T extends Comparable<T>> void sort(T[] a, int[] auxPerm, int[] perm, int lo, int hi) {
        if (hi <= lo)
            return;
        int mid = lo + (hi - lo) / 2;
        sort(a, auxPerm, perm, lo, mid);
        sort(a, auxPerm, perm, mid + 1, hi);
        merge(a, auxPerm, perm, lo, mid, hi);
    }

    private static  <T extends Comparable<T>> void merge(T[] a, int[] auxPerm, int[] perm, int lo, int mid, int hi) {
        assert a != null;
        assert a.length >= 1;
        assert 0 <= lo;
        assert lo <= mid;
        assert mid <= hi;
        assert hi < a.length;

        for (int k = lo; k <= hi; k++)
            auxPerm[k] = perm[k];

        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                perm[k] = auxPerm[j++];
            }
            else if (j > hi) {
                perm[k] = auxPerm[i++];
            }
            else if (SortUtil.less(a[auxPerm[j]], a[auxPerm[i]])) {
                perm[k] = auxPerm[j++];
            } else {
                perm[k] = auxPerm[i++];
            }
        }
    }

    private static <T extends Comparable<T>> boolean isSorted(T[] a, int[] perm) {
        assert perm.length == a.length;
        for (int i = 1; i < perm.length; i++)
            if (SortUtil.less(a[perm[i]], a[perm[i-1]]))
                return false;
        return true;
    }

    public static void main(String[] args) {
        for (int n = 4; n <= 1024; n *= 2){
            Double[] a = SortUtil.createRandomDoubleArray(n);
            int[] perm = indexSort(a);
            assert isSorted(a, perm);
        }
    }
}
