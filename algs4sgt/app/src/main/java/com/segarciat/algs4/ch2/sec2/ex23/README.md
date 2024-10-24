# Exercise 2.2.23

*Improvements*. Run empirical studies to evaluate the effectiveness of each of the
three improvements to mergesort that are described in the text (see Exercise 2.2.11).
ALso, compare the performance of the merge implementation given in the text with
the merge described in Exercise 2.2.10. In particular, empirically determine the
best value of the parameter that decides when to switch to insertion sort for
small sub-arrays.

## Solution

To run my empirical studies, I implemented the suggested mergesort improvements
in separate classes:

- `MergeOrderCheck`: This class  implements the
improvement where a check is performed to see if `a[mid] <= a[mi+1]`, and if so,
decides to skip the call to `merge()`. This works because if `a[lo..mid]` is sorted,
and `a[mid+1..hi]` is sorted, with `a[mid] <= a[mid+1]`, then `a[lo..hi]` is sorted.

- `MergeNoCopy`: This class implements  the improvement that eliminates the copy into
the auxiliary array `aux` in the `merge()` method by changing the roles of the result
array `aux` and the result arrays `a` in the recursive `sort()` call.

- `MergeInsertionCutoff`: This class implements  improvement where mergesort performs
  insertion sort when the sub-arrays `a[lo..hi]` a cutoff threshold.

- `com.segarciat.algs4.ch2.sec2.ex11.MergeImproved`: This class combines all three
improvements. I implemented it as part of Exercise 2.2.11, so I decided to re-purpose
it in this exercise.

For each, I ran timed doubling tests using `SortCompare.timeRandomInput()` to observe
whether the algorithm still correctly  sorted the input array, and that  linearithmic
performance was likely attained. Also, I updated my `com.segarciat.algs4.ch2.sec2.SortCompare`,
which implements the `SortCompare` class described in Section 2.1. I added string identifiers
for the variations of mergesort implemented here, and ran it against the `sort()` implemented
by `Merge`.

### `MergeOrderCheck`

Below is a sample run from the doubling test:

```text
n=512, ratio=0.0
n=1024, ratio=Infinity
n=2048, ratio=2.3
n=4096, ratio=2.3
n=8192, ratio=2.2
n=16384, ratio=2.2
n=32768, ratio=1.3
n=65536, ratio=0.7
n=131072, ratio=2.1
n=262144, ratio=3.6
n=524288, ratio=2.2
n=1048576, ratio=2.2
n=2097152, ratio=2.3
n=4194304, ratio=2.3
```

Since the ratios are close to 2, the performance appears like it continues to
be close to linearithmic. I then ran tests using `SortCompare` to see the
comparative performance with the default `Merge` implementation:

```bash
./gradlew -q --console=plain -PmainClass=com.segarciat.algs4.ch2.SortCompare run --args='MergeOrderCheck Merge 1000000 5'
```

The results I got were mixed. Here are some sample outcomes:

```text
For 1000000 random Doubles
    MergeOrderCheck is 1.12 times faster than Merge
    
    For 1000000 random Doubles
    MergeOrderCheck is 0.94 times faster than Merge
    
    For 1000000 random Doubles
    MergeOrderCheck is 1.04 times faster than Merge
```

Overall it appeared, it appeared that the largest improvement with
`MergeNoOrderCheck` over `Merge` was on the order of about 12% at most.
From the second sample above, at times it seemed to perform worse, but
not by much, and I suspect it was due to other system-related conditions.
Overall it seems it does offer a slight improvement. That being said,
the real advantage of this improvement is that it guarantees linear
performance for arrays that are already sorted, and it does while maintaining
its linearithmic performance.

### `MergeNoCopy`

Below is a sample run for the doubling test:

```text
n=512, ratio=1.0
n=1024, ratio=2.0
n=2048, ratio=2.3
n=4096, ratio=2.4
n=8192, ratio=1.7
n=16384, ratio=0.7
n=32768, ratio=1.2
n=65536, ratio=1.8
n=131072, ratio=2.3
n=262144, ratio=2.9
n=524288, ratio=2.2
n=1048576, ratio=2.2
n=2097152, ratio=2.3
n=4194304, ratio=2.2
```

