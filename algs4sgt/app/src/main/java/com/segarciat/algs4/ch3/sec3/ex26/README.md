# Exercise 3.5.26

*Single top-down pass*. Develop a modified version of your solution to **Exercise 3.3.25**
that does *not* use recursion. Complete all the work splitting and balancing 4-nodes
(and balancing 3-nodes) on the way down the tree, finishing with an insertion at the bottom.

## Solution

To handle this, I used an iterative method with two nodes: the node being processed,
and its parent. Whenever the node being processed sends a node up (via a color flip),
I balance the parent if necessary.

Although the color flip and the balancing is done in one pass, a second pass is
needed to update the node counts.