package com.segarciat.algs4.ch1.sec4.ex12;

/**
 * <strong>1.4.12)</strong>
 * Write a program that, given two sorted arrays of <em>n</em> <code>int</code>
 * values, prints all elements that appear in both arrays, in sorted order.
 * The running time of your program should be proportional to <em>n</em>
 * in the worst case.
 */
public class PrintTwoSortedArrays {
    /**
     * Given two sorted arrays, prints all elements from both arrays in
     * sorted order.
     * @param a A sorted array.
     * @param b A sorted array.
     */
    public static void printSortedArrays(int[] a, int [] b) {
        if (a == null || b == null)
            throw new NullPointerException("arrays cannot be null");

        int i = 0;
        int j = 0;
        while (i < a.length && j < b.length) {
            if (a[i] < b[j])
                System.out.println(a[i++]);
            else
                System.out.println(b[j++]);
        }

        // One of the arrays has been exhausted
        while (i < a.length)
            System.out.println(a[i++]);
        while (j < b.length)
            System.out.println(b[j++]);
    }

    public static void main(String[] args) {
        int[] a = { 1, 3, 4, 4, 6, 8 };
        int[] b = { 0, 2, 3, 3, 4, 7 };
        printSortedArrays(a, b);
    }
}
