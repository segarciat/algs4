package com.segarciat.algs4.ch4.sec1.ex24;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Queue;

/**
 * <strong>4.1.24)</strong>
 * A version of {@link edu.princeton.cs.algs4.CC} that uses breadth-first search
 * instead of depth-first search, to avoid stack overflow.
 * @author Sergio E> Garcia Tapia
 */
public final class CCBFS {
    private final boolean[] marked;
    private final int[] id;
    private final int[] size;
    private int count;

    public CCBFS(Graph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        size = new int[G.V()];
        for (int s = 0; s < G.V(); s++)
            if (!marked[s]) {
                bfs(G, s);
                count++;
            }
    }

    private void bfs(Graph G, int s) {
        marked[s] = true;
        size[count] = 1;
        id[s] = count;
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(s);
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int w: G.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    id[w] = count;
                    size[count]++;
                    queue.enqueue(w);
                }
            }
        }
    }

    public int count() {
        return count;
    }

    public int id(int v) {
        return id[v];
    }

    public int size(int componentId) {
        return size[componentId];
    }

    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }
}
