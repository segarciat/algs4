# Exercise 5.1.12

*Alphabet*. Develop an implementation of the `Alphabet` API that
is given on page 698 and use it to develop LSD and MSD sorts for
general alphabets.

## Solution

For `Alphabet`, I made use of a symbol-table to convert characters to indices, and
an array to act as an inverted index (converting integer indices to characters).
I included the client given.