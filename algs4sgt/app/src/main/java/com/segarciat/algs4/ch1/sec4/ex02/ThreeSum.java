package com.segarciat.algs4.ch1.sec4.ex02;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * <strong>1.4.2)</strong>
 * Modify <code>ThreeSum</code> to work properly even when the <code>int</code>
 * values are so large that adding two of them might cause integer overflow.
 */
public class ThreeSum {

    /**
     * Determines of x + y overflows.
     *
     * @param x The first value.
     * @param y The second value.
     * @param sum The sum of x and y.
     * @return <code>true</code> if x + y overflows, <code>false</code> otherwise.
     */
    private static boolean sumOverflows(int x, int y, int sum) {
        return (x < 0 && y < 0 && sum >= 0) || (x > 0 && y > 0 && sum <= 0);
    }

    /**
     * Find all the triples in <code>a</code> that sum to 0.
     *
     * @param a Non-empty array of integers
     *
     * @return How many triples sum to 0.
     */
    public static int count(int[] a) {
        int n = a.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int s = a[i] + a[j];
                boolean overflowed = sumOverflows(a[i], a[j], s);
                // Any integer a[k] cannot make a[i] + a[j] + a[k] zero if s overflows,
                // except when s is Integer.MIN_VALUE
                if (s != Integer.MIN_VALUE && overflowed)
                    continue;
                for (int k = j + 1; k < n; k++) {
                    if (s + a[k] == 0 && (s != Integer.MIN_VALUE || overflowed))
                        count++;
                }
            }
        }
        return count;
    }

    /**
     * Same <code>main()</code> implementation as in code listing in page 173
     * of Algorithms by Sedgewick and Wayne.
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Provide a single argument: a file with integers");
            System.exit(1);
        }
        In in = new In(args[0]);
        int[] a = in.readAllInts();
        StdOut.println(count(a));
    }
}
