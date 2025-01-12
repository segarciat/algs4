# Exercise 4.1.16

The *eccentricity* of a vertex `v` is the length of the shortest path from that
vertex to the furthest vertex from `v`. The *diameter* of a graph is the maximum
eccentricity of any vertex. The *radius* of a graph is the smallest eccentricity
of any vertex. A *center* is a vertex whose eccentricity is the radius.
Implement the following API:

```
public class GraphProperties
        GraphProperties(Graph G)    // constructor (exception if G not connected)
    int diameter()                  // diameter of G
    int radius()                    // radius of G
    int center()                    // a center of G
```

## Solution

Run

```bash
 ./gradlew -q --console=plain -PmainClass=com.segarciat.algs4.ch4.sec1.ex16.GraphProperties run --args='algs4-data/tinyCG.txt'
```

Sample result:

```text
Diameter: 2
Radius: 2
Center: 0
```