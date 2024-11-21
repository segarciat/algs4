# Exercise 2.5.19

*Kendall tau distance*. Write a program `KendallTau.java` that computes the Kendall
tau distance between two permutations in linearithmic time.

## Solution

The notion of Kendall tau distance is discussed in page 345, where the following
definitions are given:

- A permutation (rankings) is an array of `n` integers where each of the integers between
`0` and `n-1` appears exactly once.
- The Kendall tau distance between two permutations is the number of pairs that are 
in different order in the two rankings.

My algorithm is a variation of my implementation of `com.segarciat.algs4.ch2.sec2.ex19.Inversions.inversions()`.
Namely, it uses merge sort because it is linearithmic and it is stable. That method uses
`less()`, which relies on the natural order of the elements being compared. Then, it uses
merge sort to sort the array and detects inversions during the `merge()` calls.

In this program, I chose one of the two arrays, permutation `b`, as defining an "order",
and then sorted the other array, permutation `a`, according to the order defined by `b`.
Once again, I employed merge-sort, and I detect the number of elements "out of order"
(which are inversions relative to the order determined by `b`) in the `merge()` call.

For example, given permutations `a` and `b`:

```text
a = [ 0, 3, 1, 6, 2, 5, 4 ]
b = [ 1, 0, 3, 6, 4, 2, 5 ]
```

then we can think of `b` as the mapping:

```text
b[0] = 1
b[1] = 0
b[2] = 3
b[3] = 6
b[4] = 4
b[5] = 2
b[6] = 5
```

and we can use its inverse to define an order array `s`::

```text
s[1] = 0
s[0] = 1
s[3] = 2
s[6] = 3
s[4] = 4
s[2] = 5
s[5] = 6
```

so we have array

```text
s = [ 1, 0, 5, 2, 4, 6, 3 ]
```

For example, `b[6]` being `5` means that `5` is the `6`th value in the sort.
By using `s`, we can know  where `5` belongs, since `s[5]` is `6`. Now
if `a` is to be sorted according to `b`, then `5` should be the last value
in the list, and since `s[4]` is `4`, that means that `4` is still the `4`th
value. Now since `a[5] = 5` and `a[6] = 4`, that means that `a` orders
`5` before `4`, which is out of order according to `b`, saying that `5`
should be the `6`th element. We can detect this as follows:

- `s[a[5]]` is `s[5]` which is `6`.
- `s[a[6]]` is `s[4]` which is `4`
- We see `a[5]` appears before `a[6]`, but `a[5]` is `5` which is the
`6`th value, and `a[6]` is `4`, which is the `4`th value (according to `b`).
Hence, `5` and `4` are out of order in `a` relative to `b`.