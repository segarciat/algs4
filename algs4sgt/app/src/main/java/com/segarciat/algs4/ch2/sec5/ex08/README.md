# Exercise 2.5.8

Write a program `Frequency` that reads strings from standard input and prints
the number of times each string occurs, in descending order of frequency.


## Solution

I accomplished this by using two priority queues: a min-oriented priority queue
of `String` objects read from standard input, and a max-oriented priority queue
with `StringNodeCount` objects, a data type I defined. The latter data type contains
s string `s` and a `frequency` field. The max-oriented priority queue is
ordered by the frequency.