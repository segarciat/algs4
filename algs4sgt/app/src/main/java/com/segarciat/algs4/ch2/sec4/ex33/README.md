# Exercise 2.4.33

*Index priority-queue implementation*. Implement the basic operations in
the index priority-queue API on page 320, by modifying `Algorithm 2.6`
as follows: Change `pq[]` to hold indices, add an array `keys[]`
to hold the key values, and add an array `qp[]` that is the inverse
of `pq[]` --- `qp[i]` gives the position of `i` in
`pq[]` (the index `j` such that `pq[j]` is `i`).
Then modify the code in `Algorithm 2.6` to maintain these data structures.
Use the convention that `qp[i] = -1` if `i` is not on the queue,
and include a method `contains()` that tests this condition. You need
to modify the helper methods `exchange()` and `less()`, but
not `sink()` and `swim()`.

## Solution

Initially my implementation of `delete(int i)` only called `sink()`
to restore the heap condition. I noticed that the book solution
used both `sink()` and `swim()`, and I did not think it was necessary
to apply `swim()`, too. I believed my approach was correct because
in using the operation `exchange(qp[i], n--)`, I was making the
unconscious assumption that the key removed was being swapped with
a leaf in the same subtree. However, this is not guaranteed unless
`qp[i]` is `1`, which is where the root of the tree is. Since the
key of the leaf that `q[i]` is exchanged with may be in a different
subtree altogether, we cannot guarantee that a `sink()` is sufficient.
Thus, we use `swim()` in case it is larger than a lot of the keys
in this subtree, too. The key's implementation of `insert()` is
also different, though in light of the previous discussion. My
implementation simply deletes the old key and adds the new one,
which admittedly is less efficient.