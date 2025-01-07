# Exercise 3.4.26

*Lazy delete for linear probing*. Add to `LinearProbingHashST` a `delete()`
method that deletes a key-value pair by setting the value to `null` (but not
removing the key) and later removing the pair from the table in `resize()`.
Your primary challenge is to decide when to call `resize()`. *Note*: You
should overwrite the `null` value if a subsequent `put()` operations associates
a new value with the key. Make sure that your program takes into account the number
of such *tombstone* items, as well as the number of empty positions, in making
the decision whether to expand or contract the table.

## Solution

As indicated in the exercise, deciding when to resize was indeed the more challenging
part, but the note given provides a good starting point. I created a `tombstoned`
field that tracked how many items had been "lazy-deleted".The `delete()` method
sets the value in the `vals` array corresponding to the given `key` to `null`,
and does not modify the `keys` array. I both decrement `n`, and increment `tombstoned`.
There is no net change in the total `n + tombstoned`, but this total is what
is compared against `m/8` when deciding whether to shrink the table size.
This is adequate for the following reason. When a `get()` call is made, it's
possible that an input key might hash to an index that has a cluster with
tombstoned items. If there are search misses that lead to a probe involving
a tombstoned item, the search continues past the tombstoned item instead of
ending. This is important because if the key was set to `null`, then items
belonging to the cluster that occurred after the tombstoned item would
not be searchable. Thus, the scheme allows those items to still be found.
On the other hand, we can no longer distinguish between differnet clusters
since a tombstoned `key` may be what separates two cluster, as opposed to a
`null` entry. So even though the correctness of `get()` is assured, there
may be a performance hit. By accounting for both `tombstoned` and `n` when
resizing, we ensure a valid load factor for the table.