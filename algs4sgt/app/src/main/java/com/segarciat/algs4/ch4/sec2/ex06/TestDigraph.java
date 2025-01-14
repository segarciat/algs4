package com.segarciat.algs4.ch4.sec2.ex06;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

/**
 * <strong>4.2.6)</strong>
 * Test client for {@link Digraph}. Builds a graph from a file,
 * and then displays all nodes incident from and incident to
 * a specified vertex.
 * @author Sergio E. Garcia Tapia
 */
public final class TestDigraph {
    public static void main(String[] args) {
        In in = new In(args[0]);
        int s = Integer.parseInt(args[1]);
        Digraph G = new Digraph(in);
        if (s < 0 || s >= G.V()) {
            System.err.println("Invalid vertex");
            return;
        }
        System.out.printf("Incident from %d:", s);
        for (int w: G.adj(s)) {
            System.out.printf(" %d", w);
        }
        System.out.println();

        Digraph R = G.reverse();
        System.out.printf("Incident to   %d:", s);
        for (int w: R.adj(s)) {
            System.out.printf(" %d", w);
        }
        System.out.println();
    }
}
