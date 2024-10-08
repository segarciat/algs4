# Exercise 1.5.14

*Weighted quick-union by height*. Develop a `UF` implementation that uses the
same basic strategy as weighted quick-union but keeps track of tree height and
always links the shorter tree to the taller one. Prove a logarithmic bound
on the height of the trees for `n` sites with your algorithm.

## Solution

The height of a tree with `n` sites produced by this algorithm is at most
$\lg n$. See my proof in the corresponding PDF for Section 1.5.