Once again, I surmised that the improvement did not cause a degradation
from the linearithmic performanc eof the original `Merge.sort()`.
I then compared against `Merge` using `SortCompare`:

```bash
./gradlew -q --console=plain -PmainClass=com.segarciat.algs4.ch2.SortCompare run --args='MergeNoCopy Merge 1000000 5'
```

Some sample outcomes are below:

```text
For 1000000 random Doubles
    MergeNoCopy is 1.63 times faster than Merge
    
For 1000000 random Doubles
    MergeNoCopy is 1.47 times faster than Merge
    
For 1000000 random Doubles
    MergeNoCopy is 1.59 times faster than Merge
```

Across the board, I consistently saw a huge performance boost of about
50% over the default `Merge.sort()`, which confirms the suspicion that
a significant amount of time is spent copying data into the auxiliary
array. Keep in mind that `MergeNoCopy.sort()` still must place an initial
copy of the array into `aux`, but this happens once, outside the `merge()`
call that is call recursively. Since the copy takes linear time and does
not fall in the inner loop (inside `merge()`), it is inconsequential
when it comes to the asymptotic performance of `sort()`.

### `MergeInsertionCutoff`

For this improvement, I use cutoff values that were one smaller than
a power of 2, such as `63`, `31`, `15`, `7`, and `3`, since that way,
`hi <= lo + CUTOFF` implies an array of at most `CUTOFF` size.

#### Doubling Tests

Below is a doubling test run for a `CUTOFF` of `63`:

```text
n=512, ratio=NaN
n=1024, ratio=Infinity
n=2048, ratio=1.0
n=4096, ratio=2.3
n=8192, ratio=1.6
n=16384, ratio=2.9
n=32768, ratio=2.8
n=65536, ratio=1.0
n=131072, ratio=0.7
n=262144, ratio=4.7
n=524288, ratio=2.1
n=1048576, ratio=2.2
n=2097152, ratio=2.1
n=4194304, ratio=2.2
n=8388608, ratio=2.1
n=16777216, ratio=2.2
n=33554432, ratio=2.1
n=67108864, ratio=2.1
```

Below is a run for `CUTOFF` of `31`:

```text
n=512, ratio=1.0
n=1024, ratio=0.0
n=2048, ratio=Infinity
n=4096, ratio=2.0
n=8192, ratio=1.5
n=16384, ratio=2.3
n=32768, ratio=2.6
n=65536, ratio=3.2
n=131072, ratio=0.5
n=262144, ratio=2.4
n=524288, ratio=2.2
n=1048576, ratio=2.1
n=2097152, ratio=2.2
n=4194304, ratio=2.1
n=8388608, ratio=2.1
n=16777216, ratio=2.2
n=33554432, ratio=2.2
n=67108864, ratio=2.0
```

Below is a run for a `CUTOFF` of `15`:

```text
n=512, ratio=1.0
n=1024, ratio=1.0
n=2048, ratio=1.0
n=4096, ratio=1.0
n=8192, ratio=1.0
n=16384, ratio=4.0
n=32768, ratio=2.8
n=65536, ratio=4.8
n=131072, ratio=0.9
n=262144, ratio=2.3
n=524288, ratio=1.5
n=1048576, ratio=2.1
n=2097152, ratio=2.1
n=4194304, ratio=2.2
n=8388608, ratio=2.2
n=16777216, ratio=2.2
n=33554432, ratio=2.1
n=67108864, ratio=2.3
```

Below is a run for a `CUTOFF` of `7`:

```text
n=512, ratio=0.0
n=1024, ratio=NaN
n=2048, ratio=Infinity
n=4096, ratio=1.0
n=8192, ratio=2.0
n=16384, ratio=2.0
n=32768, ratio=2.8
n=65536, ratio=5.0
n=131072, ratio=1.4
n=262144, ratio=1.1
n=524288, ratio=2.1
n=1048576, ratio=2.2
n=2097152, ratio=2.2
n=4194304, ratio=2.2
n=8388608, ratio=2.2
n=16777216, ratio=2.2
n=33554432, ratio=2.3
n=67108864, ratio=2.0
```

Finally, below is a run for a `CUTOFF` of `3`:

