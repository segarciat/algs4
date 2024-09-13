package com.segarciat.algs4.ch1.sec1.ex24;

import edu.princeton.cs.algs4.StdOut;

/**
 * 1.1.24) Extend the code on page 4 to develop a program <code>Euclid</code>
 * that takes two integers from the command line and computes their greatest
 * common divisor, printing out the two  arguments for each call on the recursive
 * method. Use your program to compute the greatest common divisor of 1111111
 * and 1234567.
 */
public class Euclid {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java Euclid p q");
            System.err.println("Given integers p and q, computes their greatest common divisor.");
        }

        int p = Integer.parseInt(args[0]);
        int q = Integer.parseInt(args[1]);
        System.out.println(gcd(p, q));
    }

    /**
     * Computes the greatest common divisor of p and q.
     *
     * @param p Non-negative integer.
     * @param q Non-negative integer.
     *
     * @return The greatest common divisor of p and q.
     */
    public static long gcd(int p, int q) {
        if (p < 0 || q < 0)
            throw new IllegalArgumentException("p and q must be non-negative integers");
        return gcd(p, q, new StringBuilder());
    }

    /**
     * Adapted from the recursive <code>indexOf()</code> implementation in page 25 of Algorithms
     * 4th Edition by Sedgewick and Wayne.
     * Recursively computes the greatest common divisor of non-negative integers <code>p</code>
     * and <code>q</code> using Euclid's algorithm. Prints a call trace for each recursive
     * invocation.
     *
     * @param p Non-negative integer.
     * @param q Non-negative integer.
     * @param sbDepth Builder used to indent traces according to call depth.
     *
     * @return The greatest common divisor of p and q.
     */
    private static long gcd(int p, int q, StringBuilder sbDepth) {
        StdOut.printf("%sp: %d, q: %d%n", sbDepth.toString(), p, q);
        if (q == 0)
            return p;
        int r = p % q;
        return gcd(q, r, sbDepth.append("  "));
    }
}
