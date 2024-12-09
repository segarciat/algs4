# Exercise 3.2.30

*BST reconstruction*. Given the preorder (or postorder) traversal of a BST (not including null nodes),
design an algorithm to reconstruct the BST.

## Solution

When doing a preorder traversal, the current node is examined before descending into
the left and right subtrees. As a result, the root of each subtree is examined before
that node's subtrees. Therefore, by adding the nodes in the same order as the given
preorder traversal, we reconstruct the binary tree.

When doing a postorder traversal, the current node is examined only after its left
and right subtree have been examined. To recover a similar behavior to the preorder
traversal, where the root of each subtree is examined first we can reverse the keys
in the postorder to do a "reverse postorder" traversal.