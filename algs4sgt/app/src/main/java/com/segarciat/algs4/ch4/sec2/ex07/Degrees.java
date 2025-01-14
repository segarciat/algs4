package com.segarciat.algs4.ch4.sec2.ex07;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;

/**
 * <strong>4.2.7)</strong>
 * Given a {@link Digraph}, computes indegres and outdegrees of all vertices, determines
 * whether it is a map, and computes sources and sinks.
 * @author Sergio E. Garcia Tapia
 */
public final class Degrees {
    private final int[] indegrees;
    private final int[] outdegrees;
    private final Bag<Integer> sources;
    private final Bag<Integer> sinks;
    private final boolean isMap;

    public Degrees(Digraph G) {
        if (G == null)
            throw new NullPointerException("graph cannot be null");

        outdegrees = new int[G.V()];
        sinks = new Bag<>();
        boolean allOutDegreesAre1 = true;
        for (int v = 0; v < G.V(); v++) {
            int outDegree = 0;
            for (int w: G.adj(v))
                outDegree++;
            outdegrees[v] = outDegree;
            if (outDegree == 0)
                sinks.add(v);
            allOutDegreesAre1 = (outDegree == 1);
        }
        isMap = allOutDegreesAre1;

        Digraph R = G.reverse();
        indegrees = new int[G.V()];
        sources = new Bag<>();
        for (int v = 0; v < R.V(); v++) {
            int inDegree = 0;
            for (int w: R.adj(v))
                inDegree++;
            indegrees[v] = inDegree;
            if (inDegree == 0)
                sources.add(v);
        }
    }

    private int validateVertex(int v) {
        if (v < 0 || v >= outdegrees.length)
            throw new IllegalArgumentException("invalid vertex");
        return v;
    }

    public int indegree(int v) {
        return indegrees[validateVertex(v)];
    }

    public int outdegree(int v) {
        return outdegrees[validateVertex(v)];
    }

    public boolean isMap() {
        return isMap;
    }

    public Iterable<Integer> sources() {
        Bag<Integer> bag = new Bag<>();
        for (int v: sources)
            bag.add(v);
        return bag;
    }

    public Iterable<Integer> sinks() {
        Bag<Integer> bag = new Bag<>();
        for (int v: sinks)
            bag.add(v);
        return bag;
    }
}
