# Exercise 2.3.17

*Sentinels*. Modify the code in Algorithm 2.5 to remove both bounds checks in the inner
`while` loops. The test against the left end of the subarray is redundant since the
partitioning item acts as a sentinel (`v` is never less than `a[lo]`). To enable removal
of the other test, put an item whose key is the largest in the whole array into `a[a.length-1]`
just after the shuffle. This item will  never move (except possibly to be swapped with an
item having the same key) and will serve as a sentinel in all subarrays involving the end
of the array. *Note*: For a subarray that does not involve the end of the array, the leftmost
entry to its right serves as a sentinel for the right end of the subarray.