# Exercise 3.1.38

*Amortized cost plots*. Develop instrumentation for `FrequencyCounter`, `SequentialSearchST`,
and `BinarySearchST` so that you can produce plots like the ones in this section showing
the cost of each `put()` operation during the computation:

## Solution

By running the command

```bash
./gradlew -q --console=plain -PmainClass=com.segarciat.algs4.ch3.sec1.ex38.SequentialSearchST run --args='8' < app/algs4-data/tale.txt
```

I produced the plot

![Sequential Search Amortized Cost Plot for FrequencyCounter with `tales.txt`](./sequential-search-st-amortized-cost-plot.png)