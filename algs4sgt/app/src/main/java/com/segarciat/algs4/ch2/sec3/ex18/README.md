# Exercise 2.3.18

*Median-of-3-partitioning*. Add median-of-3 partitioning to quicksort, as described
in the text (see page 296). Run doubling tests to determine the effectiveness of
the change.

## Solution

The following are the results of a sample run of doubling tests, with
five trials per array size:

```text
n=512, ratio=0.5
n=1024, ratio=3.0
n=2048, ratio=2.0
n=4096, ratio=1.8
n=8192, ratio=1.7
n=16384, ratio=2.1
n=32768, ratio=1.3
n=65536, ratio=1.5
n=131072, ratio=1.7
n=262144, ratio=2.6
n=524288, ratio=2.6
n=1048576, ratio=2.3
n=2097152, ratio=2.3
n=4194304, ratio=2.3
```

The output suggests that the algorithm remains close to linear. Additionally, I
ran `SortCompare` to compare how `Quick.sort()` fares against it using

```bash
./gradlew -q --console=plain -PmainClass=com.segarciat.algs4.ch2.SortCompare run --args='QuickMedianOf3 Quick 1000000 5'
```

Below are the results of three sample runs:

```text
For 1000000 random Doubles
    QuickMedianOf3 is 1.10 times faster than Quick
For 1000000 random Doubles
    QuickMedianOf3 is 1.02 times faster than Quick
For 1000000 random Doubles
    QuickMedianOf3 is 1.17 times faster than Quick
```

I observed consistently better performance, with an improvement of no more than 20%.