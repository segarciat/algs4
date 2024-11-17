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

Effectively, `s` defines an order.

For example, say we want to see if `4` and `5` appear in different order.
Note that `a[5] == 5` and `a[6] == 4`. The value of `5` according
to `s` is `6`, because `s[5] == 6`, and the value of `4` according to `s` is `4`,
because `s[4] == 4`. Since `s[a[5]] < s[a[4]]`, it follows that `5` and `4`
are out of order  in `a` and `b`.