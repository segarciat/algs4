# Exercise 1.4.19

*Local minimum of a matrix*. Given an $n$-by-$n$ array `a[]` of $n^2$ distinct
integers, design an algorithm that finds a *strict local minimum*: an entry
`a[i][j]` that is strictly less than its neighbors. Internal entries have 4 neighbors;
entries on an edge have 3 neighbors; entries on a corner have 2 neighbors. The running time
of your program should be proportional to $n$ in the worst case, which means you cannot
afford to examine all $n^2$ entries.