# Exercise 2.3.19

*Median-of-5-partitioning*. Implement a quicksort based on partitioning on the
median of a random sample of five items from the subarray. Put the items of
the sample at the appropriate ends of the array so that only the median
participates in partitioning. Run doubling tests to determine the effectiveness
of the change, in comparison to the standard algorithm and to median-of-3
partitioning (see the previous exercise).
*Extra-credit*: Devise a median-of-5 algorithm that uses fewer than seven compares
on any input.

## Solution

The best I could achieve was 7 compares. I essentially sorted established
`a[lo] <= a[midLo]` and `a[mid] <= a[midHi] <= a[hi]`, then manually "merged them"
as in the merge algorithm in mergesort. I started from the highest to ensure
that `a[hi]` acted as the sentinel. I placed the median of 5 at `a[lo]`.

I ran doubling test against the median-of-3 and standard algorithms and observed
the following:

```text
n=512,
	0.25 faster than median of 3
	0.50 faster than standard
n=1024,
	1.00 faster than median of 3
	0.75 faster than standard
n=2048,
	1.00 faster than median of 3
	0.57 faster than standard
n=4096,
	0.73 faster than median of 3
	1.73 faster than standard
n=8192,
	1.43 faster than median of 3
	5.29 faster than standard
n=16384,
	0.95 faster than median of 3
	0.55 faster than standard
n=32768,
	0.88 faster than median of 3
	0.91 faster than standard
n=65536,
	1.03 faster than median of 3
	1.10 faster than standard
n=131072,
	0.92 faster than median of 3
	1.01 faster than standard
n=262144,
	0.92 faster than median of 3
	1.08 faster than standard
n=524288,
	0.92 faster than median of 3
	0.99 faster than standard
n=1048576,
	1.05 faster than median of 3
	1.16 faster than standard
n=2097152,
	0.99 faster than median of 3
	1.16 faster than standard
n=4194304,
	1.03 faster than median of 3
	1.18 faster than standard
```

It appears the algorithm does often perform better than
the standard one, but often performs no better than the
median of 3 version. This is consistent with the remark on
page 296, which claimed that most improvement is available
for a sample size of 3.