# Exercise 1.5.12

*Quick-union with path compression*. Modify quick-union (page 224) to include
*path-compression*, by adding a loop to `find()` that links every site on the
path from `p` to the `root`. Give a sequence of input pairs that causes this
method to produce a path of length 4.
*Note*: The amortized cost per operation for this algorithm is known to be
logarithmic.

## Solution

The following
sequence of input pairs yields a path of length 4:

```text
1-0 2-3 0-2 0-4 4-5
```

In general, joining the root of a tree with another will increase the tree height.
This is because if `p` is a root, then `find(p)` will only encounter `p` on the path
to the root. Thus, the paths with greater depth will not be renamed. In the
tree that is formed, node with id `3` has a depth of 4.