package com.segarciat.algs4.ch1.sec4.ex08;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * <code>1.4.8)</code>
 * Write a program to determine the number of pairs of values in an input
 * file that are equal. If your first try is quadratic, think again and use
 * <code>Arrays.sort()</code> to develop a linearithmic solution.
 */
public class EqualNumberPairs {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Provide one argument: a file with integers");
            System.exit(1);
        }
        In in = new In(args[0]);
        int[] a = in.readAllInts();
        Arrays.sort(a);

        int count = 0;
        for (int i = 1; i < a.length; i++) {
            // Count repeats
            int frequency = 1;
            while (a[i] == a[i - 1]) {
                frequency++;
                i++;
            }
            // (frequency choose 2) equal pairs
            count += frequency * (frequency - 1) / 2;
        }
        StdOut.println(count);
    }
}
