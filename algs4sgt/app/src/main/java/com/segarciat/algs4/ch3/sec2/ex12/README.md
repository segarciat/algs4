# Exercise 3.2.12

Develop a `BST` implementation that omits `rank()` and `select()` and does
not use a count field in `Node`.

## Solution

I thought to implement it by leveraging the private `keys(node, queue, lo, hi)` method,
and then just called the `.size()` method on the queue. To avoid the extra space for
using the queue, I instead implemented a similar recursive method that does inorder
traversal.