# Exercise 3.2.25

*Perfect balance*. Write a program that inserts a set of keys into an initially
empty BST such that the tree produced is equivalent to binary search, in the
sense that the sequence of compares done in the search for any key in the BST
is the same as the sequence of compares used by binary search for the same key.

## Solution

I achieved this by first sorting the given set of keys, and then recursively inserting the
middle key of the current range of keys.