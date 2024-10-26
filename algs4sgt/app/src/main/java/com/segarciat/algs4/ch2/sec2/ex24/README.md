# Exercise 2.2.24

*Sort-test improvement*. Run empirical studies for large randomly ordered arrays
to study the effectiveness of the modification described in Exercise 2.2.8 for
random data. In particular, develop a hypothesis about the average number of
times the test (whether an array is sorted) succeeds, as a function of *n*
(the original length for the sort).

## Solution

From running doubling tests I observed the following values:

```text
n=512, inOrderSuccessCount=152, successToSizeRatio=0.30
n=1024, inOrderSuccessCount=287, successToSizeRatio=0.28
n=2048, inOrderSuccessCount=595, successToSizeRatio=0.29
n=4096, inOrderSuccessCount=1180, successToSizeRatio=0.29
n=8192, inOrderSuccessCount=2468, successToSizeRatio=0.30
n=16384, inOrderSuccessCount=4771, successToSizeRatio=0.29
n=32768, inOrderSuccessCount=9585, successToSizeRatio=0.29
n=65536, inOrderSuccessCount=19220, successToSizeRatio=0.29
n=131072, inOrderSuccessCount=38405, successToSizeRatio=0.29
n=262144, inOrderSuccessCount=76870, successToSizeRatio=0.29
n=524288, inOrderSuccessCount=153487, successToSizeRatio=0.29
n=1048576, inOrderSuccessCount=308364, successToSizeRatio=0.29
n=2097152, inOrderSuccessCount=616364, successToSizeRatio=0.29
n=4194304, inOrderSuccessCount=1231201, successToSizeRatio=0.29
n=8388608, inOrderSuccessCount=2463031, successToSizeRatio=0.29
n=16777216, inOrderSuccessCount=4925234, successToSizeRatio=0.29
```

For large $n$, it appears that the average number of times the test passes
is around $0.29n$.