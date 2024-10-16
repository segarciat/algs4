# Exercise 2.2.10

*Faster merge*. Implement a version of `merge()` that copies the second half of
`a[]` to `aux[]` in *decreasing order* and then does the merge back to `a[]`.
This change allows you to remove the code to test that each of the halves has been
exhausted from the inner loop. *Note*: The resulting sort is not stable
(see page 341).