package com.segarciat.algs4.ch4.sec1.ex30;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Stack;

/**
 * <strong>4.1.30)</strong>
 * Detects whether a given undirected graph has an Eulerian cycle,
 * and if so, finds one.
 * @author Sergio E. Garcia Tapia
 */
public class EulerianCycle {
    private boolean hasEulerianCycle;
    private Stack<Integer> eulerianCycle;
    private int count;

    public EulerianCycle(Graph G) {
    }

    public boolean hasEulerianCycle() {
        // only one connected component, all degrees are even
        return hasEulerianCycle;
    }

    /**
     * @return An iterable for a cycle, or <code>null</code> if
     * one does not exist.
     */
    public Iterable<Integer> eulerianCycle() {
        return eulerianCycle;
    }
}
