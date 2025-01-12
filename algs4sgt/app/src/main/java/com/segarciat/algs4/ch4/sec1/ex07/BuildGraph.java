package com.segarciat.algs4.ch4.sec1.ex07;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;

/**
 * <strong>4.1.7)</strong>
 * Uses the constructor that accepts a file stream to build
 * and display a graph specified in a given file.
 * @author Sergio E. Garcia Tapia
 */
public final class BuildGraph {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("""
                    Usage: graph-file
                    Given a text file as a command-line argument, builds the
                    graph and displays it.
                    
                    The first line specifies the number of vertices.
                    The second line is specifies the number of edges that follow.
                    Each line that follows is an edge, written as an unordered pair
                    of integers.
                    """);
            System.exit(1);
        }

        In in = new In(args[0]);
        Graph G = new Graph(in);
        System.out.println(G);
    }
}
