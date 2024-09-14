package com.segarciat.algs4.ch1.sec1.ex19;

import edu.princeton.cs.algs4.StdOut;

public class Fibonacci {
    public static long fibonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fibonacci(n-1) + fibonacci(n - 2);
    }

    /**
     * Produces the value of the nth Fibonacci number.
     * @param n Non-negative integer.
     * @return The nth Fibonacci number.
     */
    public static long fibonacciFaster(int n) {
        if (n < 0)
            throw new IllegalArgumentException("n must be non-negative");

        long[] fib = new long[n];
        return fibonacciFaster(n, fib);
    }

    /**
     * Helper method for 'long fibonacciFaster(int n)'. Recursively computes
     * the nth fibonacci number by caching intermediate results in an array.
     * @param n Non-negative integer.
     * @param fib Array of computed fibonacci numbers.
     * @return The nth Fibonacci number.
     */
    private static long fibonacciFaster(int n, long[] fib) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        if (fib[n - 2] == 0)
            fib[n - 2] = fibonacciFaster(n - 2, fib);
        if (fib[n - 1] == 0)
            fib[n - 1] = fibonacciFaster(n - 1, fib);
        return fib[n - 1] + fib[n - 2];
    }

    public static void main(String[] args) {
        for (int n = 0; n < 90; n++) {
            long  start = System.currentTimeMillis();
            long fib = fibonacciFaster(n);
            double secondElapsed = (System.currentTimeMillis() - start) / 1000.0;
            StdOut.printf("%d %d (%.4f seconds)%s", n, fib, secondElapsed, System.lineSeparator());
        }
    }
}
