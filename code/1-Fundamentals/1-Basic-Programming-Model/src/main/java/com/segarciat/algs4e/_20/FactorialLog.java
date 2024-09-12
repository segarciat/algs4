package com.segarciat.algs4e._20;

/**
 * 1.1.20
 * Write a recursive static method that computes the value of ln(n!).
 */
public class FactorialLog {
    /**
     * Computes ln(n!), the natural logarithm of n factorial.
     * Warning: Beware of error propagation.
     * @param n Positive integer.
     * @return The natural logarithm of n factorial.
     */
    public static double factorialLog(long n) {
        if (n <= 0)
            throw new IllegalArgumentException("n must be positive");
        if (n == 1)
            return 0;
        return Math.log(n) + factorialLog(n - 1);
    }
}
