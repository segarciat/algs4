# Exercise 2.3.7

Find the expected number of sub-arrays of size $0$, $1$, and $2$ when quicksort
is used to sort an array of $N$ items with distinct keys. If you are mathematically
inclined, do the math; if not, run experiments to develop hypotheses.

## Solution

The program I wrote counts the number of sub-arrays of size 2. Below
is the output of a sample run:

```text
n = 2, Count of Arrays of size 2 or less = 3, Ratio of count to n = 1.50
n = 4, Count of Arrays of size 2 or less = 4, Ratio of count to n = 1.00
n = 8, Count of Arrays of size 2 or less = 8, Ratio of count to n = 0.98
n = 16, Count of Arrays of size 2 or less = 14, Ratio of count to n = 0.89
n = 32, Count of Arrays of size 2 or less = 28, Ratio of count to n = 0.87
n = 64, Count of Arrays of size 2 or less = 53, Ratio of count to n = 0.83
n = 128, Count of Arrays of size 2 or less = 105, Ratio of count to n = 0.82
n = 256, Count of Arrays of size 2 or less = 213, Ratio of count to n = 0.83
n = 512, Count of Arrays of size 2 or less = 422, Ratio of count to n = 0.82
n = 1024, Count of Arrays of size 2 or less = 859, Ratio of count to n = 0.84
n = 2048, Count of Arrays of size 2 or less = 1707, Ratio of count to n = 0.83
n = 4096, Count of Arrays of size 2 or less = 3404, Ratio of count to n = 0.83
n = 8192, Count of Arrays of size 2 or less = 6845, Ratio of count to n = 0.84
n = 16384, Count of Arrays of size 2 or less = 13676, Ratio of count to n = 0.83
n = 32768, Count of Arrays of size 2 or less = 27291, Ratio of count to n = 0.83
n = 65536, Count of Arrays of size 2 or less = 54660, Ratio of count to n = 0.83
n = 131072, Count of Arrays of size 2 or less = 109285, Ratio of count to n = 0.83
n = 262144, Count of Arrays of size 2 or less = 218374, Ratio of count to n = 0.83
n = 524288, Count of Arrays of size 2 or less = 436873, Ratio of count to n = 0.83
n = 1048576, Count of Arrays of size 2 or less = 873932, Ratio of count to n = 0.83
n = 2097152, Count of Arrays of size 2 or less = 1747220, Ratio of count to n = 0.83
```

From this, I formed the hypothesis that if $N$ is the number of distinct elements
in the array, then the number of sub-arrays of size 2 encountered by quicksort
is about $\sim 0.83N$. Notice that $\frac{5}{6}\sim 0.8\overline{3}$.
the corresponding PDF file for this section, whose path relative to the top-level
of this repository is `pdf/2-Sorting/3-Quicksort/quicksort.pdf`, I arrived at
a recurrence describing the expected (average) number of sub-arrays of sizes 0, 1, and 2,
given by $T(0)=1$, $T(1)=1$, $T(2)=3$, and

$$
T(N)=\frac{2}{N}\sum_{j=1}^{N-1}T(j),\quad N\geq 3
$$

I determined that the solution to this recurrence for $N\geq 4$ was:

$$
T(N)=\frac{5}{6}(N+1)
$$

Here you can see the $\frac{5}{6}$, consistent with the ratios observed experimentally.
See the pdf for more details of how I arrived at $\frac{5}{6}$ analytically.