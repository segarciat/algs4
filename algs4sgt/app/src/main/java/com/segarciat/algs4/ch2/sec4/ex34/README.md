# Exercise 2.4.34

*Index-priority queue implementation (additional operations)*. Add `minIndex()`,
`changeKey()`, and `delete()` to your implementation of **Exercise 2.4.33**.

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