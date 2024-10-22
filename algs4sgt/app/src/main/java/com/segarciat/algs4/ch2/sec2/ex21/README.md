# Exercise 2.2.21

*Triplicates*. Given three lists of *n* names each, devise a linearithmic
algorithm to determine if there is a name common to all three lists, and
if so, return the lexicographically first such name.

## Solution

Since there is no space restriction in this question, I began by creating
a copy of each array and sorting it. Once we have three sorted arrays,
we can iterate through all 3 sorted arrays at the same time. Each time
we see if the current index for all three is the same string. Otherwise,
we increment the index of the one with the lowest value. Each iteration
one index increases, so this takes 3n iterations.