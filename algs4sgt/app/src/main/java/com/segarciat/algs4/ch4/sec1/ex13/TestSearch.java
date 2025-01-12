package com.segarciat.algs4.ch4.sec1.ex13;

import com.segarciat.algs4.ch4.sec1.ex08.Search;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * <strong>4.1.13)</strong>
 * This is exactly the same code as on page 529, but
 * it uses my implementation of {@link Search} as opposed to
 * {@link edu.princeton.cs.algs4.DepthFirstSearch} as in the book.
 * @author Sergio E. Garcia Tapia
 */
public final class TestSearch {
    public static void main(String[] args) {
        Graph G = new Graph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        BreadthFirstPaths search = new BreadthFirstPaths(G, s);

        StdOut.println("Vertex Distance");
        for (int v = 0; v < G.V(); v++)
            if (search.hasPathTo(v)) {
                StdOut.printf("%3d    %3d%n", v, search.distTo(v));
            }
    }
}
