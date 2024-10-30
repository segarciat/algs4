# Exercise 2.3.16

*Best case*. Write a program that produces a best-case array (with no duplicates)
for `sort()` in Algorithm 2.5: an array of $n$ items with distinct keys
having the property that every partition will produce sub-arrays that differ in
size by at most 1 (the same sub-array lengths that would happen for $n$ equal
keys). (For the purpose of this exercise, ignore the initial shuffle).

## Solution

My solution can be found in detail in the associated PDF For this chapter.
To summarize, to have a best-case array, the partition key at each stage of
quicksort should cut the array nearly in half. To do so, it should either
be the median or close to it. Given `a[lo..hi]` with `mid` being the "median"
`lo..hi`, quicksort would choose the next partition index `j` and swap
`a[j]` with `a[lo]`. Since `mid` is the median, we will ideally place
`mid` at `a[mid]`, so we want `j` to be `mid`. Since `a[mid]` will be
the next partition key for the left sub-array, it should be the median
of `lo..mid-1`. Similarly, since `a[mid+1]` will be the partition key
for the right sub-array, it should be the median of `mid+1..hi`.
Finally setting `a[lo]=mid` we achieve what we want. This leads to
the recursive algorithm implemented by my `QuicksortBestCase.bestCase()`
method.

Below is a sample output showing the number of compares performed by
quicksort on the array produced by this algorithm, for different array
sizes, compared against the compares required on the average:

```text
n=2, actual=3, average=2, actualToAverageRatio=1.50
n=4, actual=8, average=8, actualToAverageRatio=1.02
n=8, actual=21, average=24, actualToAverageRatio=0.88
n=16, actual=54, average=66, actualToAverageRatio=0.82
n=32, actual=135, average=171, actualToAverageRatio=0.79
n=64, actual=328, average=424, actualToAverageRatio=0.77
n=128, actual=777, average=1017, actualToAverageRatio=0.76
n=256, actual=1802, average=2379, actualToAverageRatio=0.76
n=512, actual=4107, average=5457, actualToAverageRatio=0.75
n=1024, actual=9228, average=12321, actualToAverageRatio=0.75
n=2048, actual=20493, average=27467, actualToAverageRatio=0.75
n=4096, actual=45070, average=60597, actualToAverageRatio=0.74
n=8192, actual=98319, average=132535, actualToAverageRatio=0.74
n=16384, actual=213008, average=287765, actualToAverageRatio=0.74
n=32768, actual=458769, average=620938, actualToAverageRatio=0.74
n=65536, actual=983058, average=1332707, actualToAverageRatio=0.74
n=131072, actual=2097171, average=2847097, actualToAverageRatio=0.74
n=262144, actual=4456468, average=6057579, actualToAverageRatio=0.74
n=524288, actual=9437205, average=12841950, actualToAverageRatio=0.73
n=1048576, actual=19922966, average=27137510, actualToAverageRatio=0.73
n=2097152, actual=41943063, average=57182262, actualToAverageRatio=0.73
n=4194304, actual=88080408, average=120179035, actualToAverageRatio=0.73
n=8388608, actual=184549401, average=251987120, actualToAverageRatio=0.73
n=16777216, actual=385875994, average=527232369, actualToAverageRatio=0.73
n=33554432, actual=805306395, average=1100981024, actualToAverageRatio=0.73
```

As you can see, the array produced by this algorithm requires about
75% of the compares normally required by quicksort on the average.