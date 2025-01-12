package com.segarciat.algs4.ch4.sec1.ex08;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * <strong>4.1.8)</strong>
 * This is exactly the same code as on page 529, but
 * it uses my implementation of {@link Search} as opposed to
 * {@link edu.princeton.cs.algs4.DepthFirstSearch} as in the book.
 * @author Sergio E. Garcia Tapia
 */
public final class TestSearch {
    public static void main(String[] args) {
        Graph G = new Graph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        Search search = new Search(G, s);

        for (int v = 0; v < G.V(); v++)
            if (search.marked(v))
                StdOut.print(v + " ");
        StdOut.println();

        if (search.count() != G.V())
            StdOut.print("not ");
        StdOut.println("connected");
    }
}
