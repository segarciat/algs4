package com.segarciat.algs4.ch4.sec1.ex16;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 * <strong>4.1.16)</strong>
 * Computes the diameter, radius, and center of a connected graph.
 * @author Sergio E. Garcia Tapia
 */
public final class GraphProperties {
    private int diameter;
    private int radius;
    private int center;
    public GraphProperties(Graph G) {
        if (G.V() < 1)
            throw new IllegalArgumentException("empty graph");

        diameter = radius = bfs(G, 0);
        center = 0;
        for (int v = 1; v < G.V(); v++) {
            int eccentricity = bfs(G, v);
            if (eccentricity > diameter) {
                diameter = eccentricity;
            }
            if (eccentricity < radius) {
                radius = eccentricity;
                center = v;
            }
        }
    }

    public int diameter() {
        return diameter;
    }

    public int radius() {
        return radius;
    }

    public int center() {
        return center;
    }

    private int bfs(Graph G, int s) {
        boolean[] marked = new boolean[G.V()];
        int[] dist = new int[G.V()];
        dist[0] = 0;
        marked[s] = true;

        Queue<Integer> queue = new Queue<>();
        queue.enqueue(s);
        int count = 1;
        int longest = 0;
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int w: G.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    dist[w] = dist[v] + 1;
                    longest = dist[w];
                    count++;
                    queue.enqueue(w);
                }
            }
        }
        if (count != G.V())
            throw new IllegalArgumentException("G is not connected");
        return longest;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        GraphProperties props = new GraphProperties(G);
        StdOut.printf("Diameter: %d%n", props.diameter());
        StdOut.printf("Radius: %d%n", props.radius());
        StdOut.printf("Center: %d%n", props.center());
    }
}