```text
n=512, ratio=0.0
n=1024, ratio=NaN
n=2048, ratio=Infinity
n=4096, ratio=1.0
n=8192, ratio=2.0
n=16384, ratio=3.5
n=32768, ratio=2.4
n=65536, ratio=3.8
n=131072, ratio=1.7
n=262144, ratio=0.9
n=524288, ratio=2.0
n=1048576, ratio=2.2
n=2097152, ratio=2.2
n=4194304, ratio=2.2
n=8388608, ratio=2.2
n=16777216, ratio=2.2
n=33554432, ratio=2.3
n=67108864, ratio=2.1
```

Overall, for the chosen `CUTOFF` values, the performance continues to
be linearithmic for large `n`.

#### Comparative Performance and Optimal `CUTOFF`

I use `SortCompare` again:

```text
./gradlew -q --console=plain -PmainClass=com.segarciat.algs4.ch2.SortCompare run --args='MergeInsertionCutoff Merge 1000000 5'
```

I ran it for different `CUTOFF` values which I would manually adjust.

For `CUTOFF` of `63`, here are some sample runs:

```text
For 1000000 random Doubles
    MergeInsertionCutoff is 2.24 times faster than Merge

For 1000000 random Doubles
    MergeInsertionCutoff is 1.86 times faster than Merge
    
For 1000000 random Doubles
    MergeInsertionCutoff is 1.90 times faster than Merge
```

For a `CUTOFF` of `31`, here are some sample runs:

```text
For 1000000 random Doubles
    MergeInsertionCutoff is 1.92 times faster than Merge
   
For 1000000 random Doubles
    MergeInsertionCutoff is 1.54 times faster than Merge

For 1000000 random Doubles
    MergeInsertionCutoff is 1.62 times faster than Merge
```

For a `CUTOFF` of `15`, here are some sample runs:


```text
For 1000000 random Doubles
    MergeInsertionCutoff is 1.33 times faster than Merge

For 1000000 random Doubles
    MergeInsertionCutoff is 1.76 times faster than Merge

For 1000000 random Doubles
    MergeInsertionCutoff is 1.49 times faster than Merge
```

For a `CUTOFF` of `7`, here are some sample runs:

```text
For 1000000 random Doubles
    MergeInsertionCutoff is 1.57 times faster than Merge
    
For 1000000 random Doubles
    MergeInsertionCutoff is 1.38 times faster than Merge

For 1000000 random Doubles
    MergeInsertionCutoff is 1.44 times faster than Merge
```

For a `CUTOFF` of `3`, here are some sample runs:

```text
For 1000000 random Doubles
    MergeInsertionCutoff is 1.49 times faster than Merge
    
For 1000000 random Doubles
    MergeInsertionCutoff is 1.25 times faster than Merge
    
For 1000000 random Doubles
    MergeInsertionCutoff is 1.35 times faster than Merge
```

Overall it appears that using a larger `CUTOFF` provides
a substantial improvement, in the order of 100% improvement
for a `CUTOFF` value of `63` for 1 million random `Double` values.
In fact, I explored larger values of `n` and saw similar performance.
However, I also realized that although this makes the asymptotic
performance of `merge()` better, it also means that it may under-perform
for small `n`.

The authors suggested that for a length of about `15`
I might have seen about 10% to 15% improvement, but the reality
(unless I am misinterpreting it) is that it is much more substantial.
Perhaps this is because I am using the version of insertion sort
that does only half-exchanges, or an aspect specific to my system.
From the data, I think the best value on my system is a cutoff
of about `63`.

### `MergeImproved`

This implementation combines all the improvements from before, and makes
use of the `CUTOFF` value of `63` that I determined to be the best on my
machine. Here's a few sample runs of `SortCompare`:

```bash
./gradlew -q --console=plain -PmainClass=com.segarciat.algs4.ch2.SortCompare run --args='MergeImproved Merge 1000000 5'
```

Below are some sample results:

```text
For 1000000 random Doubles
    MergeImproved is 3.27 times faster than Merge

For 1000000 random Doubles
    MergeImproved is 3.34 times faster than Merge

For 1000000 random Doubles
    MergeImproved is 3.15 times faster than Merge
```

The combined effect leads to a consistent improvement of about 300%, or
3 times as fast.