# Exercise 4.2.9

**Write a method that checks whether a given permutation of a DAG's vertices
is a topological order of that DAG.**

## Solution

Run

```bash
./gradlew -q --console=plain -PmainClass=com.segarciat.algs4.ch4.sec2.ex09.DAGTopological run --args 'algs4-data/tinyDAG.txt'
```

Sample output:

```text
Preorder is topological? false
Postorder is topological? false
Reverse postorder is topological? true
```