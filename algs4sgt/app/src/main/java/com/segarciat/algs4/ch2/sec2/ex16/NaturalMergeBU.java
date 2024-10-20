package com.segarciat.algs4.ch2.sec2.ex16;

import com.segarciat.algs4.ch2.SortUtil;

public class NaturalMergeBU {
    private NaturalMergeBU() {}

    public static <T extends Comparable<T>> void sort(T[] a) {
        if (a == null)
            throw new NullPointerException("array cannot be null");
        if (a.length < 2)
            return;

        int n = a.length;
        T[] aux = (T[]) new Comparable[n];
        int hi = 0;
        while (hi < n - 1) {
            int mid = hi;
            while (mid + 1 < n && !SortUtil.less(a[mid + 1], a[mid]))
                mid++;
            if (mid + 1 >= n)
                break;
            hi = mid + 1;
            while (hi + 1 < n && !SortUtil.less(a[hi + 1], a[hi]))
                hi++;
            merge(a, aux, 0, mid, hi);
        }
    }

    private static <T extends Comparable<T>> void merge(T[] a, T[] aux, int lo, int mid, int hi) {
        assert a != null;
        assert a.length >= 1;
        assert 0 <= lo;
        assert lo <= mid;
        assert mid <= hi;
        assert hi < a.length;

        // Temp copy
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];

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

    public static void main(String[] args) {
        Double[] a = SortUtil.createRandomDoubleArray(100);
        sort(a);
        assert SortUtil.isSorted(a, 0, a.length - 1);
    }
}
