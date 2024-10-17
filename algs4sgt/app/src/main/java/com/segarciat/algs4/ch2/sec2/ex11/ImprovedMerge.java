package com.segarciat.algs4.ch2.sec2.ex11;

import com.segarciat.algs4.ch2.SortUtil;

/**
 * @author Sergio E. Garcia Tapia
 * Based on implementation in Secton 2.2 of Algorithms by Sedgewick and Wayne.
 */
public class ImprovedMerge {
    private static final int SMALL_SUBARRAY_SIZE_CUTOFF = 15;

    private ImprovedMerge() {}

    /**
     * Sorts the given array.
     * @param a The array to be sorted.
     * @throws NullPointerException if the array is <code>null</code>.
     */
    public static <T extends Comparable<T>> void sort(T[] a) {
        if (a == null)
            throw new NullPointerException("array cannot be null");
        if (a.length  <= 1)
            return;
        // Auxiliary array used during sorting.
        T[] aux = (T[]) new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    private static <T extends Comparable<T>> void sort(T[] a, T[] aux, int lo, int hi) {
        assert a != null && aux != null;
        assert a.length == aux.length;
        assert a.length >= 1;
        assert 0 <= lo;
        assert lo <= hi;
        assert hi < a.length;

        // Subarrays of size SMALL_SUBARRAY_SIZE_CUTOFF + 1 or less use insertion sort.
        if (hi <= lo + SMALL_SUBARRAY_SIZE_CUTOFF) {
            insertionSort(a, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(aux, a, lo, mid);
        sort(aux, a, mid + 1, hi);

        // Skip merge() if subarrays are ordered
        if (!SortUtil.less(aux[mid+1], aux[mid])) {
            for (int k = lo; k <= hi; k++)
                a[k] = aux[k];
            return;
        }
        merge(a, aux, lo, mid, hi);
    }

    private static <T extends Comparable<T>> void merge(T[] a, T[] aux, int lo, int mid, int hi) {
        assert a != null;
        assert a.length >= 1;
        assert 0 <= lo;
        assert lo <= mid;
        assert mid <= hi;
        assert hi < a.length;
        assert SortUtil.isSorted(aux, lo, mid);
        assert SortUtil.isSorted(aux, mid + 1, hi);

        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                a[k] = aux[j++];
            else if (j > hi)
                a[k] = aux[i++];
            else if (SortUtil.less(aux[j], aux[i]))
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }
    }

    /**
     * Sorts a[lo..hi] with insertion sort.
     */
    private static <T extends Comparable<T>> void insertionSort(T[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            T temp = a[i];
            int j;
            for (j = i; j > lo && SortUtil.less(temp, a[j - 1]); j--)
                a[j] = a[j - 1];
            a[j] = temp;
        }
    }

    public static void main(String[] args) {
        Double[] a = SortUtil.createRandomDoubleArray(1000);
        sort(a);
        assert SortUtil.isSorted(a, 0, a.length - 1);
    }
}
