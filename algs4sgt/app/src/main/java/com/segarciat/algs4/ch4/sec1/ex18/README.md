# Exercise 4.1.18

The *girth* of a graph is the length of its shortest cycle. If a graph is
acyclic, then its girth is infinite. Add a method `girth()` to `GraphProperties`
that returns the girth of the graph. *Hint*: Run BFS from each vertex. The
shortest cycle containing `s` is an edge between `s` and some vertex `v` 
concatenated with a shortest path between `s` and `v` (that doesn't use
edge `s-v`).

## Solution

Run as:

```bash
./gradlew -q --console=plain -PmainClass=com.segarciat.algs4.ch4.sec1.ex18.GraphProperties run --args='algs4-data/tinyCG.txt'
```

Result:

```text
Diameter: 2
Radius: 2
Center: 0
Wiener index: 22
Girth: 3
```