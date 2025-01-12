# Exercise 4.1.13

Add a `distTo()` method to the `BreadthFirstPaths` API and implementation,
which returns the number of edges on the shortest path from the source to
a given vertex. A `distTo()` query should run in constant time.

## Solution

Run as follows:

```bash
./gradlew -q --console=plain -PmainClass=com.segarciat.algs4.ch4.sec1.ex13.TestSearch run --args='algs4-data/tinyG.txt 0'
```

Sample output:

```text
Vertex Distance
  0      0
  1      1
  2      1
  3      2
  4      2
  5      1
  6      1
```