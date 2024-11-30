# Exercise 3.2.6

Add to `BST` a method `height()` that computes the height of the tree. Develop two implementations:
a recursive method (which takes linear time and space proportional to the height), and a method
like `size()` that adds a field to each node in the tree (and takes linear space and constant
time per query).

## Solution

The height of a tree is `0` if either the tree is empty, or if both the left and right
subtrees are empty. Otherwise, the tree is 1 more than the maximum of the subtree heights.
This is the logic behind the implementation of `height()`, which is adapted to a recursive
and non-recursive version.