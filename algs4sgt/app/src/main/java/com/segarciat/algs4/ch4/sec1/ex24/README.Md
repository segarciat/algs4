# Exercise 4.1.24

Compute the number of connected components in `movies.txt`, the size of the
largest component, and the number of components of size less than 10. Find
the eccentricity, diameter, radius, a center, and the girth of the largest
component in the graph. Does it contain Kevin Bacon?

## Solution

Run as follows:

```text
./gradlew -q --console=plain -PmainClass=com.segarciat.algs4.ch4.sec1.ex24.MovieDBProperties run --args='algs4-data/movies.txt "/" "Bacon, Kevin"'
```

The output is as follows:

```text
Number of components: 33
Largest component id: 0
Size of largest component: 118774
Number of components of size less than 10: 32
Diameter of largest component: 18
Radius of largest component: 9
Center of largest component: 2057
Girth of largest component: 4
Vertex "Bacon, Kevin" (index 9145) is in component: 0
```

As we can see, the largest component contains Kevin Bacon, but he is not the center.

The `CC` implementation provided with the book distribution uses depth-first
search to compute the number of connected components. Because of the large
size of the connected components in the graph formed from `movies.txt`,
using it resulted in a stack overflow. To overcome this, I implemented `CCBFS`,
a version of `CC` that uses BFS instead of DFS, and therefore which does
not use recursion. With this I was able to find the connected components.

The next challenge was finding the graph properties of the largest component.
The challenge I faced was how to create a separate graph object which that
I knew which vertices and edges belonged to this component. The problem
was that the vertex numbers corresponded to the original graph, so they
were not sequential. As it stands, the `Graph` data type expects a graph
of $V$ vertices to have all vertices indexed in the interval $[0, V - 1]$.
To work out this challenge, I created an index and an inverted index,
so that I could map the indices from the largest component into an
appropriate index set for a new graph and back.

The other challenge was adding the correct edges to the graph. I could
obtain the edges for each vertex in the largest component by using the
adjacency list from the original graph. However, I had to be careful
not to end up with double edges. For example, the `Graph` data type
has operation `addEdge(v, w)` that adds two edges, `v-w` and `w-v`.
The original graph already had the two edges for each pair. If I had
naively looped through all adjacency lists, then I would have called
`addEdge(v, w)` when looking at `v`'s adjacency list and then `addEdge(w, v)`
when looking at `w`'s adjacency list. Since I was adding edges to
the largest component graph by indexing from smallest vertex number to
largest, I simply required that `w >= v` whenever I added an edge from
`v`'s adjacency list, thereby addressing the issue.

Finally, I was able to use `GraphProperties` which I developed in exercises
16, 17, and 18 to determine the remaining properties. Unfortunately, my
implementations cause `bfs()` to be called once for each vertex in the
largest component, so computing the properties took quite long.
