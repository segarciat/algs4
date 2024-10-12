package com.segarciat.algs4.ch1.sec5.ex17;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * @author Sergio E. Garcia Tapia
 */
public class ErdosRenyi {
    /**
     * Determines the number of connections necessary to reduce a
     * set of n sites to a single component.
     * @param n The number of components.
     * @return The number of connections
     */
    public static long count(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("number of pairs must be positive");
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(n);
        long connections = 0;
        while (uf.count() != 1) {
            connections++;
            int p = StdRandom.uniformInt(0, n);
            int q = StdRandom.uniformInt(0, n);
            uf.union(p, q);
        }
        return connections;
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Randomly generates site-pair connections to solve the dynamic connectivity problem");
            System.err.println("Provide command-line arguments: n");
            System.err.println("n    The number of sites.");
            System.exit(1);
        }
        int n = Integer.parseInt(args[0]);
        System.out.println(count(n));
    }
}
