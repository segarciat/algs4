# Exercise 4.1.15

Modify the input stream constructor for `Graph` to also allow
adjacency lists from standard input (in a manner similar to `SymbolGraph`),
as in the example `tinyGadj.txt`:

For example, `tinyGadj.txt` looks as follows:

```text
13
13
0 1 2 5 6
4 5 6
7 8
9 10 11 12
11 12
```

Then running `java Graph tinyGadj.txt` would output

```text
13 vertices, 13 edges
0: 6 5 2 1
1: 0
3: 5 4
4: 6 5 3
5: 4 3 0
6: 4 0
7: 8
8: 7
9: 12 11 10
10: 9
11: 12 9
12: 11 9
```

After the number of vertices and edges, each line contains a vertx and its list of adjacent vertices.

## Solution

Run

```bash
./gradlew -q --console=plain -PmainClass=com.segarciat.algs4.ch4.sec1.ex15.Graph run --args='src/main/java/com/segarciat/algs4/ch4/sec1/ex15/tinyGadj.txt'
```