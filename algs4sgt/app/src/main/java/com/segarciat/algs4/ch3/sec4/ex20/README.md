# Exercise 3.4.20

Add a method to `LinearProbingHashST` that computes the average cost of a search hit
in the table, assuming that each key in the table is equally likely to be sought.

## Solution

To implement the specified method, I hashed all keys in the table and counted the
number of iterations needed to arrive at the key, which could be more than 1 because
of clusters that may form.