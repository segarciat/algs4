# Exercise 2.2.17

*Linked-list sort*. Implement a natural mergesort for linked lists. (This is the
method of choice for sorting linked lists because it uses no extra space and
is guaranteed to be linearithmic).

## Solution

I included a doubling test like the one in Section 1.4 to verify the performance
of this algorithm. Below are some results:

```text
n=512, ratio=1.0
n=1024, ratio=0.0
n=2048, ratio=NaN
n=4096, ratio=Infinity
n=8192, ratio=2.0
n=16384, ratio=2.0
n=32768, ratio=1.3
n=65536, ratio=2.6
n=131072, ratio=1.9
n=262144, ratio=2.4
n=524288, ratio=2.3
n=1048576, ratio=2.5
n=2097152, ratio=2.3
n=4194304, ratio=2.1
n=8388608, ratio=2.4
n=16777216, ratio=3.2
n=33554432, ratio=2.5
```

The ratios are the time it took to run the for the previous `n` and
the current `n`. That is, how long it takes to sort an array of twice
the size. Based on the ratio, it's reasonable to say the algorithm
is close to linear (and certainly better than quadratic), so may be
linearithmic.