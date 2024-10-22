# Exercise 2.2.16

*Natural mergesort*. Write a version of bottom-up mergesort that takes advantage
of order in the array by proceeding as follows each time it needs to find
two arrays to merge: find a sorted subarray (by incrementing a pointer until
finding an entry that is smaller than its predecessor in the array), then find
the next, then merge them. Analyze the running time of this algorithm in terms
of the array length and the number of maximal increasing sequences in the array.

## Solution

I included a doubling test like the one in Section 1.4 to verify the performance
of this algorithm. Below are some results:

```text
n=512, ratio=1.0
n=1024, ratio=1.0
n=2048, ratio=2.0
n=4096, ratio=1.5
n=8192, ratio=1.7
n=16384, ratio=2.0
n=32768, ratio=4.1
n=65536, ratio=2.3
n=131072, ratio=0.8
n=262144, ratio=1.7
n=524288, ratio=2.2
n=1048576, ratio=2.3
n=2097152, ratio=2.0
n=4194304, ratio=2.5
n=8388608, ratio=2.0
n=16777216, ratio=2.7
```

The ratios are the time it took to run the for the previous `n` and
the current `n`. That is, how long it takes to sort an array of twice
the size. Based on the ratio, it's reasonable to say the algorithm
is close to linear (and certainly better than quadratic), so may be
linearithmic.