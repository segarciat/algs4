package com.segarciat.algs4.ch4.sec2.ex09;

import edu.princeton.cs.algs4.DepthFirstOrder;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * <strong>4.2.9)</strong>
 * Implements {@link #isTopologicalOrder(Digraph, Iterable)} to determine
 * whether a given permutation of vertices is a topological order.
 * @author Sergio E. Garcia Tapia
 */
public class DAGTopological {
    /**
     * Determines whether a given permutation of the vertices of a
     * DAG (Directed Acyclic Graph) is a topological order.
     * @param G A directed acyclic graph.
     * @param permutation A permutation of the vertices of the given graph.
     * @return <code>true</code> if the given permutation is a topological
     * order, <code>false</code> otherwise.
     * @throws NullPointerException if <code>G</code> or <code>permutation</code> is <code>null</code>.
     * @throws IllegalArgumentException if <code>G</code> is not a DAG.
     */
    public static boolean isTopologicalOrder(Digraph G, Iterable<Integer> permutation) {
        if (G == null || permutation == null)
            throw new NullPointerException("cannot be null");

        DirectedCycle cycleFinder = new DirectedCycle(G);
        if (cycleFinder.hasCycle())
            throw new IllegalArgumentException("not a DAG");

        int count = 0;
        boolean[] seen = new boolean[G.V()];
        for (int v: permutation) {
            if (v < 0 || v >= G.V() || seen[v])
                return false;

            // v->w, w should not have occurred already.
            for (int w: G.adj(v))
                if (seen[w])
                    return false;

            seen[v] = true;
            count++;
        }

        // count distinct vertices encountered
        return count == G.V();
    }

    public static void main(String[] args) {
        Digraph G = new Digraph(new In(args[0]));
        DirectedCycle cycleFinder = new DirectedCycle(G);
        if (cycleFinder.hasCycle()) {
            StdOut.println("Not a DAG");
            return;
        }
        DepthFirstOrder order = new DepthFirstOrder(G);
        StdOut.printf("Preorder is topological? %s%n", isTopologicalOrder(G, order.pre()));
        StdOut.printf("Postorder is topological? %s%n", isTopologicalOrder(G, order.post()));
        StdOut.printf("Reverse postorder is topological? %s%n", isTopologicalOrder(G, order.reversePost()));
    }
}
