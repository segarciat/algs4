package com.segarciat.algs4.ch2.sec2.ex19;

import com.segarciat.algs4.ch2.SortUtil;

/**
 * @author Sergio E. Garcia Tapia
 */
public class Inversions {
    private Inversions() {}

    /**
     * Uses brute force to compute the number of inversions in the given array
     * without altering the array.
     * @param a An array with at least 2 elements.
     * @return The number of inversions in the array.
     * @param <T> The type of the elements in the array.
     * @throws NullPointerException if the array is <code>null</code>.
     */
    public static <T extends  Comparable<T>> int inversionsBruteForce(T[] a) {
        if (a == null)
            throw new NullPointerException("array cannot be null");
        if (a.length < 2)
            return 0;

        int inversions = 0;
        for (int i = 1; i < a.length; i++) {
            for (int j = 0; j < i; j++) {
                if (SortUtil.less(a[i], a[j]))
                    inversions++;
            }
        }
        return inversions;
    }

    /**
     * Computes the number of inversions in a given array without altering
     * the array.
     * @param a A non-null array with at least 1 element.
     * @return The number of inversions in the given array.
     * @param <T> The type of the elements in the array.
     * @throws NullPointerException if the array is <code>null</code>.
     */
    public static <T extends Comparable<T>> int inversions(T[] a) {
        if (a == null)
            throw new NullPointerException("array cannot be null");
        if (a.length < 2)
            return 0;

        int n = a.length;
        T[] temp = (T[]) new Comparable[n];
        System.arraycopy(a, 0, temp, 0, n);
        T[] aux = (T[]) new Comparable[n];
        return sort(temp, aux, 0, n - 1);
    }

    /**
     * Sorts the array a[lo..hi] using mergesort and returns the number of inversions
     * encountered.
     */
    private static <T extends Comparable<T>> int sort(T[] a, T[] aux, int lo, int hi) {
        if (hi <= lo)
            return 0;
        int mid = lo + (hi - lo) / 2;
        int inversions = 0;
        inversions += sort(a, aux, lo, mid);
        inversions += sort(a, aux, mid + 1, hi);
        inversions += merge(a, aux, lo, mid, hi);
        return inversions;
    }

    /**
     * Merges sorted subarrays a[lo..mid] and a[mid+1..hi] and returns the
     * number of inversions.
     */
    private static <T extends Comparable<T>> int merge(T[] a, T[] aux, int lo, int mid, int hi) {
        assert a != null;
        assert a.length >= 1;
        assert 0 <= lo;
        assert lo <= mid;
        assert mid <= hi;
        assert hi < a.length;

        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];

        int inversions = 0;

        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                a[k] = aux[j++];
            else if (j > hi) {
                // inversion for each remaining element, but already accounted for
                a[k] = aux[i++];
            }
            else if (SortUtil.less(aux[j], aux[i])) {
                // elements in aux[i..mid] are larger than aux[j]
                inversions += mid - i + 1;
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
        return inversions;
    }

    public static void main(String[] args) {
        for (int n = 4; n <= 4096; n *= 2){
            Double[] a = SortUtil.createRandomDoubleArray(n);
            int inversions = inversions(a);
            int inversionsBf = inversionsBruteForce(a);
            System.out.printf("n=%d, inversions=%d, bruteForce=%d%n",
                    n, inversions, inversionsBf);
        }
    }
}
