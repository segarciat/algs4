# Exercise 1.5.13

*Weighted quick-union with path compression*. Modify weighted quick-union (Algorithm 1.5)
to implement path compression, as described in Exercise 1.5.12. Give a sequence of input
pairs that causes this method to produce a tree of height 4. *Note*: The amortized cost
per operation for this algorithm is known to be bounded by a function known as the
*inverse Ackermann function* and is less than 5 for any conceivable value of *n* that
arises in practice.

## Solution

The following sequence of input pairs produces a tree of height 3 with `0` as the root:

```text
0-1 2-3 0-2 4-5 6-7 0-4
```

The following sequence of input pairs produces another tree of height 3 with `8` as the root:

```text
8-9 10-11 8-10 12-13 14-15 8-12
```

Finally, the following pair combines the trees and creates a tree of height 4 rooted at `0`:

```text
0-8
```

In this tree, there are two nodes with a depth of 4: node `7` and node `15`.