# Exercise 4.1.26

Write a `SymbolGraph` client like `DegreesOfSeparation` that uses *depth-first*
search instead of breadth-first search to find paths connecting two performers.

## Solution

Run as follows:

```bash
./gradlew -q --console=plain -PmainClass=com.segarciat.algs4.ch4.sec1.ex26.DegreesOfSeparationDFS run --args='algs4-data/movies.txt "/" "Bacon, Kevin"'
```

Initially I encountered a stack overflow using the traditional recursive implementation,
similar to the issue I encountered in Exercise 24. I noticed that exercise 38 asked
to implement DFS non-recursively, and I went on to do that (see `com.segarciat.algs4.ch4.sec1.ex38`),
and then I leveraged it in this exercise successfully.