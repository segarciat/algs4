package com.segarciat.algs4.ch1.sec1.ex27;

public class BinomialDistribution {
    public static double binomial(int n, int k, double p) {
        if (p < 0 || p > 1)
            throw new IllegalArgumentException("p should be at least 0 and at most 1");
        if (n == 0 && k == 0)
            return 1.0;
        if (n < 0 || k < 0)
            return 0.0;
        double[][] binom = new double[n][k + 1];
        return binomial(n, k, p, binom);
    }



    private static double binomial(int n, int k, double p, double[][] binom) {
        if (n == 0 && k == 0)
            return 1.0;
        if (n <= 0 || k < 0)
            return 0.0;

        if (binom[n - 1][k] == 0.0)
            binom[n - 1][k] = binomial(n - 1, k, p, binom);
        if (k > 0 && binom[n - 1][k - 1] == 0.0) {
            binom[n - 1][k - 1] = binomial(n - 1, k - 1, p, binom);
        }
        double right = (k > 0) ? binom[n - 1][k - 1] : 0.0;
        return (1 - p) * binom[n - 1][k] + p * right;
    }

    public static void main(String[] args) {
        System.out.println(binomial(5, 3, 0.25));
    }
}
