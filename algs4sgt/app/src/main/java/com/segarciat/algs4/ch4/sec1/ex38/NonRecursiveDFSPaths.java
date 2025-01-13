package com.segarciat.algs4.ch4.sec1.ex38;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * <strong>4.1.38)</strong>
 * Non-recursive implementation of depth-first search (in the same
 * vein as {@link edu.princeton.cs.algs4.DepthFirstPaths}).
 * @author Sergio E. Garcia Tapia
 */
public class NonRecursiveDFSPaths {
    private final boolean[] marked;
    private final int[] edgeTo;
    private final int s;

    public NonRecursiveDFSPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }

    private void dfs(Graph G, int s) {
        marked[s] = true;
        Stack<Iterator<Integer>> adj = new Stack<>();
        Stack<Integer> vertices = new Stack<>();
        adj.push(G.adj(s).iterator());
        vertices.push(s);

        while (!adj.isEmpty()) {
            Iterator<Integer> vAdj = adj.peek();
            int v = vertices.peek();
            while (vAdj.hasNext()) {
                int w = vAdj.next();
                if (!marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = v;
                    adj.push(G.adj(w).iterator());
                    vertices.push(w);
                    break;
                }
            }
            if (vAdj == adj.peek() && !vAdj.hasNext()) {
                adj.pop();
                vertices.pop();
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v))
            return null;

        Stack<Integer> stack = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x])
            stack.push(x);
        stack.push(s);
        return stack;
    }

    public static void main(String[] args) {
        Graph G = new Graph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        NonRecursiveDFSPaths search = new NonRecursiveDFSPaths(G, s);
        for (int v = 0; v < G.V(); v++) {
            StdOut.print(s + " to " + v + ": ");
            if (search.hasPathTo(v)) {
                for (int x : search.pathTo(v)) {
                    if (x == s)
                        StdOut.print(x);
                    else
                        StdOut.print("-" + x);
                }
            }
            StdOut.println();
        }
    }
}
