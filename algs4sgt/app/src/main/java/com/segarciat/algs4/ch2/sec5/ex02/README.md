# Exercise 2.52

Write a program that reads a list of words from standard input and prints all
two-word compound words in the list. For example, if `after`, `thought`, and
`afterthought` are in the list, then `afterthought` is a compound word.

## Solution

I wanted to see if there would be a way to use a priority queue to save on
space to allow for the possibility that the input stream is large. However,
in order to produce a correct result, it's necessary to know all the
input strings (for example the last string in the input may be the
prefix for a compound word for every other word in the input). Moreover,
this problem requires that we search through the list, so that doing it
by sorting is best so that we can make use of binary search.