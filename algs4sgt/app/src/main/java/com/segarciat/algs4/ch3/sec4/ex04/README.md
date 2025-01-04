# Exercise 3.4.4

Write a program to find values of `a` and `m`, with `m` as small as possible,
such that the hash function `(a * k) % m` for transforming the $k$th letter
of the alphabet into a table index produces distinct values (no collisions)
for the keys `S E A R C H X M P L`. The result is known as a *perfect hash function*.

## Solution

I began searching with an `m` value of `9` because there are 9 letters.
Also, for each value of `m`, I try all values of `a` from `1` through `m - 1`.
There is no need to try values of `a` that are `m` or greater because
we are computing modulo `m`.