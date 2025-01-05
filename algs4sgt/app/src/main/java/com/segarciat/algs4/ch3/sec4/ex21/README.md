# Exercise 3.4.21

Add a method to `LinearProbingHashST` that computes the average cost of a search *miss*
in the table, assuming a random hash function. *Note*: You do not have to compute any hash
functions to solve this problem.

## Solution

Suppose that a key that is not in the table hashes to index 0, which is part of a cluster of length $l$.
This means that the first $l$ entries are occupied, none of which are the given key, and
a `null` entry follows the cluster. This search hit thus takes $l$ probes. My implementation effectively
does this for all indices from $0$ to $m-1$. This is appropriate because a given key is equally
likely to hash to any of the table entries, so any table index is a valid starting point
for this search.