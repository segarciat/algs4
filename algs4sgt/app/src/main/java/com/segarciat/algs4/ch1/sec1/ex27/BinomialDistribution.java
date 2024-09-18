package com.segarciat.algs4.ch1.sec1.ex27;

/**
 * <strong>1.1.27)</strong>
 * <em>Binomial distribution</em>. Estimate the number of recursive calls that would be
 * 	used by the code
 <pre>
 {@code
*    public static double binomial(int n, int k, double p)
*    {
*        if ((n == 0) && (k == 0)) return 1.0;
*        if ((n  < 0) || (k < 0)) return 0.0;
*        return (1 - p)*binomial(n-1, k, p) + p*binomial(n-1, k-1, p);
*    }
 }
 </pre>
 * to compute <code>binomial(100, 50, 0.25)</code>. Develop a better implementation
 * that is based on saving computed values in an array.
 *
 * @author Sergio E. Garcia Tapia
 */
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
        if (k > 0 && binom[n - 1][k - 1] == 0.0)
            binom[n - 1][k - 1] = binomial(n - 1, k - 1, p, binom);

        double right = (k > 0) ? binom[n - 1][k - 1] : 0.0;
        return (1 - p) * binom[n - 1][k] + p * right;
    }

    public static void main(String[] args) {
        System.out.println(binomial(5, 3, 0.25));
    }
}
