package com.segarciat.algs4.ch4.sec1.ex26;

import com.segarciat.algs4.ch4.sec1.ex38.NonRecursiveDFSPaths;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.SymbolGraph;

/**
 * <strong>4.1.26)</strong>
 * Degrees of separation as in {@link edu.princeton.cs.algs4.DegreesOfSeparation},
 * but using depth-first search.
 * @author Sergio E. Garcia Tapia
 */
public final class DegreesOfSeparationDFS {
    public static void main(String[] args) {
        SymbolGraph sg = new SymbolGraph(args[0], args[1]);
        Graph G = sg.graph();
        String source = args[2];
        if (!sg.contains(source)) {
            StdOut.println(source+ " not in database");
            return;
        }

        int s = sg.indexOf(source);
        NonRecursiveDFSPaths dfs = new NonRecursiveDFSPaths(G, s);

        while (!StdIn.isEmpty()) {
            String sink = StdIn.readLine();
            if (sg.contains(sink)) {
                int t = sg.indexOf(sink);
                if (dfs.hasPathTo(t)) {
                    for (int v: dfs.pathTo(t))
                        StdOut.println("   " + sg.nameOf(v));
                } else {
                    StdOut.println("not connected");
                }
            } else {
                StdOut.println("not in database");
            }
        }
    }
}
