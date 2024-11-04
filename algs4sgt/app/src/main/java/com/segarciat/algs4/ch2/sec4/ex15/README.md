# Exercise 2.4.15

Design a linear-time certification algorithm to check whether an array `pq[]`
is a min-oriented heap.

## Solution

I employed a non-recursive solution where I checked starting at the root,
which I assumed to be `pq[0]`. If any entry is `null`, then I consider it
to not be a min-oriented heap. Moreover, if we are at index `k`, if its
children at indices `2k * 1` or `2k * 1` are smaller, than we do not
have a min-oriented heap.