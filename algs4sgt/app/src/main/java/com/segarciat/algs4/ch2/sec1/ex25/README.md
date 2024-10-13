# Exercise 2.1.25

*Insertion sort without exchanges*. Develop an implementation of insertion sort
that moves larger elements to the right one position with one array access per entry,
rather than `exchange()`. Use `SortCompare` to evaluate the effectiveness of doing so.

## Solution

I implemented this by holding a temporary copy to the rightmost element in the
inner `for` loop, comparing all elements against it, and placing that element
at the leftmost `j` index after the inner loop ends.

I performed experiments with `SortCompare` by running the following command;

```bash
./gradlew -q --console=plain -PmainClass=com.segarciat.algs4.ch2.sec1.SortCompare run --args 'InsertionHalf Insertion 10000 5'
```

With arrays with about 10,000 doubles, I consistently saw better
performance for `InsertionHalf`, usually at least 20% and even as much as 50% better:

```text
For 10000 random Doubles
    InsertionHalf is 1.2 times faster than Insertion
```