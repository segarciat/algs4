# Exercise 2.4.24

*Priority queue with explicit links*. Implement a priority queue using
a heap-ordered binary tree, but use a triply linked structure instead of
an array. You will need three links per node: two to traverse down the tree
and one to traverse up the tree. Your implementation should guarantee
logarithmic running time per operation, even if no maximum priority-queue
size is known a head of time.

## Solution

This was a challenging problem because I did not initially know how to
ensure that the binary tree remained "complete" at each step. In the
implementation using an array, it was easy because the array size was
precisely the location of the next key. However, if each node in the
linked implementation only had fields for the parent, left child, and
right child, then there would be no way to determine where the next
item should be inserted.

To handle this, I included an extra field `size` in each `Node`. This
field is the number of nodes in the subtree rooted at any given node.
To complement this, I included a field `subTreeTargetSize` in the
`LinkedMaxPQ`, representing the most nodes that each subtree of the
`root` should have after the next insertion to ensure the tree is
complete.

For example, suppose that the binary tree is perfect. Such a tree
has $2^n-1$ nodes for positive integer $n$. Suppose that
$n=5$, so that the tree has $2^5-1=31$ nodes. Then after the next
insertion, the tree will have 32 nodes. Such an insertion should
occur in the left subtree of the root, meaning that subtree will
have 16 nodes afterward. In order to be perfect again, the tree
will have to grow to 63 nodes, and when this occurs, both subtrees
of the root will have 31 nodes. Therefore, `subtreeTargetSize = 31`
in this case.

Suppose now that the binary tree is complete but not perfect. This
means the tree size is not $2^n-1$ for positive integer $n$. For
example, suppose that the tree size is $29$. Then the leaf subtree
has 15 nodes and the right subtree has 13 nodes. To be complete,
both subtrees must have 15 nodes. Therefore, `subTreeTargetSize = 15`.

Using this, I implemented a way to find the correct node to add and
delete at each step. To ensure the heap condition was preserved, I
implemented versions of `swim()` and `sink()` catered to the linked
structure, based on the array-based implementations in the book.
I also added a method `isMaxHeapOrdered()` to certify that the heap
was indeed heap-ordered.

To test the efficiency of the algorithm, I included a doubling test
that built heap or `n` items.If my implementations of `insert()` and
`delMax()` have an order of growth of $\sim \lg n$, then I expected
to have an order  of growth of about $n \lg n$ for the construction
of the heap of `n` items through `n` calls to `insert()`. The following
values are from a sample run:

```text
n=512, ratio=1.0
n=1024, ratio=0.0
n=2048, ratio=Infinity
n=4096, ratio=0.0
n=8192, ratio=Infinity
n=16384, ratio=0.5
n=32768, ratio=3.0
n=65536, ratio=2.3
n=131072, ratio=2.9
n=262144, ratio=1.9
n=524288, ratio=2.4
n=1048576, ratio=2.0
n=2097152, ratio=1.8
n=4194304, ratio=2.7
n=8388608, ratio=2.0
n=16777216, ratio=2.1
n=33554432, ratio=2.5
n=67108864, ratio=2.0
```

Since the ratios hover around 2, it appears that the construction does have
near linear (or linearithmic) performance.