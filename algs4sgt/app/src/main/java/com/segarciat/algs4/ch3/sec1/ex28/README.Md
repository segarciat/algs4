# Exercise 3.1.28

*Ordered insertions*. Modify `BinarySearchST` so that inserting a key that is larger
than all keys in the table takes constant time (so that building a table by calling
`put()` for keys that are in order takes linear time).

## Solution

To accommodate this, we simply need to check against the last key in the table
prior to calling `rank()` in `put()`. If `key` is greater than or equal to the
last key  in the table, then by transitivity and uniqueness, it is greater than
all the other keys as well.