# Exercise 4.1.38

*Nonrecursive depth-first search*. Implement depth-first search using an
explicit stack instead of recursion. *Warning*: Replacing the queue in
`BreadthFirstPaths` with a stack yields some graph searching algorithm
but not depth-first search.

## Solution

I accomplished this by using a stack of iterators. The iterators correspond
to the adjacency lists of each vertex. The benefit is that the iterators
have state. Namely, to process an item, we use the `.next()` method. When
an unmarked item is seen, we push an iterator corresponding to its adjacency
list to the stack. We pop iterators off the stack when they are empty.

To verify this worked, I ran this using the same test client as the book,
as follows:

```bash
./gradlew -q --console=plain -PmainClass=com.segarciat.algs4.ch4.sec1.ex38.NonRecursiveDFSPaths run --args='algs4-data/tinyCG.txt 0'
```

I got the same output as in the text:

```text
0 to 0: 0
0 to 1: 0-2-1
0 to 2: 0-2
0 to 3: 0-2-3
0 to 4: 0-2-3-4
0 to 5: 0-2-3-5
```