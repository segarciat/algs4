package com.segarciat.algs4.ch1.sec1.ex28;

import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * <strong>1.1.28)</strong>
 * <em>Remove duplicates</em>. Modify the test client in <em>BinarySearch</em> to remove
 * any duplicate keys in the whitelist after the sort.
 *
 * @author Sergio E. Garcia Tapia
 */
public class RemoveDuplicates {
    public static void main(String[] args) {
        In in = new In(args[0]);
        int[] referenceList = sortAndDedup(in.readAllInts());
        System.out.println(Arrays.toString(referenceList));

        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if (BinarySearch.indexOf(referenceList, key) == -1)
                StdOut.println(key);
        }
    }

    private static int[] sortAndDedup(int[] a) {
        Arrays.sort(a);
        int i = 0;
        int j = 1;
        while (j < a.length) {
            if (a[i] != a[j])
                a[++i] = a[j];
            j++;
        }
        return Arrays.copyOf(a, i);
    }
}
