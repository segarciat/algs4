package com.segarciat.algs4.ch4.sec1.ex13;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

/**
 * <strong>4.1.13)</strong>
 * Enhances {@link edu.princeton.cs.algs4.BreadthFirstPaths} with
 * a method {@link #distTo(int)} that operates in constant time.
 * @author Sergio E. Garcia Tapia
 */
public final class BreadthFirstPaths {
    private final boolean[] marked;
    private final int[] edgeTo;
    private final int[] distTo;
    private final int s;

    public BreadthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        distTo = new int[G.V()];
        this.s = s;
        bfs(G, s);
    }

    public void bfs(Graph G, int s) {
        marked[s] = true;
        distTo[s] = 0;
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(s);
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int w: G.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    queue.enqueue(w);
                }

            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v))
            return null;

        Stack<Integer> path = new Stack<>();
        int x;
        for (x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }

    public int distTo(int v) {
        if (!hasPathTo(v)) return -1;
        return distTo[v];
    }
}
