# Exercise 1.5.11

Implement *weighted quick-find*, where you always change the `id[]` entries
of the smaller component to the identifier of the larger component. How does
this change affect performance?

## Solution

This is not an improvement over (unweighted) quick-find. In particular, if `p` is any
site in any component, then `id[p]` always evaluates to the identifier of
the root of the tree. As a result, `find()` takes constant time. However,
in order to change the `id[]` entries in the smaller component to the
identifier of the larger component in the `union()` implementation, we must
iterate through all the sites. This is necessary because the links are
unidirectional; that is, they always point towards the root. In particular,
if an input pair consists of two roots, the current arrangement does not
provide an easy way to find the leaves, or any of the internal non-root
nodes. As a result, the performance of `union()` degrades to linear,
as it was in the (unweighted) quick-find implementation. In this case,
maintaining the size of the trees for each component in the `sz[]` array
added complexity to our algorithm and increased our space requirement,
with no payoff.