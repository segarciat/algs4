# Exercise 4.1.17

The *Wiener index* of a graph is the sum of the lengths of the shortest
paths between all pairs of vertices. Mathematical chemists use this
quantity to analyze *molecular graphs*, where vertices correspond to
atoms and edges correspond to chemical bonds. Add a method `wiener()`
to `GraphProperties` that returns the Wiener index of a graph.

## Solution

Run as:

```bash
./gradlew -q --console=plain -PmainClass=com.segarciat.algs4.ch4.sec1.ex17.GraphProperties run --args='algs4-data/tinyCG.txt'
```

Result:

```text
Diameter: 2
Radius: 2
Center: 0
Wiener index: 22
```