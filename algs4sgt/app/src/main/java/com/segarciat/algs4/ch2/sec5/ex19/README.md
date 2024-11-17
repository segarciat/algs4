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