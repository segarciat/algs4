# Exercise 2.2.16

*Natural mergesort*. Write a version of bottom-up mergesort that takes advantage
of order in the array by proceeding as follows each time it needs to find
two arrays to merge: find a sorted subarray (by incrementing a pointer until
finding an entry that is smaller than its predecessor in the array), then find
the next, then merge them. Analyze the running time of this algorithm in terms
of the array length and the number of maximal increasing sequences in the array.