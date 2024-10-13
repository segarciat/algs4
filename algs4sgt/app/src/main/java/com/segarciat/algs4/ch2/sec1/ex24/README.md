# Exercise 2.1.24

*Insertion sort with sentinel*. Develop an implementation of insertion sort that
eliminates the `j>0` test in the inner loop by first putting the smallest item
into position. Use `SortCompare` to evaluate the effectiveness of doing so.
*Note*: It is often possible to avoid an index-out-of-bounds test in this way ---
the element that enables the test to be eliminated is known as a *sentinel*.

## Solution

I implemented this by placing the minimum at the left end of the array.
This way, any compares against the minimum will never succeed, and the
index will not reach out of bounds.

To assess whether there was an improvement, I ran `InsertionSentinel.java` against
`Insertion.java` using the `SortCompare` implementation provided in the text.

I ran `SortCompare` from the top-level directory of this repository as follows:

```bash
./gradlew -q --console=plain -PmainClass=com.segarciat.algs4.ch2.sec1.SortCompare run --args 'InsertionSentinel Insertion 10000 5'
```

The experiment yielded mixed results. For large arrays, such as arrays of size 10,000,
`InsertionSentinel` underperformed  compared to `Insertion`. For smaller arrays,
such as says 1,000, `InsertionSentinel` would often perform better.

Here are the results of a sample run:

```text
For 10000 random Doubles
    InsertionSentinel is 0.9 times faster than Insertion
```
