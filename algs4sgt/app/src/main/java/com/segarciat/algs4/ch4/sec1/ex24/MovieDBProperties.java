package com.segarciat.algs4.ch4.sec1.ex24;

import com.segarciat.algs4.ch4.sec1.ex18.GraphProperties;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.SymbolGraph;

/**
 * <strong>4.1.24)</strong>
 * Displays information about a graph in the specified file, with
 * relation to a given vertex of the graph. Meant to work with
 * the movies.txt database file in the resources, and "Bacon, Kevin"
 * as the key.
 * @author Sergio E. Garcia Tapia
 */
public final class MovieDBProperties {
    public static void main(String[] args) {
        SymbolGraph sg = new SymbolGraph(args[0], args[1]);
        Graph G = sg.graph();

        CCBFS cc = new CCBFS(G);
        final int sizeThreshold = 10;
        int idLargest = 0;
        int largest = 0;
        int countLt10 = 0;
        for (int v = 0; v < G.V(); v++) {
            int componentId = cc.id(v);
            int componentSize = cc.size(componentId);
            if (largest < componentSize) {
                largest = componentSize;
                idLargest = componentId;
            }
            if (componentSize < sizeThreshold) {
                countLt10++;
            }
        }

        // Build an index and inverted index to map the vertices from the largest component to a new subgraph.
        ST<Integer, Integer> toLargestCompIndex = new ST<>();
        ST<Integer, Integer> invertedLargestCompIndex = new ST<>();
        for (int v = 0; v < G.V(); v++) {
            if (cc.id(v) == idLargest && !toLargestCompIndex.contains(v)) {
                int mappedIndex = toLargestCompIndex.size();
                toLargestCompIndex.put(v, mappedIndex);
                invertedLargestCompIndex.put(mappedIndex, v);
            }
        }

        // Build a graph from the vertices and edges in the last component.
        Graph largestComponent = new Graph(largest);
        for (int vPrime = 0; vPrime < largestComponent.V(); vPrime++) {
            int v = invertedLargestCompIndex.get(vPrime);
            for (int w: G.adj(v)) {
                if (w >= v) {
                    int wPrime = toLargestCompIndex.get(w);
                    largestComponent.addEdge(vPrime, wPrime);
                }
            }
        }

        GraphProperties largestProperties = new GraphProperties(largestComponent);

        StdOut.printf("Number of components: %d%n", cc.count());
        StdOut.printf("Largest component id: %d%n", idLargest);
        StdOut.printf("Size of largest component: %d%n", largest);
        StdOut.printf("Number of components of size less than 10: %d%n", countLt10);
        StdOut.printf("Diameter of largest component: %d%n", largestProperties.diameter());
        StdOut.printf("Radius of largest component: %d%n", largestProperties.radius());
        StdOut.printf("Center of largest component: %d%n", largestProperties.center());
        StdOut.printf("Girth of largest component: %.0f%n", largestProperties.girth());
        StdOut.printf("Vertex \"%s\" (index %d) is in component: %d%n",
                args[2], sg.indexOf(args[2]), cc.id(sg.indexOf(args[2])));
    }
}
