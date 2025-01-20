# Exercise 4.4.8

The *diameter* of a digraph is the length of the maximum-length shortest path
connecting two vertices. Write a `DijkstraSP` client that finds the diameter
of a given `EdgeWeighteDigraph` that has non-negative weights.

## Solution

Running

```bash
./gradlew -q --console=plain -PmainClass=com.segarciat.algs4.ch4.sec4.ex08.DiameterEWD run --args='algs4-data/tinyEWD.txt 0'
```

produced

```text
Diameter: 1.860000
```