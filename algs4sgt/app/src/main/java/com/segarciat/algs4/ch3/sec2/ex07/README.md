# Exercise 3.2.7

Add ato `BST` a recursive method `avgCompares()` that computes the average number of compares
required by a random hit in a given BST (the internal path length of the tree divided by
its size, plus one). Develop two implementations: a recursive method (which takes linear
time and space proportional to the height), and a method like `size()` that adds a field
to each node in the tree (and takes linear space and constant time per query).

## Solution

To support the computation, I made use of a third argument called `depth` that
keeps track of the recursion depth to compute the internal path length on the
way up the tree. The idea is that the root has a `depth` of `0`, and each recursive
call increases `depth` by `1`.