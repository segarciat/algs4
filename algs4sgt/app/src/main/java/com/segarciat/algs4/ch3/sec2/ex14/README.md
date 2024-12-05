# Exercise 3.2.14

Give non-recursive implementations of `min()`, `max()`, `floor()`, `ceiling()`, `rank()`,
`select()`, and `keys()`.

## Solution

The implementations of `min()`, `max()`, `floor()`, `ceiling()`, `rank()`, and `select()`
were relatively straightforward from their recursive counterpart. I found `keys(lo, hi)`
to be harder to implement, however.

In general, I am aware that recursive methods can  be implemented using stacks because function calls
have associated stack frames. For  example, to implement a factorial function, we can push `n` until
its value is `1`, and then pop and multiply until it is empty. However, in the case of `keys(lo, hi)`,
it was not as straightforward for me to leverage a stack to implement inorder traversal.
I was struggling with how to prevent a node from being processed repeatedly, and kept falling
into an infinite loop.

The [implementation of `keys()` on the website](https://algs4.cs.princeton.edu/32bst/NonrecursiveBST.java.html)
achieves this by only enqueuing keys after a key is popped. In other words, the "processing", which is
enqueueing, does not occur until the item is popped off the stack. Also, when descending into the
left and right subtrees, those children are not pushed, but rather, we update our `current` node
to descend into the subtrees.

I modified the code to descend into the subtrees conditionally (when the `current` key was in
the range `lo..hi`). I also created an interactive program to test the implementation is correct.