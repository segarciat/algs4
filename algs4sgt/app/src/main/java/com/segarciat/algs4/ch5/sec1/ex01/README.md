# Exercise 5.1.1

Develop a sort implementation that counts the number of distinct key values,
then uses a symbol table to apply key-indexed counting to sort the array.
(This method is *not* for use when the number of different keys is large.)

## Solution

In the traditional example of key-indexed counting, we know that keys are
integers between $0$ and $R-1$ for some positive integer parameter $R$, and
we use that to create an array to compute the frequency counts. Given an array
of strings, 