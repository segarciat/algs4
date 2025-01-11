# Exercise 4.1.7

Develop a test client for `Graph` that reads a graph from the input stream named
command-line argument and the prints it, relying on `toString()`.

## Solution

The test client simply passes the file to be used as an input stream to the
appropriate constructor. Here's an example of running it:

```bash
./gradlew -q --console=plain -PmainClass=com.segarciat.algs4.ch4.sec1.ex07.BuildGraph run --args='algs4-data/tinyG.txt'
```

Here's the sample output:

```text
13 vertices, 13 edges 
0: 6 2 1 5 
1: 0 
2: 0 
3: 5 4 
4: 5 6 3 
5: 3 4 0 
6: 0 4 
7: 8 
8: 7 
9: 11 10 12 
10: 9 
11: 9 12 
12: 11 9
```