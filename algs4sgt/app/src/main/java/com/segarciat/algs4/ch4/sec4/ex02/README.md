# Exercise 4.4.2

Provide implementations of the constructor `EdgeWeightedDigraph(In in)`
and the method `toString()` for `EdgeWeightedDigraph`.

## Solution

Run

```bash
./gradlew -q --console=plain -PmainClass=com.segarciat.algs4.ch4.sec4.ex02.EdgeWeightedDigraph run --args='algs4-data/tinyEWD.txt'
```

The observed output:

```text
8 vertices and 15 edges.
0: 0->2  0.26 0->4  0.38
1: 1->3  0.29
2: 2->7  0.34
3: 3->6  0.52
4: 4->7  0.37 4->5  0.35
5: 5->1  0.32 5->7  0.28 5->4  0.35
6: 6->4  0.93 6->0  0.58 6->2  0.40
7: 7->3  0.39 7->5  0.28
```